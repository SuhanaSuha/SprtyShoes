package com.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  


@Entity
@Table(name= "users")   
public class User { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "address")
	private String address;

	@Column(name = "age")
	private int age;

	@Column(name = "date_added")
	private Date dateAdded;  
	
	@Column(name = "emailid")
	private String emailId;

	@Column(name = "pwd")
	private String pwd;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getID() {return this.ID; }
	public String getEmail() { return this.emailId;}
	public String getFname() { return this.fname;}
	public String getLname() { return this.lname;}
	public String getAddress() { return this.address;}
	public String getPwd() { return this.pwd;}
	public int getAge() { return this.age;}
	public Date getDateAdded() { return this.dateAdded;}
	
	public void setID(long id) { this.ID = id;}
	public void setEmail(String value) { this.emailId = value;}
	public void setFname(String value) { this.fname = value;}
	public void setLname(String value) { this.lname = value;}
	public void setAddress(String value) { this.address= value;}
	public void setPwd(String value) { this.pwd= value;}
	public void setAge(int value) { this.age= value;}
	public void setDateAdded(Date date) { this.dateAdded = date;}   
}
