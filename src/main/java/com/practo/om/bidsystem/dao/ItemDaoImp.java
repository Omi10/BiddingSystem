package com.practo.om.bidsystem.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.om.bidsystem.entities.Category;
import com.practo.om.bidsystem.entities.Item;

@Repository
@Transactional
public class ItemDaoImp implements ItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Item Item) {
		getSession().save(Item);
	}

	@Override
	public Item getItem(int ItemId) {
		return getSession().get(Item.class, ItemId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItems() throws JDBCConnectionException {
		return getSession().createQuery("from Item").list();

	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getUserItems(int userId) throws JDBCConnectionException {
		Query query = getSession().createQuery("from Item where user.id=:userId");
		query.setParameter("userId", userId);
		List<Item> result = query.list();
		return result;

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItemsForUser(int userId) throws JDBCConnectionException {
		Date now = new Date();
		Query query = getSession().createQuery("from Item where user.id!=:userId and endTime>:currentTime");
		query.setParameter("currentTime",now);
		query.setParameter("userId", userId);
		List<Item> result = query.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItemsByFilter(int userId, int categoryId) throws JDBCConnectionException {
		Date now = new Date();
		Query query = getSession().createQuery("from Item where user.id!=:userId and category.id=:categoryId and endTime>:currentTime");
		query.setParameter("currentTime",now);
		query.setParameter("userId", userId);
		query.setParameter("categoryId", categoryId);
		List<Item> result = query.list();
		return result;
	}
	
	@Override
	public List<Item> getItemsByCategory(int categoryId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Item.class);
		DetachedCriteria categoryCriteria = criteria.createCriteria("category")
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		; // Item class has category object
		categoryCriteria.add(Restrictions.eq("id", categoryId)); // category
																	// entity
																	// has id
																	// field
		Criteria executableCriteria = categoryCriteria.getExecutableCriteria(getSession());
		return executableCriteria.list();
	}

	@Override
	public void update(Item Item) {
		getSession().update(Item);
	}


}