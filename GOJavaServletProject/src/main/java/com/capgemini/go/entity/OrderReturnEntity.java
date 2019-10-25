package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name = "ORDER_RETURN")
public class OrderReturnEntity{
	
	@EmbeddedId
	private OrderReturnPK orderReturnPK;
	
	@Column(name="ORDER_RETURN_TIME")
	private String orderReturnTime;
	
	@Column(name="ORDER_RETURN_REASON")
	private String orderReturnReason;
	
	@Column(name="ORDER_RETURN_STATUS")
	private String orderReturnStatus;

	public OrderReturnEntity(OrderReturnPK orderReturnPK, String orderReturnTime, String orderReturnReason,
			String orderReturnStatus) {
		super();
		this.orderReturnPK = orderReturnPK;
		this.orderReturnTime = orderReturnTime;
		this.orderReturnReason = orderReturnReason;
		this.orderReturnStatus = orderReturnStatus;
	}

	public OrderReturnPK getOrderReturnPK() {
		return orderReturnPK;
	}

	public void setOrderReturnPK(OrderReturnPK orderReturnPK) {
		this.orderReturnPK = orderReturnPK;
	}

	public String getOrderReturnTime() {
		return orderReturnTime;
	}

	public void setOrderReturnTime(String orderReturnTime) {
		this.orderReturnTime = orderReturnTime;
	}

	public String getOrderReturnReason() {
		return orderReturnReason;
	}

	public void setOrderReturnReason(String orderReturnReason) {
		this.orderReturnReason = orderReturnReason;
	}

	public String getOrderReturnStatus() {
		return orderReturnStatus;
	}

	public void setOrderReturnStatus(String orderReturnStatus) {
		this.orderReturnStatus = orderReturnStatus;
	}

	}