package om.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import om.dao.BidDao;
import om.entities.Bid;
import om.models.BidModel;
import om.models.MakeBidModel;
import om.models.ResultModel;
import om.utilities.MappingUtility;

@Service

public class BidServiceImp implements BidService {

	@Autowired
	MappingUtility mUtility;

	@Autowired
	BidDao bidDao;

	@Override
	public List<BidModel> getBids(int itemId) {
		List<Bid> bids = (List<Bid>) bidDao.getBids();
		List<BidModel> bidModels = mUtility.bidsToBidModels(bids);
		return bidModels;
	}

	@Override
	public List<BidModel> getUserBids(int userId) {
		List<Bid> bids = (List<Bid>) bidDao.getUserBids(userId);
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
	public MakeBidModel addBid(int itemId, MakeBidModel makeBidModel) {
		Bid bid = mUtility.makeBidModelToBid(itemId, makeBidModel);
		bidDao.save(bid);
		//return mUtility.bidToBidModel(bid);
		return makeBidModel;
	}

	@Override
	public BidModel updateBid(int bidId, BidModel bidModel) {
		  Bid bid=bidDao.getBid(bidId); 
		  bid.setBidAmount(bidModel.getBidValue());
		  System.out.println("PATCH done");
		  bidDao.update(bid); 
		  return bidModel;
		 
	}

	@Override
	public ResultModel getBidResult(int itemId) {
		ResultModel resultModel = bidDao.getBidResult(itemId);
		return resultModel;
	}

	@Override
	public void deleteBid(int bidId) {
		Bid bid = new Bid();
		bid.setId(bidId);
		bidDao.delete(bid);
	}

}
