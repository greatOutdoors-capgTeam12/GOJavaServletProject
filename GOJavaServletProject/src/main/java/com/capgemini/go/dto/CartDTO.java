package com.capgemini.go.dto;

public class CartDTO {
	private String retailerId;
	private String productId;
	private int quantity;

	/**
	 * @param productId
	 * @param retailerId
	 * @param quantity
	 */
	public CartDTO(String productId, String retailerId, int quantity) {
		super();
		this.productId = productId;
		this.retailerId = retailerId;
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
