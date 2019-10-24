package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RetailerCartUniqueKey {
	
	@Column(name = "userId", unique = true, nullable = false)
	private String userId;
	
	@Column(name = "productId", unique = true, nullable = false)
	private String productId;

	//getters
	public String getUserId() {return userId;}
	public String getProductId() {return productId;}

	//setters
	public void setUserId(String userId) {this.userId = userId;}
	public void setProductId(String productId) {this.productId = productId;}

	//constructors
	public RetailerCartUniqueKey() {
	
	}
	public RetailerCartUniqueKey(String userId, String productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}
	
	
}
