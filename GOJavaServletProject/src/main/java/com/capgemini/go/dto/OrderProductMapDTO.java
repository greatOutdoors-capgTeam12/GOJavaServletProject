package com.capgemini.go.dto;

public class OrderProductMapDTO {

	private String orderId;
	private String productId;
	private String productUIN;
	private boolean productStatus;

	public OrderProductMapDTO(String orderId, String productId, String productUIN, boolean productStatus,
			boolean giftStatus) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productUIN = productUIN;
		this.productStatus = productStatus;
		this.giftStatus = giftStatus;
	}

	private boolean giftStatus;

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

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public boolean isGiftStatus() {
		return giftStatus;
	}

	public void setGiftStatus(boolean giftStatus) {
		this.giftStatus = giftStatus;
	}

}
