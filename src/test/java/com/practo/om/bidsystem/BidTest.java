package com.practo.om.bidsystem;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.om.bidsystem.models.BidModel;
import com.practo.om.bidsystem.models.ItemModel;
import com.practo.om.bidsystem.models.MakeBidModel;
import com.practo.om.bidsystem.services.BidService;
import com.practo.om.bidsystem.services.ItemService;

import inti.ws.spring.exception.client.BadRequestException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes={TestDatabaseConfig.class})
@ComponentScan(basePackages = {"com.practo.om.bidsystem"})
public class BidTest {
	
	@Autowired
	ItemService itemService;
	
	
	@Autowired
	BidService bidService;
	
	@Test
	public void getItemBidsTest() throws BadRequestException {
		List<BidModel> bidModels = bidService.getItemBids(new Integer(1));
		int oldSize = bidModels.size();
		MakeBidModel bidModelToSave = new MakeBidModel();
		bidModelToSave.setBidderId(2);
		bidModelToSave.setBidValue(10000);
		bidService.addBid(1,bidModelToSave);	
		bidModels = bidService.getItemBids(1);
		int newSize = bidModels.size();
		Assert.assertEquals(newSize, oldSize + 1);
	}
	
	@Test
	public void updateBidTest() throws BadRequestException {
		BidModel oldBid = bidService.getBid(87);
		BidModel updateBid=new BidModel();
		updateBid.setBidId(87);
		updateBid.setBidValue(oldBid.getBidValue()+5);
		bidService.updateBid(87, updateBid);
		
		BidModel newBid=bidService.getBid(87);
		Assert.assertEquals(oldBid.getBidValue()+5,newBid.getBidValue());
	}
	
	@Test
	public void getUserBidsTest() throws BadRequestException {
		int userId=1;
		int itemId=5;
		
		List<BidModel> bidModels = bidService.getUserBids(userId);
		int oldSize = bidModels.size();
		
		MakeBidModel bidModelToSave = new MakeBidModel();
		bidModelToSave.setBidderId(userId);
		bidModelToSave.setBidValue(10000);
		
		bidService.addBid(itemId,bidModelToSave);
		
		bidModels = bidService.getUserBids(userId);
		int newSize = bidModels.size();
		Assert.assertEquals(newSize, oldSize + 1);
	}
	
	
	@Test
	public void deleteBidTest() throws BadRequestException {
		int itemId=4;
		List<BidModel> bidModels = bidService.getItemBids(itemId);
		int oldSize = bidModels.size();
		
		bidService.deleteBid(bidModels.get(0).getBidId());
		
		bidModels = bidService.getItemBids(itemId);
		int newSize = bidModels.size();
		
		Assert.assertEquals(newSize, oldSize - 1);
	}
	
	@Test(expected = BadRequestException.class)
	public void getItemBidsInvalidTest() throws BadRequestException {
		bidService.getItemBids(-1);
	}
	
	
	@Test(expected = BadRequestException.class)
	public void getUserBidsInvalidTest() throws BadRequestException {
		bidService.getUserBids(-1);
	}
	
	@Test(expected = BadRequestException.class)
	public void getUserBidsResultOutInvalidTest() throws BadRequestException {
		bidService.getUserBidsResultOut(-1);
	}
	
	@Test(expected = BadRequestException.class)
	public void addBidInvalidTest() throws BadRequestException {
		int itemId=1;
		int userId=5;
		
		ItemModel itemModel=itemService.getItem(1);
		
		MakeBidModel bidModelToSave = new MakeBidModel();
		bidModelToSave.setBidderId(userId);
		bidModelToSave.setBidValue(itemModel.getStartBidAmount()-1);
		
		bidService.addBid(itemId,bidModelToSave);
	}
	
	

}
