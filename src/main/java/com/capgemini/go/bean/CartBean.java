package com.capgemini.go.bean;

import java.io.Serializable;

public class CartBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1543836432636962138L;
	private String cartId;
	private String productId;
	private String productUIN;

	public CartBean(String cartId, String productId, String productUIN) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.productUIN = productUIN;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductUIN() {
		return productUIN;
	}

	public void setProductUIN(String productUIN) {
		this.productUIN = productUIN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productUIN == null) ? 0 : productUIN.hashCode());
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
		CartBean other = (CartBean) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productUIN == null) {
			if (other.productUIN != null)
				return false;
		} else if (!productUIN.equals(other.productUIN))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartBean [cartId=" + cartId + ", productId=" + productId + ", productUIN=" + productUIN + "]";
	}
}
