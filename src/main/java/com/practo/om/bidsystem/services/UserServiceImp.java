package com.practo.om.bidsystem.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.om.bidsystem.dao.UserDao;
import com.practo.om.bidsystem.entities.User;

import inti.ws.spring.exception.client.BadRequestException;

@Service
//@Transactional
public class UserServiceImp implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<User> getAllUsers() {
		List<User> users = userDao.getAll();
		return users;
	}

	@Override
	public User addUser(String name, String email) {
		User user = new User(name, email);
		userDao.save(user);
		return user;
	}

	
	@Override
	public User getUserById(int userId) throws BadRequestException {
		if (userId < 0)
			throw new BadRequestException("Invalid userId parameters");
		User user = userDao.getUser(userId);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userDao.getByEmail(email);
		return user;
	}

}
