package com.practo.om.bidsystem.dao;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.om.bidsystem.entities.Category;
import com.practo.om.bidsystem.entities.Item;

public interface CategoryDao {

	public List<Category> getCategories() throws JDBCConnectionException;
	
	public Category getCategory(int categoryId);
	
	public void save(Category category);

}
