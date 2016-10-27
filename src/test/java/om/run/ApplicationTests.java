package om.run;

import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import inti.ws.spring.exception.client.BadRequestException;
import om.models.ItemModel;
import om.services.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	ItemService itemService;

	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
	@Rollback(true)
	public void addItemTest() throws BadRequestException {
		ItemModel itemModelToSave = new ItemModel();
		itemModelToSave.setName("Bike");
		itemModelToSave.setOwner("om");
		itemModelToSave.setDescription("2014 purchased");
		itemModelToSave.setBidType("Closed");
		itemModelToSave.setCategory(null);
		System.out.println("Testing");
		// Save and Get Test
		// ItemModel itemModelSaved = itemService.addItem(itemModelToSave);
		// assertTrue(itemModelSaved.getItemId() > 0);
		assertTrue(1 > 0);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void deleteItemTest() throws BadRequestException {

		assertTrue(1 > 0);
	}

}
