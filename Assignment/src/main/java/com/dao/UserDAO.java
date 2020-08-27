package com.dao;

import java.util.Calendar;

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
public class UserDAO {

	//@Autowired
    private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
	}

	
	@SuppressWarnings("unchecked")
	public User authenticate(String emailId, String pwd) {
		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where emailid=:emailid and pwd=:pwd")
				.setParameter("emailid", emailId)
				.setParameter("pwd", pwd)
				.list();
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public User getUserById(long id) {
		String strId = String.valueOf(id);
		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where id=" + strId).list();
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}
	@SuppressWarnings("unchecked")
	public User getUserByEmailId(String emailId) {
		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where emailid='" + emailId + "'").list();
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public void updateUser(User user) {
		String sql = "";
		if (user.getID() == 0) {
			user.setDateAdded(Calendar.getInstance().getTime());
			this.sessionFactory.getCurrentSession().save(user);
		} else if (user.getID() > 0) {
			sql = "update User set fname=:fname, lname=:lname, address=:address, age=:age, pwd=:pwd where " +
					" ID=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("fname", user.getFname());
			query.setParameter("lname", user.getLname());
			query.setParameter("address", user.getAddress());
			query.setParameter("age", user.getAge());
			query.setParameter("pwd", user.getPwd());
			if (user.getID() > 0)
				query.setParameter("id", user.getID());
			
			query.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User order by date_added").list();

		return list;
	}
	
}
