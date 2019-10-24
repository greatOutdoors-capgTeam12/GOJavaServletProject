package com.capgemini.go.entity;

import javax.persistence.*;

@Embeddable
public class OrderProductMapPK {

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
