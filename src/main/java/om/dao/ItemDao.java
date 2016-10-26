package om.dao;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import om.entities.Item;

public interface ItemDao {

	public void save(Item item);

	public List<Item> getItems();

	public Item getItem(int ItemId);

	public void update(Item Item);

	public void delete(Item Item);

	public List<Item> getItemsByCategory(int categoryId);

	public List<Item> getUserItems(int userId) throws JDBCConnectionException;
}