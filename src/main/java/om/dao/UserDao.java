package om.dao;

import java.util.List;

import om.entities.User;

public interface UserDao {

	public void save(User user);

	public void delete(User user);

	public void update(User user);

	public List<User> getAll();

	public User getByEmail(String email);

	public User getUser(int id);

}