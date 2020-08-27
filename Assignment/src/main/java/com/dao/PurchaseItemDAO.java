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

import com.model.PurchaseItem;

@Repository
@Component
public class PurchaseItemDAO {

	//@Autowired
    private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public PurchaseItem getItemById(long id) {
		String strId = String.valueOf(id);
		List<PurchaseItem> list = this.sessionFactory.getCurrentSession().createQuery("from PurchaseItem where id=" + strId).list();
		if (list.size() > 0)
			return (PurchaseItem) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PurchaseItem> getAllItemsByPurchaseId(long purchaseId) {
		String strId = String.valueOf(purchaseId);
		List<PurchaseItem> list = this.sessionFactory.getCurrentSession().createQuery("from PurchaseItem where purchase_id=" + strId).list();
		return list;
	}	
	
	@SuppressWarnings("unchecked")
	public void updateItem(PurchaseItem item) {
		this.sessionFactory.getCurrentSession().save(item);
		
	}
	

	@SuppressWarnings("unchecked")
	public void deleteItem(long id) {
		//Pending: Purchase total in purchase table should be updated after this
		String sql = "";
		sql += "delete from PurchaseItem where ID=:id";

		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public void deleteAllItemsForPurchaseId(long purchaseId) {
		String sql = "";
		sql += "delete from PurchaseItem where purchase_id=:purchase_id";

		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("purchase_id", purchaseId);
		
		query.executeUpdate();
		
	}

	
}
