package com.practo.om.bidsystem.entities;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user", catalog = "bid_system")
public class User implements java.io.Serializable {

	private Integer id;
	private String name;
	private String email;
	private String contactNo;
	private Set<Bid> bids = new HashSet<Bid>(0);
	private Set<Item> items = new HashSet<Item>(0);
	private Set<Wallet> wallets = new HashSet<Wallet>(0);

	public User() {
	}

	public User(String name, String email, String contactNo) {
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public User(String name, String email, String contactNo, String address, Set<Bid> bids, Set<Item> items,
			Set<Wallet> wallets) {
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.bids = bids;
		this.items = items;
		this.wallets = wallets;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "contact_no")
	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/*
	 * @OneToMany(fetch=FetchType.LAZY, mappedBy="user") public Set<Bid>
	 * getBids() { return this.bids; }
	 * 
	 * public void setBids(Set<Bid> bids) { this.bids = bids; }
	 * 
	 * @OneToMany(fetch=FetchType.EAGER, mappedBy="user") public Set<Item>
	 * getItems() { return this.items; }
	 * 
	 * public void setItems(Set<Item> items) { this.items = items; }
	 * 
	 * @OneToMany(fetch=FetchType.LAZY, mappedBy="user") public Set<Wallet>
	 * getWallets() { return this.wallets; }
	 * 
	 * public void setWallets(Set<Wallet> wallets) { this.wallets = wallets; }
	 */
}
