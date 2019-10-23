package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "ORDER_RETURN")
public class OrderReturnEntity{
	
	@Id
	@Column(name="ORDER_ID",unique = false, nullable = false)
	private String OrderId;
	
	@Id
	@Column(name="PRODUCT_ID",unique = false, nullable = false)
	private String productId;
	
	@Id
	@Column(name="PRODUCT_UIN",unique = true, nullable = false)
	private	String productUIN;
	
	@Id
	@Column(name="ORDER_RETURN_TIME",unique = false, nullable = false)
	private String orderReturnTime;
	
	@Id
	@Column(name="ORDER_RETURN_REASON",unique = false, nullable = false)
	private String orderReturnReason;
	
	@Id
	@Column(name="ORDER_RETURN_STATUS",unique = false, nullable = false)
	private String orderReturnStatus;

	@Override
	public String toString() {
		return "OrderReturnEntity [OrderId=" + OrderId + ", productId=" + productId + ", productUIN=" + productUIN
				+ ", orderRetrunTime=" + orderReturnTime+ ", orderRetrunReason=" + orderReturnReason
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
		return orderReturnReason;
	}

	public void setOrderRetrunTime(String orderRetrunTime) {
		this.orderReturnReason = orderRetrunTime;
	}

	public String getOrderRetrunReason() {
		return orderReturnReason;
	}

	public void setOrderRetrunReason(String orderRetrunReason) {
		this.orderReturnReason = orderRetrunReason;
	}

	public String getOrderReturnStatus() {
		return orderReturnStatus;
	}

	public void setOrderReturnStatus(String orderReturnStatus) {
		this.orderReturnStatus = orderReturnStatus;
	}

	public OrderReturnEntity(String orderId, String productId, String productUIN, String orderReturnTime,
			String orderRetrunReason, String orderReturnStatus) {
		super();
		OrderId = orderId;
		this.productId = productId;
		this.productUIN = productUIN;
		this.orderReturnReason = orderReturnTime;
		this.orderReturnReason = orderRetrunReason;
		this.orderReturnStatus = orderReturnStatus;
	}
}