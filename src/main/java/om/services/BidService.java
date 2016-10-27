package om.services;

import java.util.List;

import om.models.BidModel;
import om.models.MakeBidModel;
import om.models.ResultModel;

public interface BidService {

	public List<BidModel> getBids(int itemId);

	public BidModel getBid(int bidId);

	public MakeBidModel addBid(int itemId, MakeBidModel makeBidModel);

	public BidModel updateBid(int bidId, BidModel bid);

	public void deleteBid(int BidId);

	public ResultModel getBidResult(int itemId);

	public List<BidModel> getUserBids(int userId);

}
