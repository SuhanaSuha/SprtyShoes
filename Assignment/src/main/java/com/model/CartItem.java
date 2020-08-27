package com.model;

import java.math.BigDecimal;


/**
 * This is NOT a Hibernate class or a table class. This is a POJO which is used internally within the app
 * @author oem
 *
 */
public class CartItem { 

	private long productId;
	private String name;
	private BigDecimal rate;
	private BigDecimal price;
	private int qty;
	
    
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getProductId() {return this.productId; }  
	public String getName() { return this.name;} 
	public BigDecimal getPrice() { return this.price;} 
	public BigDecimal getRate() { return this.rate;} 
	public int getQty() { return this.qty;} 

	
	public void setProductId(long id) { this.productId= id;}
	public void setName(String value) { this.name = value;}
	public void setPrice(BigDecimal value) { this.price = value;}
	public void setRate(BigDecimal value) { this.rate = value;}
	public void setQty(int value) { this.qty = value;}
}
