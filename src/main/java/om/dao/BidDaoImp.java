package om.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import om.entities.Bid;
import om.entities.Item;
import om.models.ResultModel;

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
	public List<Bid> getBids() throws JDBCConnectionException {
		return getSession().createQuery("from Bid").list();
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
	public ResultModel getBidResult(int itemId) throws JDBCConnectionException {

		Item item = itemDao.getItem(itemId);
		ResultModel resultModel = new ResultModel();
		Date now = new Date();
		Date bidEndTime = item.getEndTime();

		if (bidEndTime.after(now)) {
			resultModel.setResultOut(false);
			System.out.println("Nope");
			return resultModel;
		}
		
		Integer maxBid=null;		
		
		Query query1 = getSession() .createQuery("select max(bidAmount) from Bid where item.id=:itemId and bidTime<:bidEndTime");
		query1.setParameter("itemId", itemId);
		query1.setParameter("bidEndTime",item.getEndTime());
		List<Integer> resultSet1=query1.list();
		maxBid=resultSet1.get(0);

		Bid resultBid=null;
		if(maxBid!=null)
		{
			Query query2=getSession().createQuery("from Bid b where bidAmount=:maxBid and item.id=:itemId");
			query2.setParameter("itemId", itemId);
			query2.setParameter("maxBid", maxBid);
			List<Bid> resultSet2 = query2.list();
			resultBid=resultSet2.get(0);
		}
		if(resultBid!=null)
		{
		    resultModel.setFinalBidAmount(resultBid.getBidAmount());
		    resultModel.setResultOut(true);
		    resultModel.setWinner(resultBid.getUser().getName());
		    resultModel.setStartBidValue(resultBid.getItem().getInitialPrice());
		    resultModel.setBidTime(resultBid.getBidTime());
		    System.out.println(resultModel.getFinalBidAmount());
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
