package com.practo.om.bidsystem.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.om.bidsystem.entities.Bid;
import com.practo.om.bidsystem.entities.Item;
import com.practo.om.bidsystem.models.ResultModel;

@Repository
@Transactional
public class BidDaoImp implements BidDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ItemDao itemDao;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Bid Bid) {
		getSession().save(Bid);
	}

	@Override
	public Bid getBid(int bidId) {
		return getSession().get(Bid.class, bidId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> getItemBids(int itemId) throws JDBCConnectionException {
		Query query = getSession().createQuery("from Bid where item.id=:itemId");
		query.setParameter("itemId",itemId);
		List<Bid> result = query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> getUserBids(int userId) throws JDBCConnectionException {
		Query query = getSession().createQuery("from Bid where user.id=:userId");
		query.setParameter("userId", userId);
		List<Bid> result = query.list();
		return result;

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> getUserBidsResultOut(int userId) throws JDBCConnectionException {
		Date now=new Date();
		Query query = getSession().createQuery("from Bid where user.id=:userId and item.endTime<:now");
		query.setParameter("userId", userId);
		query.setParameter("now", now);
		List<Bid> result = query.list();
		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultModel getBidResult(int itemId) throws JDBCConnectionException {

		Item item = itemDao.getItem(itemId);
		ResultModel resultModel = new ResultModel();
		Date now = new Date();
		Date bidEndTime = item.getEndTime();

		if (bidEndTime.after(now)) {
			resultModel.setResultOut(false);
			return resultModel;
		}

		resultModel.setResultOut(true);
		Query query0 = getSession()
				.createQuery("from Bid where item.id=:itemId and bidTime<:bidEndTime");
		query0.setParameter("itemId", itemId);
		query0.setParameter("bidEndTime", item.getEndTime());
		List<Integer> resultSet0 = query0.list();
		int totalBids=resultSet0.size();
		resultModel.setTotalBids(totalBids);
		
		Integer maxBid = null;
		Query query1 = getSession()
				.createQuery("select max(bidAmount) from Bid where item.id=:itemId and bidTime<:bidEndTime");
		query1.setParameter("itemId", itemId);
		query1.setParameter("bidEndTime", item.getEndTime());
		List<Integer> resultSet1 = query1.list();
		maxBid = resultSet1.get(0);

		Bid resultBid = null;
		if (maxBid != null) {
			Query query2 = getSession().createQuery("from Bid b where bidAmount=:maxBid and item.id=:itemId");
			query2.setParameter("itemId", itemId);
			query2.setParameter("maxBid", maxBid);
			List<Bid> resultSet2 = query2.list();
			resultBid = resultSet2.get(0);
		}
		if (resultBid != null) {
			resultModel.setFinalBidAmount(resultBid.getBidAmount());
			resultModel.setResultOut(true);
			resultModel.setWinner(resultBid.getUser().getName());
			resultModel.setStartBidValue(resultBid.getItem().getInitialPrice());
			resultModel.setBidTime(resultBid.getBidTime());
		}
		return resultModel;
	}

	@Override
	public void update(Bid bid) {
		getSession().update(bid);
	}

	@Override
	public void delete(Bid bid) {
		getSession().delete(bid);
	}

}
