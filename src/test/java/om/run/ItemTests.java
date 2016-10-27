package om.run;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import inti.ws.spring.exception.client.BadRequestException;
import om.models.ItemModel;
import om.services.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes={TestDatabaseConfig.class})
public class ItemTests {

	@Autowired
	ItemService itemService;

	@Test
	public void addItemTest() throws BadRequestException {
		List<ItemModel> itemModel = itemService.getAllItems();
		int oldSize = itemModel.size();

		ItemModel itemModelToSave = new ItemModel();
		itemModelToSave.setName("Fridge");
		itemModelToSave.setOwner("om");
		itemModelToSave.setDescription("2014 purchased");
		itemModelToSave.setBidType("Closed");
		itemModelToSave.setStartBidAmount(5000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date dt = new Date();
		itemModelToSave.setBidEndTime(sdf.format(dt));
		ItemModel itemSaved = itemService.addItem(itemModelToSave, 1);

		itemModel = itemService.getAllItems();
		int newSize = itemModel.size();
		Assert.assertEquals(newSize, oldSize + 1);
	}

	@Test
	public void getItemTest() throws BadRequestException {
		ItemModel itemModel = itemService.getItem(1);
		Assert.assertTrue(itemModel.getItemId() != null);
	}

	// Constraint of Cascade delete (i.e. the item will not get deleted if there
	// are corresponding bids to it
	/*
	 * @Test public void deleteItemTest() throws BadRequestException {
	 * List<ItemModel> itemModel=itemService.getAllItems(); int oldSize =
	 * itemModel.size(); itemService.deleteItem(55);
	 * itemModel=itemService.getAllItems(); int newSize =itemModel.size();
	 * Assert.assertEquals(newSize,oldSize-1); }
	 */

	@Test
	public void getAllItemsTest() throws BadRequestException {
		List<ItemModel> itemModel = itemService.getAllItems();
		int oldSize = itemModel.size();

		ItemModel itemModelToSave = new ItemModel();
		itemModelToSave.setName("Bike");
		itemModelToSave.setOwner("om");
		itemModelToSave.setDescription("2015 purchased");
		itemModelToSave.setBidType("Closed");
		itemModelToSave.setStartBidAmount(800000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date dt = new Date();
		itemModelToSave.setBidEndTime(sdf.format(dt));
		ItemModel itemSaved = itemService.addItem(itemModelToSave, 1);
		itemModel = itemService.getAllItems();
		int newSize = itemModel.size();
		Assert.assertEquals(newSize, oldSize + 1);

	}

}
