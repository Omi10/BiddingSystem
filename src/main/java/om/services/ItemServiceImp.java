package om.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inti.ws.spring.exception.client.BadRequestException;
import om.dao.CategoryDao;
import om.dao.ItemDao;
import om.dao.UserDao;
import om.entities.Item;
import om.models.ItemModel;
import om.utilities.Logger;
import om.utilities.MappingUtility;

@Service
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
	public ItemModel addItem(ItemModel itemModel, int userId) throws BadRequestException {

		if (userId < 0) {
			LOG.error("Invalid UserId passed", new BadRequestException());
			throw new BadRequestException("Invalid parameters");
		}
		Item item = mUtility.itemModelToItem(itemModel);
		item.setUser(userDao.getUser(userId));
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

	@Override
	public void deleteItem(int itemId) {
		Item item = new Item();
		item.setId(itemId);
		itemDao.delete(item);
		LOG.info("Deleted the Item with Id=" + itemId);
	}

}
