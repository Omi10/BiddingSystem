package com.practo.om.bidsystem.services;

import java.util.List;

import com.practo.om.bidsystem.models.BidModel;
import com.practo.om.bidsystem.models.MakeBidModel;
import com.practo.om.bidsystem.models.ResultModel;

import inti.ws.spring.exception.client.BadRequestException;

public interface BidService {

	public List<BidModel> getItemBids(int itemId) throws BadRequestException;

	public BidModel getBid(int bidId);

	public MakeBidModel addBid(int itemId, MakeBidModel makeBidModel) throws BadRequestException;

	public BidModel updateBid(int bidId, BidModel bid) throws BadRequestException;

	public void deleteBid(int BidId)  throws BadRequestException ;

	public ResultModel getBidResult(int itemId) throws BadRequestException;

	public List<BidModel> getUserBids(int userId) throws BadRequestException;
	
	public List<BidModel> getUserBidsResultOut(int userId) throws BadRequestException;

}
