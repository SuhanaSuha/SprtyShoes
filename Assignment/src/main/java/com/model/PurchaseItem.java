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
@Table(name= "purchase_items")   
public class PurchaseItem { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "purchase_id")
	private long purchaseId;
	
	@Column(name = "product_id")
	private long productId;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "rate")
	private BigDecimal rate;

	@Column(name = "qty")
	private int qty;

	@Column(name = "price")
	private BigDecimal price;

	
	
	public PurchaseItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getID() {return this.ID; }
	public long getPurchaseId() { return this.purchaseId;}
	public long getProductId() { return this.productId;}
	public long getUserId() { return this.userId;}
	public BigDecimal getRate() { return this.rate;}	
	public int getQty() { return this.qty;}	
	public BigDecimal getPrice() { return this.price;}
	
	public void setID(long id) { this.ID = id;}
	public void setPurchaseId(long value) { this.purchaseId = value;}
	public void setProductId(long value) { this.productId = value;}
	public void setUserId(long value) { this.userId = value;}
	public void setRate(BigDecimal value) { this.rate = value;}
	public void setQty(int value) { this.qty= value;}
	public void setPrice(BigDecimal value) { this.price= value;}
	
	

}
