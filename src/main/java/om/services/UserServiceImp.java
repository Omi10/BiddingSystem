package om.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inti.ws.spring.exception.client.BadRequestException;
import om.dao.UserDao;
import om.entities.User;

@Service
//@Transactional
public class UserServiceImp implements UserService{

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
	public void deleteUser(int userId) {
		User user = new User();
		user.setId(userId);
		userDao.delete(user);
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
