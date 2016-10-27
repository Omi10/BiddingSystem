package om.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import inti.ws.spring.exception.client.BadRequestException;
import om.entities.User;
import om.models.ItemModel;
import om.services.ItemService;
import om.utilities.Logger;

@RestController
public class ItemController {

	@Autowired
	ItemService itemService;

	private static final Logger LOG = Logger.getInstance(ItemController.class);

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<ItemModel> getAllItems(HttpServletRequest request) throws Exception {
		Map<String, String[]> parameters = request.getParameterMap();
		LOG.info("Get all Item requests");
		List<ItemModel> itemModels = null;
		itemModels = itemService.getItems(parameters);
		return itemModels;
	}

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ItemModel uploadItem(@RequestBody ItemModel itemModel, HttpSession session) throws BadRequestException {
		User user=(User) session.getAttribute("user");
		int userId=user.getId();
		return itemService.addItem(itemModel,userId);

	}

	@RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteItem(@PathVariable int itemId) {
		itemService.deleteItem(itemId);
	}

	@RequestMapping(value = "/item/{itemId}", method = RequestMethod.PATCH)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ItemModel updateItem(@PathVariable int itemId, @RequestBody ItemModel itemModel) {
		return itemService.updateItem(itemId, itemModel);
	}

	@RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ItemModel getItem(@PathVariable int itemId) throws BadRequestException {
		return itemService.getItem(itemId);
	}

	@RequestMapping(value = "/user/{userId}/items", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<ItemModel> getUserItems(@PathVariable int userId,HttpSession session) throws BadRequestException {
		User user=(User) session.getAttribute("user");
		userId=user.getId();
		return itemService.getUserItems(userId);
	}

}
