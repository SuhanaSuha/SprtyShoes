package com.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;  
 

@Entity
@Table(name= "category")   
public class Category { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "name")
	private String name;
	
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getID() {return this.ID; }
	public String getName() { return this.name;}
	
	public void setID(long id) { this.ID = id;}
	public void setName(String value) { this.name = value;}
	
}
