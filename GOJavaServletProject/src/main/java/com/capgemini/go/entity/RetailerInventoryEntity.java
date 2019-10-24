package com.capgemini.go.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RETAILER_INVENTORY")
public class RetailerInventoryEntity {
	// attributes
	@Column(name = "RETAILER_ID", unique = false, nullable = false)
	private String retailerId;
	
	@Column(name = "PRODUCT_CATEGORY", unique = false, nullable = false)
	private byte productCategory;
	
	@Id
	@Column(name = "PRODUCT_UIN", unique = true, nullable = false)
	private String productUniqueId;
	
	@Column(name = "PRODUCT_DISPATCH_TIMESTAMP", unique = false, nullable = false)
	private Calendar productOrderTimestamp;
	
	@Column(name = "PRODUCT_RECEIVE_TIMESTAMP", unique = false, nullable = true)
	private Calendar productDeliveryTimestamp;
	
	@Column(name = "PRODUCT_SALE_TIMESTAMP", unique = false, nullable = true)
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
		
		//String result = "{" + day + "/" + month + "/" + year + "}";
		//String result = "{" + day + "/" + month + "/" + year + "::" + hour + ":" + minute + ":" + second + ":" + milliSecond + "}";
		String result = "{Date: " + day + "/" + month + "/" + year + ", Time:" + hour + ":" + minute + ":" + second + "}";		
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
