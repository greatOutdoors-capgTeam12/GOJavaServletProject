package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderReturnPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8143887179101862155L;

	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="PRODUCT_UIN")
	private String productUIN;
	
}
