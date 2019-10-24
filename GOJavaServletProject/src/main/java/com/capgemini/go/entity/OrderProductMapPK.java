package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class OrderProductMapPK implements Serializable{

	private static final long serialVersionUID = -3282093658803237026L;

	@Id
	@Column(name = "order_id", unique = false, length = 20)
	private String orderId;

	@Column(name = "product_id", unique = false, length = 20)
	private String productId;

	@Column(name = "product_uin", unique = true, length = 20)
	private String productUIN;

	public String getOrderId() {
		return orderId;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductUIN() {
		return productUIN;
	}
}
