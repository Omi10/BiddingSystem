package com.practo.om.bidsystem.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.om.bidsystem.dao.BidDao;
import com.practo.om.bidsystem.dao.ItemDao;
import com.practo.om.bidsystem.entities.Bid;
import com.practo.om.bidsystem.entities.Item;
import com.practo.om.bidsystem.models.BidModel;
import com.practo.om.bidsystem.models.MakeBidModel;
import com.practo.om.bidsystem.models.ResultModel;
import com.practo.om.bidsystem.utilities.Logger;
import com.practo.om.bidsystem.utilities.MappingUtility;

import inti.ws.spring.exception.client.BadRequestException;

@Service
public class BidServiceImp implements BidService {

	@Autowired
	MappingUtility mUtility;

	@Autowired
	BidDao bidDao;

	@Autowired
	ItemDao itemDao;

	private static final Logger LOG = Logger.getInstance(BidServiceImp.class);

	@Override
	public List<BidModel> getItemBids(int itemId) throws BadRequestException {
		if (itemId < 0) {
			LOG.error("Invalid Item Id passed", new BadRequestException());
			throw new BadRequestException("Invalid parameters");
		}
		LOG.info("Requested all Bids with ItemId=" + itemId);
		List<Bid> bids = (List<Bid>) bidDao.getItemBids(itemId);
		List<BidModel> bidModels = mUtility.bidsToBidModels(bids);
		return bidModels;
	}

	@Override
	public List<BidModel> getUserBids(int userId) throws BadRequestException {
		if (userId < 0) {
			LOG.error("Invalid user Id passed", new BadRequestException());
			throw new BadRequestException("Invalid parameters");
		}
		List<Bid> bids = (List<Bid>) bidDao.getUserBids(userId);
		List<BidModel> bidModels = mUtility.bidsToBidModels(bids);
		return bidModels;
	}
	
	
	@Override
	public List<BidModel> getUserBidsResultOut(int userId) throws BadRequestException {
		if (userId < 0) {
			LOG.error("Invalid user Id passed", new BadRequestException());
			throw new BadRequestException("Invalid parameters");
		}
		List<Bid> bids = (List<Bid>) bidDao.getUserBidsResultOut(userId);
		List<BidModel> bidModels = mUtility.bidsToBidModels(bids);
		return bidModels;
	}

	@Override
	public BidModel getBid(int bidId) {
		Bid bid = (Bid) bidDao.getBid(bidId);
		BidModel bidModel = mUtility.bidToBidModel(bid);
		return bidModel;
	}

	@Override
	public MakeBidModel addBid(int itemId, MakeBidModel makeBidModel) throws BadRequestException {
		Item item = itemDao.getItem(itemId);
		
		if (makeBidModel.getBidValue() < item.getInitialPrice()) {
			LOG.error("Invalid  bid amount ", new BadRequestException());
			throw new BadRequestException("Invalid  bid amount");
		}

//		if (makeBidModel.getBidderId() == item.getUser().getId()) {
//			LOG.error("Owner cannot bid for his item ", new BadRequestException());
//			throw new BadRequestException("Invalid Bid");
//		}

		Bid bid = mUtility.makeBidModelToBid(itemId, makeBidModel);
		bidDao.save(bid);
		return makeBidModel;
	}

	@Override
	public BidModel updateBid(int bidId, BidModel bidModel) throws BadRequestException {
		Bid bid = bidDao.getBid(bidId);
		Item item = itemDao.getItem(bid.getItem().getId());
		Date now=new Date();
		if (bidModel.getBidValue() < item.getInitialPrice()) {
			LOG.error("Invalid  bid amount ", new BadRequestException());
			throw new BadRequestException("Invalid  bid amount");
		}
		
		if (now.after(item.getEndTime())) {
			LOG.error("Bidding Time is over for this item", new BadRequestException());
			throw new BadRequestException("Bidding Time is over for this item");
		}	
		bid.setBidAmount(bidModel.getBidValue());
		bid.setBidTime(now);
		System.out.println("PATCH done");
		bidDao.update(bid);
		return bidModel;

	}

	@Override
	public ResultModel getBidResult(int itemId) throws BadRequestException {
		Item item = itemDao.getItem(itemId);
		Date now = new Date();
		/*if (item.getEndTime().after(now)) {
			LOG.error("Results are not out yet ", new BadRequestException());
			throw new BadRequestException("Results are not out yet");
		}*/
		ResultModel resultModel = bidDao.getBidResult(itemId);
		return resultModel;
	}

	@Override
	public void deleteBid(int bidId) throws BadRequestException{
		Bid bid=bidDao.getBid(bidId);
		Item item = bid.getItem();
		Date now = new Date();
		if (item.getEndTime().before(now)) {
			LOG.error("Bid Results are out cannot delete the bid now", new BadRequestException());
			throw new BadRequestException("Bid Results are out cannot delete the bid now");
	    }
		//Bid bid = new Bid();
		//bid.setId(bidId);
		bidDao.delete(bid);
		LOG.info("Deleted the Bid with Id=" + bidId);
	}

}
