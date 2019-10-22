package com.capgemini.go.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

import com.capgemini.go.dto.ProductDTO;

public class OrderBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5686588684360562747L;
	private String orderId;
	private String userId;
	private String addressId;
	private boolean orderPresent; // true -> present; false -> not present
	private boolean orderDispatched; // true -> dispatched; false -> not dispatched
	private Calendar orderInitiationTime;
	private Calendar orderDispatchTime;
	private HashMap<ProductDTO, Integer> prodQtyList;

	public OrderBean(String orderId, String userId, String addressId, boolean orderPresent, boolean orderDispatched,
			Calendar orderInitiationTime, Calendar orderDispatchTime, HashMap<ProductDTO, Integer> prodQtyList) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.addressId = addressId;
		this.orderPresent = orderPresent;
		this.orderDispatched = orderDispatched;
		this.orderInitiationTime = orderInitiationTime;
		this.orderDispatchTime = orderDispatchTime;
		this.prodQtyList = prodQtyList;
	}

	// getters
	public String getOrderId() {
		return orderId;
	}

	public String getUserId() {
		return userId;
	}

	public String getAddressId() {
		return addressId;
	}

	public boolean isOrderDispatched() {
		return orderDispatched;
	}

	public boolean isOrderPresent() {
		return orderPresent;
	}

	public Calendar getOrderDispatchTime() {
		return orderDispatchTime;
	}

	public Calendar getOrderInitiationTime() {
		return orderInitiationTime;
	}

	// setters
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setOrderDispatched(boolean orderDispatched) {
		this.orderDispatched = orderDispatched;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setOrderPresent(boolean orderPresent) {
		this.orderPresent = orderPresent;
	}

	public void setOrderInitiationTime(Calendar orderInitiationTime) {
		this.orderInitiationTime = orderInitiationTime;
	}

	public void setOrderDispatchTime(Calendar orderDispatchTime) {
		this.orderDispatchTime = orderDispatchTime;
	}

	// constructors
	public OrderBean() {
		this.orderId = this.userId = this.addressId = null;
		this.orderPresent = this.orderDispatched = false;
		this.orderInitiationTime = this.orderDispatchTime = null;
	}

	public HashMap<ProductDTO, Integer> getProdQtyList() {
		return prodQtyList;
	}

	public void setProdQtyList(HashMap<ProductDTO, Integer> prodQtyList) {
		this.prodQtyList = prodQtyList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result + ((orderDispatchTime == null) ? 0 : orderDispatchTime.hashCode());
		result = prime * result + (orderDispatched ? 1231 : 1237);
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderInitiationTime == null) ? 0 : orderInitiationTime.hashCode());
		result = prime * result + (orderPresent ? 1231 : 1237);
		result = prime * result + ((prodQtyList == null) ? 0 : prodQtyList.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderBean other = (OrderBean) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (orderDispatchTime == null) {
			if (other.orderDispatchTime != null)
				return false;
		} else if (!orderDispatchTime.equals(other.orderDispatchTime))
			return false;
		if (orderDispatched != other.orderDispatched)
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderInitiationTime == null) {
			if (other.orderInitiationTime != null)
				return false;
		} else if (!orderInitiationTime.equals(other.orderInitiationTime))
			return false;
		if (orderPresent != other.orderPresent)
			return false;
		if (prodQtyList == null) {
			if (other.prodQtyList != null)
				return false;
		} else if (!prodQtyList.equals(other.prodQtyList))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderBean [orderId=" + orderId + ", userId=" + userId + ", addressId=" + addressId + ", orderPresent="
				+ orderPresent + ", orderDispatched=" + orderDispatched + ", orderInitiationTime=" + orderInitiationTime
				+ ", orderDispatchTime=" + orderDispatchTime + ", prodQtyList=" + prodQtyList + "]";
	}

}
