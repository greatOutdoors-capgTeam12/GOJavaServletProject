package com.capgemini.go.dto;

public class ReturnOrder {
	private String orderId;
	private String userId;
	private String returnReason;
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
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	@Override
	public String toString() {
		return "ReturnOrder [orderId=" + orderId + ", userId=" + userId + ", returnReason=" + returnReason + "]";
	}
	public ReturnOrder(String orderId, String userId, String returnReason) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.returnReason = returnReason;
	}
}
