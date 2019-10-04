package com.capgemini.go.dto;

import java.util.Date;

public class OrderReturnDTO {

	private String orderId;
	private String userId;
	private String productId;
	private String productUIN;
	private Date orderReturnTime;
	private String returnReason;
	private int orderReturnStatus;

	public String getOrderId() {
		return orderId;
	}

	public OrderReturnDTO(String orderId, String userId, String productId, String productUIN, Date orderReturnTime,
			String returnReason, int orderReturnStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.productUIN = productUIN;
		this.orderReturnTime = orderReturnTime;
		this.returnReason = returnReason;
		this.orderReturnStatus = orderReturnStatus;
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

	public Date getOrderReturnTime() {
		return orderReturnTime;
	}

	public void setOrderReturnTime(Date orderReturnTime) {
		this.orderReturnTime = orderReturnTime;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public int getOrderReturnStatus() {
		return orderReturnStatus;
	}

	public void setOrderReturnStatus(int orderReturnStatus) {
		this.orderReturnStatus = orderReturnStatus;
	}

}
