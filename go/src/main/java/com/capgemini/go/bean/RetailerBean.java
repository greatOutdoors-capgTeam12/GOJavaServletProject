package com.capgemini.go.bean;

import java.io.Serializable;

public class RetailerBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3544067972668980631L;
	private String userId;
	private double discount;
	private String cartID;

	public RetailerBean(String userId, double discount, String cartID) {
		super();
		this.userId = userId;
		this.discount = discount;
		this.cartID = cartID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartID == null) ? 0 : cartID.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RetailerBean other = (RetailerBean) obj;
		if (cartID == null) {
			if (other.cartID != null)
				return false;
		} else if (!cartID.equals(other.cartID))
			return false;
		if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCartID() {
		return cartID;
	}

	public void setCartID(String cartID) {
		this.cartID = cartID;
	}

	@Override
	public String toString() {
		return "RetailerBean [userId=" + userId + ", discount=" + discount + ", cartID=" + cartID + "]";
	}
	
}
