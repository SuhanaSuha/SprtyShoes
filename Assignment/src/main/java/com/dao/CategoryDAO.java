package com.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.Category; 

@Repository
@Component
public class CategoryDAO {

	//@Autowired
    private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public Category getCategoryById(long id) {
		String strId = String.valueOf(id);
		List<Category> list = this.sessionFactory.getCurrentSession().createQuery("from Category where id=" + strId).list();
		if (list.size() > 0)
			return (Category) list.get(0);
		else
			return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void updateCategory(Category category) {
		String sql = "";
		if (category.getID() == 0)
			this.sessionFactory.getCurrentSession().save(category);
		else if (category.getID() > 0) {
			sql = "update Category set name=:name where " +
					" ID=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("name", category.getName());
			if (category.getID() > 0)
				query.setParameter("id", category.getID());
			
			query.executeUpdate();
		}
		
	}
	

	@SuppressWarnings("unchecked")
	public void deleteCategory(long id) {
		// mark all category_id of products with this category to zero before deleting the category row 
		String sql = "";
		sql = "update Product set category_id=0 where category_id=:id";

		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();

		sql = "delete from Category where ID=:id";
		query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		List<Category> list = this.sessionFactory.getCurrentSession().createQuery("from Category order by name").list();

		return list;
	}
	
}
