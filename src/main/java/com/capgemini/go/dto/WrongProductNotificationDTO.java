package com.capgemini.go.dto;

import java.sql.Date;
import java.util.Arrays;

import com.capgemini.go.bean.UserCategory;

public class WrongProductNotificationDTO {

	private int count;
	private String productName;
	private String customerName;
	private int customerType;
	private Date returnDate;

	/**
	 * @param productName
	 * @param customerName
	 * @param customerType
	 * @param returnDate
	 */
	public WrongProductNotificationDTO(int count, String productName, String customerName, int customerType,
			Date returnDate) {
		super();
		this.count = count;
		this.productName = productName;
		this.customerName = customerName;
		this.customerType = customerType;
		this.returnDate = returnDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerType() {
		return customerType;
	}

	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		String UserCategoryStream[] = Arrays.stream(UserCategory.values()).map(Enum::name).toArray(String[]::new);
		return "WrongProductNotification [productName=" + productName + ", customerName=" + customerName
				+ ", customerType=" + UserCategoryStream[customerType - 1] + ", returnDate=" + returnDate + "]";
	}

}
