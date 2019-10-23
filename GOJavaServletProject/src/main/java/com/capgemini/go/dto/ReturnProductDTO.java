package com.capgemini.go.dto;

import java.util.Date;

public class ReturnProductDTO {

	private String orderId;
	private String userId;
	private String productId;
	private int qty;
	private String returnReason;
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getOrderId() {
		return orderId;
	}

	public ReturnProductDTO(String orderId, String userId,
			String returnReason) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		
		this.returnReason = returnReason;
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

	

}
