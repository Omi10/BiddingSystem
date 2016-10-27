package om.services;

import java.util.List;
import java.util.Map;

import inti.ws.spring.exception.client.BadRequestException;
import om.models.ItemModel;

public interface ItemService {

	public List<ItemModel> getItems(Map<String, String[]> parameters);

	public ItemModel getItem(int itemId) throws BadRequestException;

	public ItemModel addItem(ItemModel item,int userId);

	public ItemModel updateItem(int itemId, ItemModel ittem);

	public void deleteItem(int itemId);

	public List<ItemModel> getUserItems(int userId) throws BadRequestException;

}
