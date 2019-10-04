package com.capgemini.go.bean;

import java.io.Serializable;

public class FrequentOrderedBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3762546600175350316L;
	private String RetailerID;
	private String ProductID;
	private String AddressID;

	public FrequentOrderedBean(String retailerID, String productID, String addressID) {
		super();
		RetailerID = retailerID;
		ProductID = productID;
		AddressID = addressID;
	}

	public String getRetailerID() {
		return RetailerID;
	}

	public void setRetailerID(String retailerID) {
		RetailerID = retailerID;
	}

	public String getProductID() {
		return ProductID;
	}

	public void setProductID(String productID) {
		ProductID = productID;
	}

	public String getAddressID() {
		return AddressID;
	}

	public void setAddressID(String addressID) {
		AddressID = addressID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AddressID == null) ? 0 : AddressID.hashCode());
		result = prime * result + ((ProductID == null) ? 0 : ProductID.hashCode());
		result = prime * result + ((RetailerID == null) ? 0 : RetailerID.hashCode());
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
		FrequentOrderedBean other = (FrequentOrderedBean) obj;
		if (AddressID == null) {
			if (other.AddressID != null)
				return false;
		} else if (!AddressID.equals(other.AddressID))
			return false;
		if (ProductID == null) {
			if (other.ProductID != null)
				return false;
		} else if (!ProductID.equals(other.ProductID))
			return false;
		if (RetailerID == null) {
			if (other.RetailerID != null)
				return false;
		} else if (!RetailerID.equals(other.RetailerID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FrequentOrderedBean [RetailerID=" + RetailerID + ", ProductID=" + ProductID + ", AddressID=" + AddressID
				+ "]";
	}
}
