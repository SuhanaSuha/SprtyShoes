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
import com.model.*;

@Repository
@Component
public class AdminDAO {

	//@Autowired
    private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public Admin authenticate(String adminId, String pwd) {
		List<Admin> list = this.sessionFactory.getCurrentSession().createQuery("from Admin where admin_id=:admin_id and admin_pwd=:admin_pwd")
				.setParameter("admin_id", adminId)
				.setParameter("admin_pwd", pwd)
				.list();
		if (list.size() > 0)
			return (Admin) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public Admin getAdminById(long id) {
		List<Admin> list = this.sessionFactory.getCurrentSession().createQuery("from Admin where ID=:admin_id")
				.setParameter("admin_id", id)
				.list();
		if (list.size() > 0)
			return (Admin) list.get(0);
		else
			return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void updatePwd(Admin admin) {
		
		String sql = "update Admin set admin_pwd=:admin_pwd where ID=:id";
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("admin_pwd", admin.getAdminPwd());
		query.setParameter("id", admin.getID());
		
		query.executeUpdate();
		
	}

	
}
