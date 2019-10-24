package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class OrderProductMapPK implements Serializable{

	private static final long serialVersionUID = -3282093658803237026L;

	@Column(name = "ORDER_ID")
	private String orderId;

	@Column(name = "PRODUCT_ID")
	private String productId;

	@Column(name = "PRODUCT_UIN")
	private String productUIN;

}
