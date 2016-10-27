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

	@Override
	public List<ItemModel> getItems(Map<String, String[]> parameters) {
		List<Item> items = null;
		if (parameters.size() == 0) {
			items = (List<Item>) itemDao.getItems();
		} else {
			int idValue = new Integer(parameters.get("categoryId")[0]);
			System.out.println("Idvalue ==" + idValue);
			items = (List<Item>) itemDao.getItemsByCategory(idValue);
		}
		List<ItemModel> itemModels = mUtility.itemsToItemModels(items);
		return itemModels;
	}

	@Override
	public ItemModel getItem(int itemId) throws BadRequestException {
		if (itemId < 0)
			throw new BadRequestException("Invalid ItemId parameters");
		Item item = itemDao.getItem(itemId);
		ItemModel itemModel = mUtility.itemToItemModel(item);
		return itemModel;
	}

	@Override
	public List<ItemModel> getUserItems(int userId) throws BadRequestException {
		if (userId < 0)
			throw new BadRequestException("Invalid User");

		List<Item> items = itemDao.getUserItems(userId);
		List<ItemModel> itemModels = mUtility.itemsToItemModels(items);
		return itemModels;
	}

	@Override
	public ItemModel addItem(ItemModel itemModel, int userId) {

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
		return itemModel;
	}

	@Override
	public void deleteItem(int itemId) {
		Item item = new Item();
		item.setId(itemId);
		itemDao.delete(item);
	}

}
