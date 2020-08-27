package com.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  


@Entity
@Table(name= "purchases")   
public class Purchase { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "gross_total")
	private BigDecimal total;

	
	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getID() {return this.ID; }
	public long getUserId() { return this.userId;}
	public BigDecimal getTotal() { return this.total;}
	public Date getDate() { return this.date;}
	
	public void setID(long id) { this.ID = id;}
	public void setUserId(long value) { this.userId = value;}
	public void setTotal(BigDecimal value) { this.total = value;}
	public void setDate(Date date) { this.date = date;}   
}
