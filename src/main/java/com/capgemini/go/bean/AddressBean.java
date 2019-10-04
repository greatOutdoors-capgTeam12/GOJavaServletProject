package com.capgemini.go.bean;

import java.io.Serializable;

public class AddressBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -844500777367995142L;
	private String AddressId;
	private String RetailerId;
	private String city;
	private String state;
	private String Zip;
	private String BuildingNo;
	private String country;
	boolean AddressStatus;

	public AddressBean (String addressId, String retailerId, String city,String state, String zip, String buildingNo, String country,
			boolean addressStatus) {
		super();
		AddressId = addressId;
		RetailerId = retailerId;
		this.city = city;
		this.state = state;
		Zip = zip;
		BuildingNo = buildingNo;
		this.country = country;
		AddressStatus = addressStatus;
	}

	public String getAddressId() {
		return AddressId;
	}

	public void setAddressId(String addressId) {
		AddressId = addressId;
	}

	public String getRetailerId() {
		return RetailerId;
	}

	public void setRetailerId(String retailerId) {
		RetailerId = retailerId;
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
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getBuildingNo() {
		return BuildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		BuildingNo = buildingNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isAddressStatus() {
		return AddressStatus;
	}

	public void setAddressStatus(boolean addressStatus) {
		AddressStatus = addressStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AddressId == null) ? 0 : AddressId.hashCode());
		result = prime * result + (AddressStatus ? 1231 : 1237);
		result = prime * result + ((BuildingNo == null) ? 0 : BuildingNo.hashCode());
		result = prime * result + ((RetailerId == null) ? 0 : RetailerId.hashCode());
		result = prime * result + ((Zip == null) ? 0 : Zip.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressBean other = (AddressBean) obj;
		if (AddressId == null) {
			if (other.AddressId != null)
				return false;
		} else if (!AddressId.equals(other.AddressId))
			return false;
		if (AddressStatus != other.AddressStatus)
			return false;
		if (BuildingNo == null) {
			if (other.BuildingNo != null)
				return false;
		} else if (!BuildingNo.equals(other.BuildingNo))
			return false;
		if (RetailerId == null) {
			if (other.RetailerId != null)
				return false;
		} else if (!RetailerId.equals(other.RetailerId))
			return false;
		if (Zip == null) {
			if (other.Zip != null)
				return false;
		} else if (!Zip.equals(other.Zip))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddressBean [AddressId=" + AddressId + ", RetailerId=" + RetailerId + ", city=" + city + ", Zip=" + Zip
				+ ", BuildingNo=" + BuildingNo + ", country=" + country + ", AddressStatus=" + AddressStatus + "]";
	}
	
	
}
