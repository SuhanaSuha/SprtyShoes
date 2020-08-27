package com.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.model.Product; 

@Repository
@Component
public class ProductDAO {

	//@Autowired
    private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public Product getProductById(long id) {
		String strId = String.valueOf(id);
		List<Product> list = this.sessionFactory.getCurrentSession().createQuery("from Product where id=" + strId).list();
		if (list.size() > 0)
			return (Product) list.get(0);
		else
			return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void updateProduct(Product product) {
		String sql = "";
		if (product.getID() == 0)
				this.sessionFactory.getCurrentSession().save(product);
		else if (product.getID() > 0) {
			sql = "update Product set name=:name, price=:price, category_id=:category_id where " +
					" ID=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("name", product.getName());
			query.setParameter("price", product.getPrice());
			query.setParameter("category_id", product.getCategoryId());
			query.setParameter("id", product.getID());
			
			query.executeUpdate();
		}
		
	}
	

	@SuppressWarnings("unchecked")
	public void deleteProduct(long id) {
		// delete all purchase items for this product before deleting this product
		// Pending:Purchase total is not updated in the purchase total - it shows the old value
		
		String sql = "";
		sql = "delete from PurchaseItem where product_id=:id";
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);

		sql = "delete from Product where ID=:id";
		query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		 
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		List<Product> list = this.sessionFactory.getCurrentSession().createQuery("from Product order by name").list(); 

		return list;
	}
	
	
	
}
