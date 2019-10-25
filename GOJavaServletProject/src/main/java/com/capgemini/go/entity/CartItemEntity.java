package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
class CartItemUniqueKey {
	
	@Column(name = "RETAILER_ID", unique = true, nullable = false)
	private String userId;
	
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private String productId;

	//getters
	public String getUserId() {return userId;}
	public String getProductId() {return productId;}

	//setters
	public void setUserId(String userId) {this.userId = userId;}
	public void setProductId(String productId) {this.productId = productId;}

	//constructors
	public CartItemUniqueKey() {
	
	}
	public CartItemUniqueKey(String userId, String productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}	
}

@Entity
@Table(name="CART_ITEM")
public class CartItemEntity {	
	
	@EmbeddedId
	@Column(name= "CartItemUniqueKey", unique=true, nullable=false)
	private CartItemUniqueKey Id;
	
	@Column(name = "QUANTITY", unique = false, nullable = false)
	private int quantity;
	
	
	//getters
	public String getUserId() {return this.Id.getUserId();}
	public String getProductId() {return this.getProductId();}
	public int getQuantity() {return quantity;}

	//setters
	public void setUserId(String userId) {this.Id.setUserId(userId);}
	public void setProductId(String productId) {this.Id.setProductId(productId);}
	public void setQuantity(int quantity) {this.quantity = quantity;}

	//constructors
	
	public CartItemEntity() {
	
	}
	
	public CartItemEntity(String retailerId, String productId, int quantity) {
		this.Id.setUserId(retailerId);
		this.Id.setProductId(productId);
		this.quantity = quantity;
	}
	
	
}
