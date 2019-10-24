package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "OrderProductMapEntity")
@Table(name = "ORDER_PRODUCT_MAP", uniqueConstraints = { @UniqueConstraint(columnNames = "product_uin") })
public class OrderProductMapEntity implements Serializable {

	private static final long serialVersionUID = -5870696027119904888L;

	@Column(name = "ORDER_ID", unique = false, length = 20)
	private String orderId;

	@Column(name = "PRODUCT_ID", unique = false, length = 20)
	private String productId;

	@Id
	@Column(name = "PRODUCT_UIN", unique = true, length = 20)
	private String productUIN;

	@Column(name = "PRODUCT_STATUS", unique = false, length = 20)
	private int productStatus;

	@Column(name = "GIFT_STATUS", unique = false, length = 20)
	private int giftStatus;

	public OrderProductMapEntity() {
		super();
	}

	public OrderProductMapEntity(String orderId, String productId, String productUIN, int productStatus,
			int giftStatus) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productUIN = productUIN;
		this.productStatus = productStatus;
		this.giftStatus = giftStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductUIN() {
		return productUIN;
	}

	public void setProductUIN(String productUIN) {
		this.productUIN = productUIN;
	}

	public int getProductStatus() {
		return productStatus;
	}

	

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	public int getGiftStatus() {
		return giftStatus;
	}

	public void setGiftStatus(int giftStatus) {
		this.giftStatus = giftStatus;
	}


	
}
