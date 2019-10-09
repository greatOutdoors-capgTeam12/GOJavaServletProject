package com.capgemini.go.service;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dao.RetailerDao;
import com.capgemini.go.dao.RetailerDaoImpl;
import com.capgemini.go.dao.UserDao;
import com.capgemini.go.dao.UserDaoImpl;
import com.capgemini.go.dto.ProductFilterDTO;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.PropertiesLoader;

public class UserServiceImpl implements UserService {

	private static Properties exceptionProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";

	private UserDao user = new UserDaoImpl();

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userRegistration - Input Parameters : userID, userName,
	 * userMail, userNumber, activeStatus, password, userCategory - Return Type :
	 * boolean - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : to register a new user
	 * 
	 * @throws UserException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public boolean userRegistration(UserDTO newuser) throws UserException, ConnectException {
		boolean regStatus = false;
		try {
			regStatus = user.userRegistration(newuser);
		} catch (UserException e) {
			throw e;
		}
		return regStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogin - Input Parameters : userID, password - Return
	 * Type : boolean - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : to login a user
	 * 
	 * @throws Exception
	 ********************************************************************************************************/

	public boolean userLogin(UserDTO existingUser) throws Exception {
		boolean userLoginStatus = false;
		try {
			userLoginStatus = user.userLogin(existingUser);
		} catch (UserException e) {
			throw new Exception (e.getMessage());
		}
		return userLoginStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogout - Input Parameters : userID- Return Type :
	 * boolean - Throws :UserExecution - Author : CAPGEMINI - Creation Date :
	 * 21/9/2019 - Description : to logout a user
	 * 
	 * @throws UserException
	 * @throws SQLException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	@Override
	public boolean userLogout(UserDTO userLoggingOut) throws UserException, SQLException, ConnectException {
		boolean userLogoutStatus = false;
		try {
			userLogoutStatus = user.userLogout(userLoggingOut);
		} catch (UserException e) {
			throw e;
		}
		return userLogoutStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getAllProducts - Input Parameters : - Return Type : Product
	 * List - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : to get all products from the database
	 * 
	 * @throws UserException
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public List<ProductBean> getAllProducts() throws UserException, ConnectException {

		List<ProductBean> allProducts;

		allProducts = user.getAllProducts();

		return allProducts;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : searchProduct - Input Parameters : product name , userId -
	 * Return Type : Product List - Throws : - Author : CAPGEMINI - Creation Date :
	 * 21/9/2019 - Description : to search product according to the product name
	 * 
	 * @throws UserException
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public List<ProductBean> searchProduct(String productName) throws UserException, ConnectException {
		List<ProductBean> searchProducts;
		searchProducts = user.searchProduct(productName);

		return searchProducts;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : filterProduct - Input Parameters : filterProduct - Return
	 * Type : Product List - Throws : UserException - Author : CAPGEMINI - Creation
	 * Date : 21/9/2019 - Description : to filter product according to the filter
	 * category
	 * 
	 * @throws UserException
	 ********************************************************************************************************/
	public List<ProductBean> filterProduct(ProductFilterDTO filterProduct) throws UserException {

		List<ProductBean> filterProducts;

		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			if (filterProduct.getLowRange() > filterProduct.getHighRange()) {
				throw new UserException(exceptionProps.getProperty("price_range_mismatch"));
			}

			filterProducts = user.filterProduct(filterProduct);
		} catch (UserException | IOException e) {

			throw new UserException(e.getMessage());
		}

		return filterProducts;

	}

	// Functions for Retailer Inventory Manipulation
	public boolean updateProductReceived(String retailerId, String productUIN) throws ConnectException {
		Calendar today = Calendar.getInstance();
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO(retailerId, 0, productUIN, null, today, null);
		RetailerDao retailer = new RetailerDaoImpl();
		if (retailer.updateProductReceiveTimeStamp(queryArguments)) {
			java.sql.Date c = new java.sql.Date(today.getTimeInMillis());
			GoLog.logger.debug("Product Successfully Received by Retailer with ID = " + retailerId + ", Time Stamp = "
					+ c.toString());
			return true;
		} else {
			GoLog.logger.debug("Product Received Update Failed for Retailer with ID = " + retailerId);
			return false;
		}
	}

	public boolean updateProductSale(String retailerId, String productUIN) throws ConnectException {
		Calendar today = Calendar.getInstance();
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO(retailerId, 0, productUIN, null, null, today);
		RetailerDao retailer = new RetailerDaoImpl();
		if (retailer.updateProductSaleTimeStamp(queryArguments)) {
			java.sql.Date c = new java.sql.Date(today.getTimeInMillis());
			GoLog.logger.debug("Product Successfully Received by Retailer with ID = " + retailerId + ", Time Stamp = "
					+ c.toString());
			return true;
		} else {
			GoLog.logger.debug("Product Received Update Failed for Retailer with ID = " + retailerId);
			return false;
		}
	}
	// end of Functions for Retailer Inventory Manipulation
}
