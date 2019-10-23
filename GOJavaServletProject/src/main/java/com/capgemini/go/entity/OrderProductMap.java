package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

public class OrderProductMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", unique = false, nullable = false, length = 20)
	private String orderId;

	@Column(name = "product_id", unique = false, nullable = false, length = 20)
	private String productid;

	@Column(name = "product_uin", unique = true, nullable = false, length = 20)
	private String productuin;

	@Column(name = "product_status", unique = false, nullable = false, length = 20)
	private int productstatus;

	@Column(name = "gift_status", unique = false, nullable = false, length = 20)
	private int giftstatus;

	public OrderProductMap(String orderId, String productid, String productuin, int productstatus, int giftstatus) {
		super();
		this.orderId = orderId;
		this.productid = productid;
		this.productuin = productuin;
		this.productstatus = productstatus;
		this.giftstatus = giftstatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductuin() {
		return productuin;
	}

	public void setProductuin(String productuin) {
		this.productuin = productuin;
	}

	public int getProductstatus() {
		return productstatus;
	}

	public void setProductstatus(int productstatus) {
		this.productstatus = productstatus;
	}

	public int getGiftstatus() {
		return giftstatus;
	}

	public void setGiftstatus(int giftstatus) {
		this.giftstatus = giftstatus;
	}

	@Override
	public String toString() {
		return "OrderProductMap [orderId=" + orderId + ", productid=" + productid + ", productuin=" + productuin
				+ ", productstatus=" + productstatus + ", giftstatus=" + giftstatus + "]";
	}

}
