package com.service;

import java.util.List;


import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdminDAO;
import com.model.Admin;
 


@Component
public class AdminService {

	 @Autowired
	 private AdminDAO adminDAO;

		@Transactional
		public Admin authenticate(String adminId, String pwd) {
			return adminDAO.authenticate(adminId, pwd);
		}
		
		@Transactional
		public Admin getAdminById(long id) {
			return adminDAO.getAdminById(id);
		}		
		
		@Transactional
		public void updatePwd(Admin admin) {
			adminDAO.updatePwd(admin);
		}




		 
}
