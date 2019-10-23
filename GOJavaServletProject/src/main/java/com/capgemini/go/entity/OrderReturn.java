package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "ORDER_RETURN")
public class OrderReturn{
	
	@Id
	@Column(name="ORDER_ID")
	private String OrderId;
	
	@Id
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Id
	@Column(name="PRODUCT_UIN")
	private	String productUIN;
	
	@Id
	@Column(name="ORDER_RETURN_TIME")
	private String orderRetrunTime;
	
	@Id
	@Column(name="ORDER_RETURN_REASON")
	private String orderRetrunReason;
	
	@Id
	@Column(name="ORDER_RETURN_STATUS")
	private String orderReturnStatus;

	@Override
	public String toString() {
		return "OrderReturn [OrderId=" + OrderId + ", productId=" + productId + ", productUIN=" + productUIN
				+ ", orderRetrunTime=" + orderRetrunTime + ", orderRetrunReason=" + orderRetrunReason
				+ ", orderReturnStatus=" + orderReturnStatus + "]";
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
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

	public String getOrderRetrunTime() {
		return orderRetrunTime;
	}

	public void setOrderRetrunTime(String orderRetrunTime) {
		this.orderRetrunTime = orderRetrunTime;
	}

	public String getOrderRetrunReason() {
		return orderRetrunReason;
	}

	public void setOrderRetrunReason(String orderRetrunReason) {
		this.orderRetrunReason = orderRetrunReason;
	}

	public String getOrderReturnStatus() {
		return orderReturnStatus;
	}

	public void setOrderReturnStatus(String orderReturnStatus) {
		this.orderReturnStatus = orderReturnStatus;
	}

	public OrderReturn(String orderId, String productId, String productUIN, String orderRetrunTime,
			String orderRetrunReason, String orderReturnStatus) {
		super();
		OrderId = orderId;
		this.productId = productId;
		this.productUIN = productUIN;
		this.orderRetrunTime = orderRetrunTime;
		this.orderRetrunReason = orderRetrunReason;
		this.orderReturnStatus = orderReturnStatus;
	}
}