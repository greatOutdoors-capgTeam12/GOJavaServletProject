package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "ORDER_PRODUCT_MAP")
public class OrderProductMap{
	
	@Id
	@Column(name="ORDER_ID")
	private String OrderId;
	
	@Id
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Id
	@Column(name="PRODUCT_UIN")
	private	String productUIN;
	
	@Id
	@Column(name="PRODUCT_STATUS")
	private String productStatus;
	
	@Id
	@Column(name="GIFT_STATUS")
	private String giftStatus;
}