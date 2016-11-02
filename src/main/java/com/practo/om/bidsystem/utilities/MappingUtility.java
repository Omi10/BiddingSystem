package com.practo.om.bidsystem.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.practo.om.bidsystem.entities.Bid;
import com.practo.om.bidsystem.entities.Item;
import com.practo.om.bidsystem.entities.User;
import com.practo.om.bidsystem.models.BidModel;
import com.practo.om.bidsystem.models.ItemModel;
import com.practo.om.bidsystem.models.MakeBidModel;

@Service
public class MappingUtility {

	public ItemModel itemToItemModel(Item item) {
		ItemModel itemModel = new ItemModel();
		itemModel.setItemId(item.getId());
		itemModel.setName(item.getItem());

		if (item.getBidType() != null && item.getBidType() == false)
			itemModel.setBidType("Closed");
		else if (item.getBidType() != null && item.getBidType() == true)
			itemModel.setBidType("Open");
		if (item.getUser() != null)
			itemModel.setOwner(item.getUser().getName());
		if (item.getDescription() != null)
			itemModel.setDescription(item.getDescription());

		itemModel.setStartBidAmount(item.getInitialPrice());
		Date bidTime = item.getEndTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		itemModel.setBidEndTime(sdf.format(bidTime));
		return itemModel;
	}

	public List<ItemModel> itemsToItemModels(List<Item> items) {
		List<ItemModel> itemList = new ArrayList<>();
		for (Item i : items)
			itemList.add(itemToItemModel(i));
		return itemList;
	}

	public Item itemModelToItem(ItemModel itemModel) {
		Item item = new Item();
		item.setItem(itemModel.getName());
		item.setInitialPrice(itemModel.getStartBidAmount());
		item.setDescription(itemModel.getDescription());
		//item.setCategory(category);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date date = sdf.parse(itemModel.getBidEndTime());
			item.setEndTime(date);
		} catch (ParseException pe) {

		}
		return item;
	}

	public ItemModel updateItem(Item item, ItemModel itemModel) {
		item.setInitialPrice(itemModel.getStartBidAmount());
		return itemModel;
	}

	public BidModel bidToBidModel(Bid bid) {
		BidModel bidModel = new BidModel();
		bidModel.setBidId(bid.getId());
		bidModel.setItemId(bid.getItem().getId());
		bidModel.setItem(bid.getItem().getItem()); // Get the Item Entity object
													// then fetch the item name
													// from that object
		bidModel.setLatestBid(bid.isIsRecent());
		bidModel.setWon(bid.isBidWon());
		bidModel.setMadeBy(bid.getUser().getName());
		bidModel.setBidValue(bid.getBidAmount());
		bidModel.setStartBidValue(bid.getItem().getInitialPrice());
		bidModel.setDescription(bid.getItem().getDescription());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date bidTime = bid.getItem().getEndTime();
		bidModel.setBidEndTime(sdf.format(bidTime));

		//Date dt = new Date();
		//String currentTime = sdf.format(dt);
		//bidModel.setBidTime(currentTime);
		bidModel.setBidTime(bid.getBidTime().toString());
		return bidModel;
	}

	public List<BidModel> bidsToBidModels(List<Bid> bids) {
		List<BidModel> bidModels = new ArrayList<>();
		for (Bid bid : bids) {
			bidModels.add(bidToBidModel(bid));
		}
		return bidModels;
	}

	public Bid makeBidModelToBid(int itemId, MakeBidModel makeBidModel) {
		Bid bid = new Bid();
		User user = new User();
		Item item = new Item();
		item.setId(itemId);
		user.setId(makeBidModel.getBidderId());
		bid.setUser(user);
		bid.setItem(item);
		bid.setBidAmount(makeBidModel.getBidValue());
		Date dt = new Date();
		bid.setBidTime(dt);
		return bid;

	}

}
