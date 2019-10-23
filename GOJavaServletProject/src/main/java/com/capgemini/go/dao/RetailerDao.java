package com.capgemini.go.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.FrequentOrderedDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.UserException;

public interface RetailerDao {

	

	List<ProductDTO> returnProductByRetailer(String userId, String orderId, List<ProductDTO> acceptedProducts)throws ConnectException;

	boolean validateRetailerID(UserDTO user) throws UserException,ConnectException;

	boolean addAddress(AddressDTO address) throws RetailerException,ConnectException;

	boolean updateAddress(AddressDTO address) throws RetailerException,ConnectException;

	

	boolean deleteAddress(AddressDTO address) throws RetailerException,ConnectException;
	
	/*******************************************************************************************************
	 *- Function Name : addProductToWishlist 
	 *- Input Parameters : Product List
	 *- Return Type : boolean
	 *- Throws : RetailerException
	 *- Author : CAPGEMINI 
	 *- Creation Date : 21/9/2019
	 *- Description : To add products to Wishlist database
	 *@throws ConnectException 
	 *@throws SQLException 
	 ********************************************************************************************************/
	boolean addProductToWishlist(FrequentOrderedDTO freqOrder) throws RetailerException,ConnectException;


	boolean addItemToCart(CartDTO cartItem) throws RetailerException,ConnectException;

	boolean placeOrder(OrderDTO order) throws RetailerException,ConnectException;

	// Functions for Retailer Inventory Manipulation
	boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments)throws ConnectException,ConnectException;

	boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments)throws ConnectException,ConnectException;
	// end of Functions for Retailer Inventory Manipulation

	List<AddressDTO> viewAllAddress(String RetailerId) throws RetailerException, ConnectException;

	int getAddressCountByRetailer(String retailerId);

	List<ProductDTO> fetchfavproduct(String userId) throws UserException;

}
