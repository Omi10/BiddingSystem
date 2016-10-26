package om.services;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;
import om.entities.User;

public interface UserService {

	public List<User> getAllUsers();
	
	public User addUser(String name, String email);
	
	public void deleteUser(int userId);
	
	public User getUserById(int userId) throws BadRequestException;
	
	public User getUserByEmail(String email) ;
}
