package com.practo.om.bidsystem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.om.bidsystem.entities.User;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(User user) {
		getSession().save(user);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		return getSession().createQuery("from User").list();
	}

	@Override
	public User getByEmail(String email) {
		return (User) getSession().createQuery("from User where email=:email").setParameter("email", email)
				.uniqueResult();
	}

	@Override
	public User getUser(int id) {
		return (User) getSession().get(User.class, id);
	}


}