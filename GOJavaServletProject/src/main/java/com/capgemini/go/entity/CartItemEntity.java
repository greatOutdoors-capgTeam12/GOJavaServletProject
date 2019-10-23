package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Cart-Item")
public class CartItemEntity {	
	@Id
	@Column(name = "userId", unique = true, nullable = false)
		private String userId;
	@Id
	@Column(name = "retailerId", unique = true, nullable = false)
		private String retailerId;
	@Column(name = "quantity", unique = false, nullable = false)
		private String quantity;
	
	//getters
	public String getUserId() {
		return userId;
	}
	public String getRetailerId() {
		return retailerId;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	
//setters
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	//constructors
	public CartItemEntity() {
		
	}
	public CartItemEntity(String userId, String retailerId, String quantity) {
		super();
		this.userId = userId;
		this.retailerId = retailerId;
		this.quantity = quantity;
	}
	

	
}
