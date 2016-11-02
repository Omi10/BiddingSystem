package com.practo.om.bidsystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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

import com.practo.om.bidsystem.entities.User;
import com.practo.om.bidsystem.models.ItemModel;
import com.practo.om.bidsystem.services.UserService;

import inti.ws.spring.exception.client.BadRequestException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes={TestDatabaseConfig.class})
@TestPropertySource(locations="classpath:application.properties")
@ComponentScan(basePackages = {"com.practo.om.bidsystem"})
public class UserTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void getAllUsersTest() throws BadRequestException {
		List<User> users = userService.getAllUsers();
		int oldSize = users.size();
 
		userService.addUser("Test User", "test@gmail.com");
		
		users = userService.getAllUsers();
		int newSize = users.size();
		Assert.assertEquals(newSize, oldSize + 1);

	}
	
	
	@Test
	@Transactional
	public void getUserByIdTest() throws BadRequestException {
		User user = userService.getUserById(1);
		Assert.assertEquals(user.getName(), "om");
	}
	
	@Test
	@Transactional
	public void getUserByEmailTest() throws BadRequestException {
		User user = userService.getUserByEmail("nirankariom@gmail.com");
		Assert.assertEquals(user.getName(), "om");
	}
	
	
	@Test
	@Transactional
	public void addUserTest() throws BadRequestException {
		List<User> users = userService.getAllUsers();
		int oldSize = users.size();
 
		userService.addUser("User", "user@gmail.com");
		
		users = userService.getAllUsers();
		int newSize = users.size();
		Assert.assertEquals(newSize, oldSize + 1);
	}
	

}
