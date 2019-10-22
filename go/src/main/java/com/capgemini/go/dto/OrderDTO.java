package com.capgemini.go.dto;

import java.sql.Date;

public class OrderDTO {

	private String orderId;
	private String userId;
	private String addressId;
	private boolean orderDispatched; // true -> dispatched; false -> not dispatched
	private Date orderInitiationTime;
	private Date orderDispatchTime;

	/**
	 * @param orderId
	 * @param userId
	 * @param addressId
	 * @param orderDispatched
	 * @param orderInitiationTime
	 * @param orderDispatchTime
	 */
	public OrderDTO(String orderId, String userId, String addressId, boolean orderDispatched, Date orderInitiationTime,
			Date orderDispatchTime) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.addressId = addressId;
		this.orderDispatched = orderDispatched;
		this.orderInitiationTime = orderInitiationTime;
		this.orderDispatchTime = orderDispatchTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public boolean isOrderDispatched() {
		return orderDispatched;
	}

	public void setOrderDispatched(boolean orderDispatched) {
		this.orderDispatched = orderDispatched;
	}

	public Date getOrderInitiationTime() {
		return orderInitiationTime;
	}

	public void setOrderInitiationTime(Date orderInitiationTime) {
		this.orderInitiationTime = orderInitiationTime;
	}

	public Date getOrderDispatchTime() {
		return orderDispatchTime;
	}

	public void setOrderDispatchTime(Date orderDispatchTime) {
		this.orderDispatchTime = orderDispatchTime;
	}

}
