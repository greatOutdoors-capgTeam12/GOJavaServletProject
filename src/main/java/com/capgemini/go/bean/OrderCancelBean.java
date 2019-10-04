package com.capgemini.go.bean;

import java.io.Serializable;

public class OrderCancelBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4217143969955692247L;
	private String orderId;
	private String uderId;
	private String ProductId;
	private String ProductUIN;

	public OrderCancelBean(String orderId, String uderId, String productId, String productUIN) {
		super();
		this.orderId = orderId;
		this.uderId = uderId;
		ProductId = productId;
		ProductUIN = productUIN;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUderId() {
		return uderId;
	}

	public void setUderId(String uderId) {
		this.uderId = uderId;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getProductUIN() {
		return ProductUIN;
	}

	public void setProductUIN(String productUIN) {
		ProductUIN = productUIN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ProductId == null) ? 0 : ProductId.hashCode());
		result = prime * result + ((ProductUIN == null) ? 0 : ProductUIN.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((uderId == null) ? 0 : uderId.hashCode());
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
		OrderCancelBean other = (OrderCancelBean) obj;
		if (ProductId == null) {
			if (other.ProductId != null)
				return false;
		} else if (!ProductId.equals(other.ProductId))
			return false;
		if (ProductUIN == null) {
			if (other.ProductUIN != null)
				return false;
		} else if (!ProductUIN.equals(other.ProductUIN))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (uderId == null) {
			if (other.uderId != null)
				return false;
		} else if (!uderId.equals(other.uderId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderCancelBean [orderId=" + orderId + ", uderId=" + uderId + ", ProductId=" + ProductId
				+ ", ProductUIN=" + ProductUIN + "]";
	}
}
