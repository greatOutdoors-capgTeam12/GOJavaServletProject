package com.capgemini.go.dto;

public class AddressDTO {
	private String addressId;
	private String retailerId;
	private String city;
	private String state;
	private String zip;
	private String buildingNo;
	private String country;
	boolean addressStatus;

	public AddressDTO(String addressId, String retailerId, String city, String state, String zip, String buildingNo,
			String country, boolean addressStatus) {
		super();
		this.addressId = addressId;
		this.retailerId = retailerId;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.buildingNo = buildingNo;
		this.country = country;
		this.addressStatus = addressStatus;
	}

	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getRetailerId() {
		return this.retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {

		return state;
	}

	public void setState(String state) {

		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getBuildingNo() {
		return this.buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isAddressStatus() {
		return this.addressStatus;
	}

	public void setAddressStatus(boolean addressStatus) {
		this.addressStatus = addressStatus;
	}

}
