package com.practo.om.bidsystem.services;

import java.util.List;

import com.practo.om.bidsystem.entities.User;

import inti.ws.spring.exception.client.BadRequestException;

public interface UserService {

	public List<User> getAllUsers();

	public User addUser(String name, String email);


	public User getUserById(int userId) throws BadRequestException;

	public User getUserByEmail(String email);
}
