package om.models;

import om.entities.Category;

public class ItemModel {
	private String name;
	private Category category;
	private String owner;
	private String description;
	private int startBidAmount;
	private String bidType;
	private String bidEndTime;
	private Integer ItemId;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
