package com.practo.om.bidsystem.entities;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "item", catalog = "bid_system")
public class Item implements java.io.Serializable {

	private Integer id;
	private Category category;
	private User user;
	private String item;
	private String description;
	private String imageUrl;
	private int initialPrice;
	private Date startTime;
	private Date endTime;
	private Boolean bidType;
	private Integer minBalance;
	private Set<Bid> bids = new HashSet<Bid>(0);

	public Item() {
	}

	public Item(String item, int initialPrice) {
		this.item = item;
		this.initialPrice = initialPrice;
	}

	public Item(Category category, User user, String item, String description, String imageUrl, int initialPrice,
			Date startTime, Date endTime, Boolean bidType, Integer minBalance, Set<Bid> bids) {
		this.category = category;
		this.user = user;
		this.item = item;
		this.description = description;
		this.imageUrl = imageUrl;
		this.initialPrice = initialPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bidType = bidType;
		this.minBalance = minBalance;
		this.bids = bids;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "item", nullable = false)
	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "image_url")
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "initial_price", nullable = false)
	public int getInitialPrice() {
		return this.initialPrice;
	}

	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "bid_type")
	public Boolean getBidType() {
		return this.bidType;
	}

	public void setBidType(Boolean bidType) {
		this.bidType = bidType;
	}

	@Column(name = "min_balance")
	public Integer getMinBalance() {
		return this.minBalance;
	}

	public void setMinBalance(Integer minBalance) {
		this.minBalance = minBalance;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
	public Set<Bid> getBids() {
		return this.bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

}
