package com.practo.om.bidsystem.services;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.om.bidsystem.dao.CategoryDao;
import com.practo.om.bidsystem.dao.ItemDao;
import com.practo.om.bidsystem.dao.UserDao;
import com.practo.om.bidsystem.entities.Category;
import com.practo.om.bidsystem.entities.Item;
import com.practo.om.bidsystem.models.ItemModel;
import com.practo.om.bidsystem.utilities.Logger;
import com.practo.om.bidsystem.utilities.MappingUtility;

import inti.ws.spring.exception.client.BadRequestException;

@Service
@Transactional
public class ItemServiceImp implements ItemService {

	@Autowired
	MappingUtility mUtility;

	@Autowired
	ItemDao itemDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	UserDao userDao;

	private static final Logger LOG = Logger.getInstance(ItemServiceImp.class);

	@Override
	public List<ItemModel> getItemsByCategory(Map<String, String[]> parameters) {
		List<Item> items = null;
		if (parameters.size() == 0) {
			items = (List<Item>) itemDao.getItems();
		} else {
			int idValue = new Integer(parameters.get("categoryId")[0]);
			System.out.println("Idvalue ==" + idValue);
			items = (List<Item>) itemDao.getItemsByCategory(idValue);
		}
		LOG.info("Requested all items");
		List<ItemModel> itemModels = mUtility.itemsToItemModels(items);
		return itemModels;
	}

	@Override
	public List<ItemModel> getAllItems() {
		List<Item> items = itemDao.getItems();
		LOG.info("Requested all items");
		List<ItemModel> itemModels = mUtility.itemsToItemModels(items);
		return itemModels;
	}
	
	
	@Override
	public List<Category> getAllCategories() {
		List<Category> categories = categoryDao.getCategories();
		LOG.info("Requested all categories");
		return categories;
	}
	
	@Override
	public Category addCategory(String categoryName) {
           Category category=new Category(categoryName);
           categoryDao.save(category);
           return category;
	}
	

	@Override
	public ItemModel getItem(int itemId) throws BadRequestException {
		if (itemId < 0) {
			LOG.error("Invalid Item Id passed", new BadRequestException());
			throw new BadRequestException("Invalid parameters");
		}
		LOG.info("Requested Item with Id=" + itemId);
		Item item = itemDao.getItem(itemId);
		ItemModel itemModel = mUtility.itemToItemModel(item);
		return itemModel;
	}

	@Override
	public List<ItemModel> getUserItems(int userId) throws BadRequestException {
		if (userId < 0) {
			LOG.error("Invalid UserId passed", new BadRequestException());
			throw new BadRequestException("Invalid parameters");
		}
		List<Item> items = itemDao.getUserItems(userId);
		List<ItemModel> itemModels = mUtility.itemsToItemModels(items);
		return itemModels;
	}
	
	
	@Override
	public List<ItemModel> getItemsForUser(int userId) throws BadRequestException {
		if (userId < 0) {
			LOG.error("Invalid UserId passed", new BadRequestException());
			throw new BadRequestException("Invalid parameters");
		}
		List<Item> items = itemDao.getItemsForUser(userId);
		List<ItemModel> itemModels = mUtility.itemsToItemModels(items);
		return itemModels;
	}
	
	
	@Override
	public List<ItemModel> getItemsByFilter(int userId, int categoryId) throws BadRequestException {
		List<Item> items = itemDao.getItemsByFilter(userId,categoryId);
		List<ItemModel> itemModels = mUtility.itemsToItemModels(items);
		return itemModels;
	}

	@Override
	public ItemModel addItem(ItemModel itemModel, int userId) throws BadRequestException {

		if (userId < 0) {
			LOG.error("Invalid UserId passed", new BadRequestException());
			throw new BadRequestException("Invalid parameters");
		}
		Item item = mUtility.itemModelToItem(itemModel);
		item.setUser(userDao.getUser(userId));
		Category category=categoryDao.getCategory(itemModel.getCategoryId());
		item.setCategory(category);
		itemDao.save(item);
		return itemModel;
	}

	@Override
	public ItemModel updateItem(int itemId, ItemModel itemModel) {
		Item item = itemDao.getItem(itemId);
		mUtility.updateItem(item, itemModel);
		itemDao.update(item);
		LOG.info("Updated the Item with Id=" + itemId);
		return itemModel;
	}


}
