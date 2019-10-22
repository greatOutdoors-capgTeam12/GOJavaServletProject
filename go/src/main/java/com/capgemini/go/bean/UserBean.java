package com.capgemini.go.bean;

import java.io.Serializable;
import java.util.Arrays;

public class UserBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8614226498233009408L;
	private String userName;
	private String userId;
	private String userMail;
	private String userPassword;
	private long userNumber;
	/**
	 * 1 => Go ADMIN
	 * 2 => SALES_REPRESENTATIVE
	 * 3 => RETAILER
	 * 4 => PRODUCT MASTER
	 */
	private int userCategory;
	private boolean userActiveStatus;

	public UserBean(String userName, String userId, String userMail, String userPassword, long userNumber, int userCategory,
			boolean userActiveStatus) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.userMail = userMail;
		this.userPassword = userPassword;
		this.userNumber = userNumber;
		this.userCategory = userCategory;
		this.userActiveStatus = userActiveStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public long getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(long userNumber) {
		this.userNumber = userNumber;
	}

	public int getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(int userCategory) {
		this.userCategory = userCategory;
	}

	public boolean isUserActiveStatus() {
		return userActiveStatus;
	}

	public void setUserActiveStatus(boolean userActiveStatus) {
		this.userActiveStatus = userActiveStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (userActiveStatus ? 1231 : 1237);
		result = prime * result + userCategory;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userMail == null) ? 0 : userMail.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + (int) (userNumber ^ (userNumber >>> 32));
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
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
		UserBean other = (UserBean) obj;
		if (userActiveStatus != other.userActiveStatus)
			return false;
		if (userCategory != other.userCategory)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userMail == null) {
			if (other.userMail != null)
				return false;
		} else if (!userMail.equals(other.userMail))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userNumber != other.userNumber)
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String UserCategoryStream[] = Arrays.stream(UserCategory.values()).map(Enum::name).toArray(String[]::new);
		return "UserBean [userName=" + userName + ", userId=" + userId + ", userMail=" + userMail + ", userPassword="
				+ userPassword + ", userNumber=" + userNumber + ", userCategory=" + UserCategoryStream[userCategory-1] + ", userActiveStatus="
				+ userActiveStatus + "]";
	}
	
}
