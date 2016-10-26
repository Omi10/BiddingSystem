package om.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import om.entities.Category;

@Repository
@Transactional
public class CategoryDaoImp implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Category> getCategories() {
		return getSession().createQuery("from Category").list();
		// getSession().getNamedQuery("sdkfjsdkj").setParameter("dfdf",
		// 1).setParameter("dfd", 1)
	}
}
