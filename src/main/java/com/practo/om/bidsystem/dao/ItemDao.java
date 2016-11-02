package com.practo.om.bidsystem.dao;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.om.bidsystem.entities.Category;
import com.practo.om.bidsystem.entities.Item;

public interface ItemDao {

	public void save(Item item);

	public List<Item> getItems();

	public Item getItem(int ItemId);

	public void update(Item Item);


	public List<Item> getItemsByCategory(int categoryId);

	public List<Item> getUserItems(int userId) throws JDBCConnectionException;
	
	public List<Item> getItemsForUser(int userId) throws JDBCConnectionException;
	
	public List<Item> getItemsByFilter(int userId, int categoryId) throws JDBCConnectionException;
	
}