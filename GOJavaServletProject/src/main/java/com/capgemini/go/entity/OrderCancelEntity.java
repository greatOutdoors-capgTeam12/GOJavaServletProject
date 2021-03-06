package com.capgemini.go.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity(name = "OrderCancelEntity")
@Table(name = "order_cancel", uniqueConstraints = { @UniqueConstraint(columnNames = "product_uin") })
public class OrderCancelEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "order_id", unique = false, length = 20)
	private String orderid;
	
	@Column(name = "user_Id", unique = false, length = 20)
	private String userId;

	@Column(name = "product_id", unique = false, length = 20)
	private String productid;
	
	@Id
	@Column(name = "product_uin", unique = true, length = 20)
	private String productuin;

	@Column(name = "order_cancel_time", unique = false, length = 20)
	private String ordercanceltime;

	@Column(name = "order_cancel_status", unique = false, length = 1)
	private String ordercancelstatus;

	public OrderCancelEntity() {
		
	}
	
	public OrderCancelEntity(String userId, String orderid, String productid, String productuin, String ordercanceltime,
			String ordercancelstatus) {
		super();
		this.userId = userId;
		this.orderid = orderid;
		this.productid = productid;
		this.productuin = productuin;
		this.ordercanceltime = ordercanceltime;
		this.ordercancelstatus = ordercancelstatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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

	public String getOrdercanceltime() {
		return ordercanceltime;
	}

	public void setOrdercanceltime(String ordercanceltime) {
		this.ordercanceltime = ordercanceltime;
	}

	public String getOrdercancelstatus() {
		return ordercancelstatus;
	}

	public void setOrdercancelstatus(String ordercancelstatus) {
		this.ordercancelstatus = ordercancelstatus;
	}

	@Override
	public String toString() {
		return "OrderCancelEntity [userId=" + userId + ", orderid=" + orderid + ", productid=" + productid
				+ ", productuin=" + productuin + ", ordercanceltime=" + ordercanceltime + ", ordercancelstatus="
				+ ordercancelstatus + "]";
	}

}
