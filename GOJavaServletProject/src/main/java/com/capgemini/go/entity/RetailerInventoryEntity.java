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
	private Calendar productDispatchTimestamp;
	
	@Column(name = "PRODUCT_RECEIVE_TIMESTAMP", unique = false, nullable = true)
	private Calendar productReceiveTimestamp;
	
	@Column(name = "PRODUCT_SALE_TIMESTAMP", unique = false, nullable = true)
	private Calendar productSaleTimestamp;
	
	// getters
	public String getRetailerId () {return this.retailerId;}
	public byte getProductCategory () {return this.productCategory;}
	public String getProductUniqueId () {return this.productUniqueId;}
	public Calendar getProductDispatchTimestamp () {return this.productDispatchTimestamp;}
	public Calendar getProductReceiveTimestamp () {return this.productReceiveTimestamp;}
	public Calendar getProductSaleTimestamp () {return this.productSaleTimestamp;}
	
	// setters
	public void setRetailerId (String retailerId) {this.retailerId = retailerId;}
	public void setProductCategory (byte productCategory) {this.productCategory = productCategory;}
	public void setProductUniqueId (String productUniqueId) {this.productUniqueId = productUniqueId;}
	public void setProductDispatchTimestamp (Calendar productOrderTimestamp) {this.productDispatchTimestamp = productOrderTimestamp;}
	public void setProductReceiveTimestamp (Calendar productDeliveryTimestamp) {this.productReceiveTimestamp = productDeliveryTimestamp;}
	public void setProductSaleTimestamp (Calendar productSaleTimestamp) {this.productSaleTimestamp = productSaleTimestamp;}
	
	// constructors
	public RetailerInventoryEntity () {
		
	}
	
	public RetailerInventoryEntity (String retailerId, byte productCategory, String productUniqueId, 
			Calendar productOrderTimestamp, Calendar productDeliveryTimestamp, Calendar productSaleTimestamp) {
		this.retailerId = retailerId;
		this.productCategory = productCategory;
		this.productUniqueId = productUniqueId;
		this.productDispatchTimestamp = productOrderTimestamp;
		this.productReceiveTimestamp = productDeliveryTimestamp;
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
		if (this.productReceiveTimestamp != null) {
			temp += ", productDeliveryTimestamp: " + calendarToString(this.productReceiveTimestamp);
		}
		if (this.productSaleTimestamp != null) {
			temp += ", productSaleTimestamp: " + calendarToString(this.productSaleTimestamp);
		}
        String result = "[retailerId: " + this.retailerId + ", productCategory: " 
        		+ this.productCategory + ", productUniqueId: " + this.productUniqueId 
        		+ ", productOrderTimestamp: " + calendarToString(this.productDispatchTimestamp) + temp + "]";
        return result;
    }
}
