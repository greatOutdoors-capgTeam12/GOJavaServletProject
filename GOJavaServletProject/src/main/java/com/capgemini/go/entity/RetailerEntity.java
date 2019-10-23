package com.capgemini.go.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "retailer")
public class RetailerEntity {

	// attributes
	@Id
	@Column(name = "USER_ID", unique = false, nullable = true)
	private String userId;
	@Column(name = "DISCOUNT", unique = false, nullable = false)
	private Double discount;
	@Column(name = "CART_ID", unique = false, nullable = false)
	private String cartId;

	// Getters
	public String getUserI1() {
		return userId;
	}

	public Double getDiscount() {
		return discount;
	}

	public String getCartId() {
		return cartId;
	}

	// Setters
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setUserI1(String userI1) {
		this.userId = userI1;
	}

	public RetailerEntity(String userI1, Double discount, String cartId) {
		super();
		this.userId = userI1;
		this.discount = discount;
		this.cartId = cartId;
	}

	public RetailerEntity() {

	}

	@Override
	public String toString() {
		return "RetailerEntity [userId=" + userId + ", discount=" + discount + ", cartId=" + cartId + "]";
	}

}
