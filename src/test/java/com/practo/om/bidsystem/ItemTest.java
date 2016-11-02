package com.practo.om.bidsystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.om.bidsystem.entities.Category;
import com.practo.om.bidsystem.entities.User;
import com.practo.om.bidsystem.models.ItemModel;
import com.practo.om.bidsystem.services.ItemService;
import com.practo.om.bidsystem.services.UserService;

import inti.ws.spring.exception.client.BadRequestException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = { TestDatabaseConfig.class })
@TestPropertySource(locations = "classpath:application.properties")
@ComponentScan(basePackages = { "com.practo.om.bidsystem" })
public class ItemTest {

	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;

	@Test
	public void addItemTest() throws BadRequestException {
		List<ItemModel> itemModel = itemService.getAllItems();
		int oldSize = itemModel.size();

		for (ItemModel item : itemModel) {
			System.out.println("Item name" + item.getName());
		}

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

	@Test
	public void getItemTest() throws BadRequestException {
		ItemModel itemModel = itemService.getItem(1);
		Assert.assertTrue(itemModel.getItemId() != null);
	}

	@Test(expected = BadRequestException.class)
	public void getItemInvalidTest() throws BadRequestException {
		ItemModel itemModel = itemService.getItem(-1);
		Assert.assertTrue(itemModel.getItemId() != null);
	}
	
	@Test
	public void getUserItemTest() throws BadRequestException {
		userService.addUser("U1","u1@gmail.com");
		int newUserId=userService.getUserByEmail("u1@gmail.com").getId();
		
		List<ItemModel> itemModels=itemService.getUserItems(newUserId);
		Assert.assertEquals(itemModels.size(), 0);
	}
	
	@Test(expected = BadRequestException.class)
	public void getUserItemInvalidTest() throws BadRequestException {
		 itemService.getUserItems(-1);
	}

	
	
	@Test
	public void getAllCategoriesTest() throws BadRequestException {
		List<Category> categories = itemService.getAllCategories();
		int oldSize = categories.size();

		itemService.addCategory("testCategory");

		categories = itemService.getAllCategories();
		int newSize = categories.size();
		Assert.assertEquals(newSize, oldSize + 1);
	}
	
	

}
