package com.capgemini.go.dto;

public class ProductUINMapDTO {

	private String productID;
	private String universalIdentificationNumber;
	private boolean productIsPresent;

	public ProductUINMapDTO(String productID, String universalIdentificationNumber, boolean productIsPresent) {
		super();
		this.productID = productID;
		this.universalIdentificationNumber = universalIdentificationNumber;
		this.productIsPresent = productIsPresent;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getUniversalIdentificationNumber() {
		return universalIdentificationNumber;
	}

	public void setUniversalIdentificationNumber(String universalIdentificationNumber) {
		this.universalIdentificationNumber = universalIdentificationNumber;
	}

	public boolean isProductIsPresent() {
		return productIsPresent;
	}

	public void setProductIsPresent(boolean productIsPresent) {
		this.productIsPresent = productIsPresent;
	}

}
