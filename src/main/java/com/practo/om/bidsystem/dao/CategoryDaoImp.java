package com.practo.om.bidsystem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.om.bidsystem.entities.Category;
import com.practo.om.bidsystem.entities.User;

@Repository
@Transactional
public class CategoryDaoImp implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() throws JDBCConnectionException{
		return getSession().createQuery("from Category").list();
	}
	
	
	@Override
	public Category getCategory(int categoryId) {
		return getSession().get(Category.class, categoryId);
	}
	
	@Override
	public void save(Category category) {
		getSession().save(category);
	}
}
