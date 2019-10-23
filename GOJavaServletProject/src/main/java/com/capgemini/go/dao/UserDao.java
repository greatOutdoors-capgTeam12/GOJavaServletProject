package com.capgemini.go.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
//
import java.util.List;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dto.ProductFilterDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.UserException;

public interface UserDao {

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userRegistration 
	 * - Input Parameters : userID, userName, userMail, userNumber, activeStatus, password, userCategory 
	 * - Return Type : boolean 
	 * - Throws :UserException
	 *  - Author:AMAN 
	 *  - Creation Date : 21/9/2019 
	 *  - Description : to register a new user
	 * @throws ConnectException 
	 * @throws SQLException
	 ********************************************************************************************************/

	boolean userRegistration(UserDTO user) throws UserException,ConnectException;

	
	// ------------------------ GreatOutdoor Application --------------------------
		/*******************************************************************************************************
		 * - Function Name : userLogin 
		 * - Input Parameters : userID, password
		 * - Return Type : boolean 
		 * - Throws :UserException 
		 * - Author : AMAN 
		 * - Creation Date :21/9/2019 
		 * - Description : to login a user
		 * @throws UserException
		 * @throws Exception
		 ********************************************************************************************************/
	boolean userLogin(UserDTO user) throws UserException, Exception;
	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogout 
	 * - Input Parameters : userID 
	 * - Return Type : boolean 
	 * - Throws :UserException 
	 * - Author : AMAN 
	 * - Creation Date : 21/9/2019
	 * - Description : to logout a user
	 * @throws SQLException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	boolean userLogout(UserDTO user) throws UserException, SQLException,ConnectException;

	List<ProductBean> getAllProducts() throws UserException,ConnectException;

	List<ProductBean> searchProduct(String productName) throws UserException,ConnectException;

	List<ProductBean> filterProduct(ProductFilterDTO filterProduct) throws UserException,ConnectException;

	UserDTO fetchUser(String userId) throws UserException;

}
