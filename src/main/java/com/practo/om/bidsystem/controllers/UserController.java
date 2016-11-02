package com.practo.om.bidsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practo.om.bidsystem.entities.User;
import com.practo.om.bidsystem.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAll() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public User create(String name, String email) {
		return userService.addUser(name, email);
	}

}
