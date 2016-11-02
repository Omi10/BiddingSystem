package com.practo.om.bidsystem.dao;

import java.util.List;

import com.practo.om.bidsystem.entities.User;

public interface UserDao {

	public void save(User user);

	public List<User> getAll();

	public User getByEmail(String email);

	public User getUser(int id);

}