package om.models;

public class BidModel {

	private int bidId;
	private String madeBy;
	private String item;
	private int bidValue;
	private boolean isLatestBid;
	private boolean isWon;
	private String bidTime;
	private int startBidValue;
	private String description;
	private String bidEndTime;

	public String getDescription() {
		return description;
	}

	public String getBidEndTime() {
		return bidEndTime;
	}

	public void setBidEndTime(String bidEndTime) {
		this.bidEndTime = bidEndTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStartBidValue() {
		return startBidValue;
	}

	public void setStartBidValue(int startBidValue) {
		this.startBidValue = startBidValue;
	}

	public String getBidTime() {
		return bidTime;
	}

	public void setBidTime(String bidTime) {
		this.bidTime = bidTime;
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public String getMadeBy() {
		return madeBy;
	}

	public void setMadeBy(String madeBy) {
		this.madeBy = madeBy;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getBidValue() {
		return bidValue;
	}

	public void setBidValue(int bidValue) {
		this.bidValue = bidValue;
	}

	public boolean isLatestBid() {
		return isLatestBid;
	}

	public void setLatestBid(boolean isLatestBid) {
		this.isLatestBid = isLatestBid;
	}

	public boolean isWon() {
		return isWon;
	}

	public void setWon(boolean isWon) {
		this.isWon = isWon;
	}
}
