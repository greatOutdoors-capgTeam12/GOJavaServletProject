package com.capgemini.go.dto;

public class SalesRepDTO {

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
	public SalesRepDTO() {

	}

	public SalesRepDTO(String userId, double bonus, double target, int targetStatus, String cartId,
			double currentTargetStatus) {
		super();
		this.userId = userId;
		this.bonus = bonus;
		this.target = target;
		this.targetStatus = targetStatus;
		this.cartId = cartId;
		this.currentTargetStatus = currentTargetStatus;
	}

	/**
	 * @param userId
	 */
	public SalesRepDTO(String userId) {
		super();
		this.userId = userId;
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

	
	public void printData() {

		System.out.printf("%-25s %-25.2f %-25d %-25.2f %n", userId, target, targetStatus, currentTargetStatus);
	}

}