package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  


@Entity
@Table(name= "admin")   
public class Admin { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "admin_id")
	private String adminId;
	
	@Column(name = "admin_pwd")
	private String pwd;
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getID() {return this.ID; }
	public String getAdminId() { return this.adminId;}
	public String getAdminPwd() { return this.pwd;}
	
	public void setID(long id) { this.ID = id;}
	public void setAdminId(String value) { this.adminId= value;}
	public void setAdminPwd(String value) { this.pwd = value;}
}
