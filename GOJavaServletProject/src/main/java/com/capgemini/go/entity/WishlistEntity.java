package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_wishlist")
public class WishlistEntity {
	// attributes
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String userId;
	
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private String productId;
	
	// getters
	public String getUserId () {return this.userId;}
	public String getProductId () {return this.productId;}
	
	// setters
	void setUserId (String userId) {this.userId = userId;}
	void setProductId (String productId) {this.productId = productId;}
	
	// constructors
	public WishlistEntity () {
		
	}
	
	public WishlistEntity (String userId, String productId )
	{
		this.userId = userId;
		this.productId = productId;
	}
	

        String result = "[retailerId: " + this.userId + ", productId: " + this.productId + "]";
    }
