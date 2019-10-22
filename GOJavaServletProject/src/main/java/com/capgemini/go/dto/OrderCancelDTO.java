package com.capgemini.go.dto;

import java.util.Date;

public class OrderCancelDTO {
	private String orderId;
	private String userId;
	private String productId;
	private String productUIN;
	private Date orderCancelTime;
	private int orderCancelStatus;

	public OrderCancelDTO(String orderId, String userId, String productId, String productUIN, Date orderCancelTime,
			int orderCancelStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.productUIN = productUIN;
		this.orderCancelTime = orderCancelTime;
		this.orderCancelStatus = orderCancelStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getOrderCancelTime() {
		return orderCancelTime;
	}

	public void setOrderCancelTime(Date orderCancelTime) {
		this.orderCancelTime = orderCancelTime;
	}

	public int getOrderCancelStatus() {
		return orderCancelStatus;
	}

	public void setOrderCancelStatus(int orderCancelStatus) {
		this.orderCancelStatus = orderCancelStatus;
	}

}
