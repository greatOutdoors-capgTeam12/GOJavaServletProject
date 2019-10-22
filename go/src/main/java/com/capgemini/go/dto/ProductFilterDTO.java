package com.capgemini.go.dto;

public class ProductFilterDTO {

	private String productName;
	private String productColour;
	private double lowRange;
	private double highRange;
	private int category;
	private String manufacturer;

	/**
	 * @param productName
	 * @param productColour
	 * @param lowRange
	 * @param highRange
	 * @param category
	 * @param manufacturer
	 */
	public ProductFilterDTO(String productName, String productColour, double lowRange, double highRange, int category,
			String manufacturer) {
		super();
		this.productName = productName;
		this.productColour = productColour;
		this.lowRange = lowRange;
		this.highRange = highRange;
		this.category = category;
		this.manufacturer = manufacturer;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductColour() {
		return productColour;
	}

	public void setProductColour(String productColour) {
		this.productColour = productColour;
	}

	public double getLowRange() {
		return lowRange;
	}

	public void setLowRange(double lowRange) {
		this.lowRange = lowRange;
	}

	public double getHighRange() {
		return highRange;
	}

	public void setHighRange(double highRange) {
		this.highRange = highRange;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
