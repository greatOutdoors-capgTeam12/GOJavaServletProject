package com.capgemini.go.service;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dto.ProductFilterDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.UserException;

public interface UserService {

	boolean userRegistration(UserDTO user) throws UserException,ConnectException;

	boolean userLogin(UserDTO user) throws UserException, Exception;

	boolean userLogout(UserDTO user) throws UserException, SQLException,ConnectException;

	List<ProductBean> getAllProducts() throws UserException,ConnectException;

	List<ProductBean> searchProduct(String productName) throws UserException,ConnectException;

	List<ProductBean> filterProduct(ProductFilterDTO filterProduct ) throws UserException,ConnectException;

	// Functions for Retailer Inventory Manipulation
	public boolean updateProductReceived(String retailerId, String productUIN)throws ConnectException;

	public boolean updateProductSale(String retailerId, String productUIN)throws ConnectException;
	// end of Functions for Retailer Inventory Manipulation
}
