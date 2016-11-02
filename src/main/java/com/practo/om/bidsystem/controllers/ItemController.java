package com.practo.om.bidsystem.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.practo.om.bidsystem.entities.Category;
import com.practo.om.bidsystem.entities.User;
import com.practo.om.bidsystem.models.ItemModel;
import com.practo.om.bidsystem.services.ItemService;

import inti.ws.spring.exception.client.BadRequestException;

@RestController
public class ItemController {

	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<ItemModel> getAllItems(HttpServletRequest request) throws Exception {
		Map<String, String[]> parameters = request.getParameterMap();
		List<ItemModel> itemModels = null;
		itemModels = itemService.getAllItems();
		return itemModels;
	}
	
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<Category> getAllCategories() throws Exception {
		List<Category> categories=itemService.getAllCategories();
		return categories;
	}

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ItemModel uploadItem(@RequestBody ItemModel itemModel, HttpSession session) throws BadRequestException {
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		return itemService.addItem(itemModel, userId);
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
	public List<ItemModel> getUserItems(@PathVariable int userId, HttpSession session) throws BadRequestException {
		User user = (User) session.getAttribute("user");
		userId = user.getId();
		return itemService.getUserItems(userId);
	}
	
	
	@RequestMapping(value = "/items/allCategories", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<ItemModel> getItemsForUser(HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		List<ItemModel> itemModels = null;
		itemModels = itemService.getItemsForUser(userId);
		
		return itemModels;
	}
	
	@RequestMapping(value = "/items/filter", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<ItemModel> getItemsByFilter(HttpSession session,String categoryId) throws Exception {
		User user = (User) session.getAttribute("user");
		Integer userId = user.getId();
		List<ItemModel> itemModels = null;
		if(categoryId.equals("0"))
			itemModels = itemService.getItemsForUser(userId);
		else 
		    itemModels = itemService.getItemsByFilter(userId,new Integer(categoryId));	
		
		return itemModels;
	}

}
