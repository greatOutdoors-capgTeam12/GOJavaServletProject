package com.capgemini.go.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesRep")
public class SalesRepEntity {

	// attributes
	@Id
	@Column(name = "USER_ID", unique = false, nullable = true)
	private String userId;
	@Column(name = "BONUS", unique = false, nullable = false)
	private Double bonus;
	@Column(name = "TARGET", unique = false, nullable = false)
	private Double target;
	@Column(name = "TARGET_STATUS", unique = false, nullable = false)
	private String targetStatus;
	@Column(name = "CART_ID", unique = false, nullable = false)
	private String cartId;

	@Column(name = "CURRENT_SALES", unique = false, nullable = false)
	private Double currentSales;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getTarget() {
		return target;
	}

	public void setTarget(Double target) {
		this.target = target;
	}

	public String getTargetStatus() {
		return targetStatus;
	}

	public void setTargetStatus(String targetStatus) {
		this.targetStatus = targetStatus;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Double getCurrentSales() {
		return currentSales;
	}

	public void setCurrentSales(Double currentSales) {
		this.currentSales = currentSales;
	}

	public SalesRepEntity(String userId, Double bonus, Double target, String targetStatus, String cartId,
			Double currentSales) {
		super();
		this.userId = userId;
		this.bonus = bonus;
		this.target = target;
		this.targetStatus = targetStatus;
		this.cartId = cartId;
		this.currentSales = currentSales;
	}

	@Override
	public String toString() {
		return "SalesRepEntity [userId=" + userId + ", bonus=" + bonus + ", target=" + target + ", targetStatus="
				+ targetStatus + ", cartId=" + cartId + ", currentSales=" + currentSales + "]";
	}

}
