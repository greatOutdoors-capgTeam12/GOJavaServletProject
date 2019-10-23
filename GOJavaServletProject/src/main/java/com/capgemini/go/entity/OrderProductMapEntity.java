package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "OrderProductMapEntity")
@Table(name = "order_product_map", uniqueConstraints = { @UniqueConstraint(columnNames = "product_uin") })
public class OrderProductMapEntity implements Serializable {

	private static final long serialVersionUID = -5870696027119904888L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", unique = false, nullable = false, length = 20)
	private String orderId;

	@Column(name = "product_id", unique = false, nullable = false, length = 20)
	private String productId;

	@Column(name = "product_uin", unique = true, nullable = false, length = 20)
	private String productUIN;

	@Column(name = "product_status", unique = false, nullable = false, length = 20)
	private int productStatus;

	@Column(name = "gift_status", unique = false, nullable = false, length = 20)
	private int giftStatus;

	public OrderProductMapEntity(String orderId, String productid, String productuin, int productstatus,
			int giftstatus) {
		super();
		this.orderId = orderId;
		this.productId = productid;
		this.productUIN = productuin;
		this.productStatus = productstatus;
		this.giftStatus = giftstatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductid() {
		return productId;
	}

	public void setProductid(String productid) {
		this.productId = productid;
	}

	public String getProductuin() {
		return productUIN;
	}

	public void setProductuin(String productuin) {
		this.productUIN = productuin;
	}

	public int getProductstatus() {
		return productStatus;
	}

	public void setProductstatus(int productstatus) {
		this.productStatus = productstatus;
	}

	public int getGiftstatus() {
		return giftStatus;
	}

	public void setGiftstatus(int giftstatus) {
		this.giftStatus = giftstatus;
	}

	@Override
	public String toString() {

		return "OrderProductMap [orderId=" + orderId + ", productid=" + productId + ", productuin=" + productUIN
				+ ", productstatus=" + productStatus + ", giftstatus=" + giftStatus + "]";

	}

}
