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

	boolean returnOrder(String userId, String reason, String orderId, Date date)throws ConnectException;

	List<ProductDTO> returnProductByRetailer(String userId, String orderId, List<ProductDTO> acceptedProducts)throws ConnectException;

	boolean validateRetailerID(UserDTO user) throws UserException,ConnectException;

	boolean addAddress(AddressDTO address) throws RetailerException,ConnectException;

	boolean updateAddress(AddressDTO address) throws RetailerException,ConnectException;

	boolean changeAddress(AddressDTO address, String orderId) throws RetailerException,ConnectException;

	boolean deleteAddress(AddressDTO address) throws RetailerException,ConnectException;

	boolean addProductToFreqOrderDB(FrequentOrderedDTO freqOrder) throws RetailerException,ConnectException;

	void changeProductAddress(FrequentOrderedDTO freqOrder) throws RetailerException,ConnectException;

	boolean addItemToCart(CartDTO cartItem) throws RetailerException,ConnectException;

	boolean placeOrder(OrderDTO order) throws RetailerException,ConnectException;

	// Functions for Retailer Inventory Manipulation
	boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments)throws ConnectException,ConnectException;

	boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments)throws ConnectException,ConnectException;
	// end of Functions for Retailer Inventory Manipulation

}
