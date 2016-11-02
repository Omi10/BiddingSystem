package com.practo.om.bidsystem.models;

import java.util.Date;

public class ResultModel {

	private String winner;
	private int finalBidAmount;
	private Date bidTime;
	private int startBidValue;
	private boolean isResultOut;
	private int totalBids;

	
	public int getTotalBids() {
		return totalBids;
	}

	public void setTotalBids(int totalBids) {
		this.totalBids = totalBids;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getFinalBidAmount() {
		return finalBidAmount;
	}

	public void setFinalBidAmount(int finalBidAmount) {
		this.finalBidAmount = finalBidAmount;
	}

	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	public int getStartBidValue() {
		return startBidValue;
	}

	public void setStartBidValue(int startBidValue) {
		this.startBidValue = startBidValue;
	}

	public boolean isResultOut() {
		return isResultOut;
	}

	public void setResultOut(boolean isResultOut) {
		this.isResultOut = isResultOut;
	}

}
