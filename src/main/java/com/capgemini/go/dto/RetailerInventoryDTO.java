package com.capgemini.go.dto;

import java.util.Calendar;

public class RetailerInventoryDTO {
	private String retailerUserId;
	private int productCategory;
	private String productUIN; // contains product id when accessing delivery time report
	private Calendar productDispatchTime; // remains null when accessing database
	private Calendar productRecieveTime; // remains null when accessing database
	private Calendar productShelfTimeOut;

	// getters
	public String getRetailerUserId() {
		return retailerUserId;
	}

	public int getProductCategory() {
		return this.productCategory;
	}

	public String getProductUIN() {
		return productUIN;
	}

	public Calendar getProductDispatchTime() {
		return productDispatchTime;
	}

	public Calendar getProductRecieveTime() {
		return productRecieveTime;
	}

	public Calendar getProductShelfTimeOut() {
		return productShelfTimeOut;
	}

	// setters
	public void setRetailerUserId(String retailerUserId) {
		this.retailerUserId = retailerUserId;
	}

	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}

	public void setProductUIN(String productUIN) {
		this.productUIN = productUIN;
	}

	public void setProductDispatchTime(Calendar productDispatchTime) {
		this.productDispatchTime = productDispatchTime;
	}

	public void setProductRecieveTime(Calendar productRecieveTime) {
		this.productRecieveTime = productRecieveTime;
	}

	public void setProductShelfTimeOut(Calendar productShelfTimeOut) {
		this.productShelfTimeOut = productShelfTimeOut;
	}

	// constructor(s)
	public RetailerInventoryDTO() {
		super();
		this.retailerUserId = this.productUIN = null;
		this.productCategory = 0;
		this.productDispatchTime = this.productRecieveTime = this.productShelfTimeOut = null;
	}

	public RetailerInventoryDTO(String retailerUserId, int productCategory, String productUIN,
			Calendar productDispatchTime, Calendar productRecieveTime, Calendar productShelfTimeOut) {
		super();
		this.retailerUserId = retailerUserId;
		this.productCategory = productCategory;
		this.productUIN = productUIN;
		this.productDispatchTime = productDispatchTime;
		this.productRecieveTime = productRecieveTime;
		this.productShelfTimeOut = productShelfTimeOut;
	}
}
