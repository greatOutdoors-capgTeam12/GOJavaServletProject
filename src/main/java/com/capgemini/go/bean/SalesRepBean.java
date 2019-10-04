package com.capgemini.go.bean;

import java.io.Serializable;

public class SalesRepBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8238188935904134188L;
	private String userId;
	private double bonus;
	private double target;
	private int targetStatus;
	private String cartId;
	private double currentTargetStatus;

	/**
	 * @param userId
	 * @param bonus
	 * @param target
	 * @param targetStatus
	 * @param cartId
	 * @param currentTargetStatus
	 */
	public SalesRepBean(String userId, double bonus, double target, int targetStatus, String cartId,
			double currentTargetStatus) {
		super();
		this.userId = userId;
		this.bonus = bonus;
		this.target = target;
		this.targetStatus = targetStatus;
		this.cartId = cartId;
		this.currentTargetStatus = currentTargetStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	public int getTargetStatus() {
		return targetStatus;
	}

	public void setTargetStatus(int targetStatus) {
		this.targetStatus = targetStatus;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public double getCurrentTargetStatus() {
		return currentTargetStatus;
	}

	public void setCurrentTargetStatus(double currentTargetStatus) {
		this.currentTargetStatus = currentTargetStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bonus);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		temp = Double.doubleToLongBits(currentTargetStatus);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(target);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + targetStatus;
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
		SalesRepBean other = (SalesRepBean) obj;
		if (Double.doubleToLongBits(bonus) != Double.doubleToLongBits(other.bonus))
			return false;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (Double.doubleToLongBits(currentTargetStatus) != Double.doubleToLongBits(other.currentTargetStatus))
			return false;
		if (Double.doubleToLongBits(target) != Double.doubleToLongBits(other.target))
			return false;
		if (targetStatus != other.targetStatus)
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
		return "SalesRepBean [userId=" + userId + ", bonus=" + bonus + ", target=" + target + ", targetStatus="
				+ targetStatus + ", cartId=" + cartId + ", currentTargetStatus=" + currentTargetStatus + "]";
	}
}
