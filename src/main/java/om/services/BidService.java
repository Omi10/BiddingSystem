package om.services;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;
import om.models.BidModel;
import om.models.MakeBidModel;
import om.models.ResultModel;

public interface BidService {

	public List<BidModel> getBids(int itemId) throws BadRequestException;

	public BidModel getBid(int bidId);

	public MakeBidModel addBid(int itemId, MakeBidModel makeBidModel) throws BadRequestException;

	public BidModel updateBid(int bidId, BidModel bid) throws BadRequestException;

	public void deleteBid(int BidId);

	public ResultModel getBidResult(int itemId) throws BadRequestException;

	public List<BidModel> getUserBids(int userId) throws BadRequestException;

}
