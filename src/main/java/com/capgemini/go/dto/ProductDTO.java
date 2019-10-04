package com.capgemini.go.dto;

public class ProductDTO {

	private String productId;
	private double price;
	private String colour;
	private String dimension;
	private String specification;
	private String manufacturer;
	/*********************
	 * Quantity Can only be 1,2,3,4 or 5 1 = CAMPING 2 = GOLF 3 = MOUNTAINEERING 4 =
	 * OUTDOOR 5 = PERSONAL
	 *************************/
	private int quantity;
	private int productCategory;
	private String productName;

	public ProductDTO(String productId, double price, String colour, String dimension, String specification,
			String manufacturer, int quantity, int productCategory, String productName) {
		super();
		this.productId = productId;
		this.price = price;
		this.colour = colour;
		this.dimension = dimension;
		this.specification = specification;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.productCategory = productCategory;
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}

	public int getProductCategory() {
		return productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
