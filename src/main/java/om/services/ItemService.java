package om.services;

import java.util.List;
import java.util.Map;

import inti.ws.spring.exception.client.BadRequestException;
import om.models.ItemModel;

public interface ItemService {

	public List<ItemModel> getItemsByCategory(Map<String, String[]> parameters);

	public List<ItemModel> getAllItems();

	public ItemModel getItem(int itemId) throws BadRequestException;

	public ItemModel addItem(ItemModel item, int userId) throws BadRequestException;

	public ItemModel updateItem(int itemId, ItemModel ittem);

	public void deleteItem(int itemId);

	public List<ItemModel> getUserItems(int userId) throws BadRequestException;

}
