package com.capgemini.go.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="retailer_inventory")
public class RetailerInventoryEntity {
	// attributes
	@Column(name = "retailerId", unique = false, nullable = false)
	private String retailerId;
	
	@Column(name = "productCategory", unique = false, nullable = false)
	private byte productCategory;
	
	@Id
	@Column(name = "productUniqueId", unique = true, nullable = false)
	private String productUniqueId;
	
	@Column(name = "productOrderTimestamp", unique = false, nullable = false)
	private Calendar productOrderTimestamp;
	
	@Column(name = "productDeliveryTimestamp", unique = false, nullable = true)
	private Calendar productDeliveryTimestamp;
	
	@Column(name = "productSaleTimestamp", unique = false, nullable = true)
	private Calendar productSaleTimestamp;
	
	// getters
	public String getRetailerId () {return this.retailerId;}
	public byte getProductCategory () {return this.productCategory;}
	public String getProductUniqueId () {return this.productUniqueId;}
	public Calendar getProductOrderTimestamp () {return this.productOrderTimestamp;}
	public Calendar getProductDeliveryTimestamp () {return this.productDeliveryTimestamp;}
	public Calendar getProductSaleTimestamp () {return this.productSaleTimestamp;}
	
	// setters
	void setRetailerId (String retailerId) {this.retailerId = retailerId;}
	void setProductCategory (byte productCategory) {this.productCategory = productCategory;}
	void setProductUniqueId (String productUniqueId) {this.productUniqueId = productUniqueId;}
	void setProductOrderTimestamp (Calendar productOrderTimestamp) {this.productOrderTimestamp = productOrderTimestamp;}
	void setProductDeliveryTimestamp (Calendar productDeliveryTimestamp) {this.productDeliveryTimestamp = productDeliveryTimestamp;}
	void setProductSaleTimestamp (Calendar productSaleTimestamp) {this.productSaleTimestamp = productSaleTimestamp;}
	
	// constructors
	public RetailerInventoryEntity () {
		
	}
	
	public RetailerInventoryEntity (String retailerId, byte productCategory, String productUniqueId, 
			Calendar productOrderTimestamp, Calendar productDeliveryTimestamp, Calendar productSaleTimestamp) {
		this.retailerId = retailerId;
		this.productCategory = productCategory;
		this.productUniqueId = productUniqueId;
		this.productOrderTimestamp = productOrderTimestamp;
		this.productDeliveryTimestamp = productDeliveryTimestamp;
		this.productSaleTimestamp = productSaleTimestamp;
	}
	
	// other helper methods
	public static String calendarToString (Calendar instance) {
		int year = instance.get(Calendar.YEAR);
		int month = instance.get(Calendar.MONTH);
		int day = instance.get(Calendar.DAY_OF_MONTH);
		
		int hour = instance.get(Calendar.HOUR);
		int minute = instance.get(Calendar.MINUTE);
		int second = instance.get(Calendar.SECOND);
		int milliSecond = instance.get(Calendar.MILLISECOND);
		
		//String result = "{" + day + "/" + month + "/" + year + "}";
		//String result = "{" + day + "/" + month + "/" + year + "::" + hour + ":" + minute + ":" + second + ":" + milliSecond + "}";
		String result = "{Date: " + day + "/" + month + "/" + year + ", Time:" + hour + ":" + minute + ":" + second + ":" + milliSecond + "}";		
		return result;
	}
	
	@Override
    public String toString() {
		String temp = "";
		if (this.productDeliveryTimestamp != null) {
			temp += ", productDeliveryTimestamp: " + calendarToString(this.productDeliveryTimestamp);
		}
		if (this.productSaleTimestamp != null) {
			temp += ", productSaleTimestamp: " + calendarToString(this.productSaleTimestamp);
		}
        String result = "[retailerId: " + this.retailerId + ", productCategory: " 
        		+ this.productCategory + ", productUniqueId: " + this.productUniqueId 
        		+ ", productOrderTimestamp: " + calendarToString(this.productOrderTimestamp) + temp + "]";
        return result;
    }
}
