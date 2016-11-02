package com.practo.om.bidsystem.models;

import com.practo.om.bidsystem.entities.Category;

public class ItemModel {
	private String name;
	private int categoryId;
	private String owner;
	private String description;
	private int startBidAmount;
	private String bidType;
	private String bidEndTime;
	private Integer ItemId;

	
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getBidType() {
		return bidType;
	}

	public void setBidType(String bidType) {
		this.bidType = bidType;
	}

	public Integer getItemId() {
		return ItemId;
	}

	public void setItemId(Integer itemId) {
		ItemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStartBidAmount() {
		return startBidAmount;
	}

	public void setStartBidAmount(int startBidAmount) {
		this.startBidAmount = startBidAmount;
	}

	public String getBidEndTime() {
		return bidEndTime;
	}

	public void setBidEndTime(String bidEndTime) {
		this.bidEndTime = bidEndTime;
	}

}
