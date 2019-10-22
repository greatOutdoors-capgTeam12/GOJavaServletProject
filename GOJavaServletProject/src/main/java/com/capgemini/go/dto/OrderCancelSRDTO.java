package com.capgemini.go.dto;

public class OrderCancelSRDTO {
	// 1 -> dispatched; 0 -> not dispatched // 1 -> attached with order ; 0 -> not attached
	private String userId;
	private String orderId;
	private String productId;
	private String productUIN;
	private int orderDispatchStatus; 
	private int productStatus; 
	
	public OrderCancelSRDTO(String userId, String orderId, String productId, String productUIN, int orderDispatchStatus,
			int productStatus) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.productId = productId;
		this.productUIN = productUIN;
		this.orderDispatchStatus = orderDispatchStatus;
		this.productStatus = productStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public int getOrderDispatchStatus() {
		return orderDispatchStatus;
	}

	public void setOrderDispatchStatus(int orderDispatchStatus) {
		this.orderDispatchStatus = orderDispatchStatus;
	}

	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	
	@Override
	public String toString() {
		return "[userId=" + userId + ", orderId=" + orderId + ", productId=" + productId
				+ ", productUIN=" + productUIN + ", orderDispatchStatus=" + orderDispatchStatus + ", productStatus="
				+ productStatus + "]";
	}

}
