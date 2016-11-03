package com.practo.om.bidsystem.services;

import java.util.List;
import java.util.Map;

import com.practo.om.bidsystem.entities.Category;
import com.practo.om.bidsystem.models.ItemModel;

import inti.ws.spring.exception.client.BadRequestException;

public interface ItemService {
	
	public List<ItemModel> getAllItems();

	public ItemModel getItem(int itemId) throws BadRequestException;

	public ItemModel addItem(ItemModel item, int userId) throws BadRequestException;

	public List<ItemModel> getUserItems(int userId) throws BadRequestException;
	
	public List<ItemModel> getItemsForUser(int userId) throws BadRequestException;
	
	public List<Category> getAllCategories() ;
	
	public List<ItemModel> getItemsByFilter(int userId, int categoryId) throws BadRequestException;
	
	public Category addCategory(String categoryName);

}
