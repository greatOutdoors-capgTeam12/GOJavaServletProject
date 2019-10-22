package com.capgemini.go.dto;

import java.util.Date;

public class OrderCancelDTO {
	private String orderId;
	private String userId;
	private String ProductId;
	private String ProductUIN;
	private Date orderCancelTime;
	private int orderCancelStatus;

	public OrderCancelDTO(String orderId, String userId, String productId, String productUIN, Date orderCancelTime,
			int orderCancelStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		ProductId = productId;
		ProductUIN = productUIN;
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

	public void setUserId(String uderId) {
		this.userId = uderId;
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
