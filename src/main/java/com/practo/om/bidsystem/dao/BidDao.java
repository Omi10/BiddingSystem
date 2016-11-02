package com.practo.om.bidsystem.dao;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.om.bidsystem.entities.Bid;
import com.practo.om.bidsystem.models.ResultModel;

public interface BidDao {

	public List<Bid> getItemBids(int itemId) throws JDBCConnectionException;

	public Bid getBid(int bidId);

	public void save(Bid bid);

	public void update(Bid Bid);

	public void delete(Bid bid);

	public ResultModel getBidResult(int itemId) throws JDBCConnectionException;

	public List<Bid> getUserBids(int userId) throws JDBCConnectionException;
	
	public List<Bid> getUserBidsResultOut(int userId) throws JDBCConnectionException;

}
