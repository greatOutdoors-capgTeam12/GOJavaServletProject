package com.capgemini.go.bean;

import java.io.Serializable;
import java.time.Period;

public class RetailerInventoryBean implements Serializable{
	private static final long serialVersionUID = -3867264172036777546L;
	
	private String retailerUserId;
	private int productCategory;
	private String productUIN;
	private Period productDeliveryTimePeriod;
	private Period productShelfTimePeriod;

	
	// getters
	public String 	getRetailerUserId() 			{return retailerUserId;}
	public int		getProductCategory()			{return this.productCategory;}
	public String 	getProductUIN() 				{return productUIN;}
	public Period 	getProductDeliveryTimePeriod() 	{return productDeliveryTimePeriod;}
	public Period 	getProductShelfTimePeriod() 	{return productShelfTimePeriod;}
	
	// setters
	public void setRetailerUserId				(String retailerUserId) 				{this.retailerUserId = retailerUserId;}
	public void setProductCategory				(int productCategory)					{this.productCategory = productCategory;}
	public void setProductUIN					(String productUIN) 					{this.productUIN = productUIN;}
	public void setProductDeliveryTimePeriod	(Period productDeliveryTimePeriod) 
		{this.productDeliveryTimePeriod = productDeliveryTimePeriod;}
	public void setProductShelfTimePeriod		(Period productShelfTimePeriod) 
	{this.productShelfTimePeriod = productShelfTimePeriod;}
	
	// constructor(s)
	public RetailerInventoryBean () {
		super();
		this.retailerUserId = this.productUIN = null;
		this.productCategory = 0;
		this.productDeliveryTimePeriod = this.productShelfTimePeriod = null;
	}
	
	public RetailerInventoryBean(String retailerUserId, int productCategory, String productUIN, Period productDeliveryTimePeriod,
			Period productShelfTimePeriod) {
		this.retailerUserId = retailerUserId;
		this.productCategory = productCategory;
		this.productUIN = productUIN;
		this.productDeliveryTimePeriod = productDeliveryTimePeriod;
		this.productShelfTimePeriod = productShelfTimePeriod;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productCategory;
		result = prime * result + ((productDeliveryTimePeriod == null) ? 0 : productDeliveryTimePeriod.hashCode());
		result = prime * result + ((productShelfTimePeriod == null) ? 0 : productShelfTimePeriod.hashCode());
		result = prime * result + ((productUIN == null) ? 0 : productUIN.hashCode());
		result = prime * result + ((retailerUserId == null) ? 0 : retailerUserId.hashCode());
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
		RetailerInventoryBean other = (RetailerInventoryBean) obj;
		if (productCategory != other.productCategory)
			return false;
		if (productDeliveryTimePeriod == null) {
			if (other.productDeliveryTimePeriod != null)
				return false;
		} else if (!productDeliveryTimePeriod.equals(other.productDeliveryTimePeriod))
			return false;
		if (productShelfTimePeriod == null) {
			if (other.productShelfTimePeriod != null)
				return false;
		} else if (!productShelfTimePeriod.equals(other.productShelfTimePeriod))
			return false;
		if (productUIN == null) {
			if (other.productUIN != null)
				return false;
		} else if (!productUIN.equals(other.productUIN))
			return false;
		if (retailerUserId == null) {
			if (other.retailerUserId != null)
				return false;
		} else if (!retailerUserId.equals(other.retailerUserId))
			return false;
		return true;
	}
	
	public static String periodToString (Period period) {
		String result = ""; int x = 0; boolean first = true;
		if (period == null) {return "N/A";}
		if ((x = period.getYears()) != 0) {result += ((first)?(""):(" ")) + "Years=" + Integer.toString(x);first = false;}
		if ((x = period.getMonths()) != 0) {result += ((first)?(""):(" ")) + "Months=" + Integer.toString(x); first = false;}
		if ((x = period.getDays()) != 0) {result += ((first)?(""):(" ")) + "Days=" + Integer.toString(x); first = false;} 
		return result;
	}
	
	@Override
	public String toString() {
		String dt = periodToString(this.productDeliveryTimePeriod);
		String st = periodToString(this.productShelfTimePeriod);
		return "RetailerInventoryBean [retailerUserId = " + retailerUserId + ", productCategory = " + productCategory 
				+ ", productUIN = " + productUIN + ", productDeliveryTimePeriod = " + dt + ", productShelfTimePeriod = " + st +"]";
	}
}
