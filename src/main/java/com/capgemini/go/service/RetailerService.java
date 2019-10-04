package com.capgemini.go.service;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.FrequentOrderedDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.UserException;

public interface RetailerService {

	boolean returnOrder(String userId, String reason, String orderId, Date date);

	List<ProductDTO> returnProductByRetailer(String userId, String orderId, List<ProductDTO> acceptedProducts);

	boolean validateRetailerID(UserDTO user) throws UserException;

	boolean addAddress(AddressDTO address) throws RetailerException;

	boolean updateAddress(AddressDTO address) throws RetailerException;

	boolean changeAddress(AddressDTO address, String orderId) throws RetailerException;

	boolean deleteAddress(AddressDTO address) throws RetailerException;

	boolean addProductToFreqOrderDB(FrequentOrderedDTO freqOrder) throws RetailerException, ConnectException;

	void changeProductAddress(FrequentOrderedDTO freqOrder) throws RetailerException, ConnectException;

	boolean addItemToCart(CartDTO cartItem) throws RetailerException, ConnectException;

	boolean placeOrder(OrderDTO order) throws RetailerException, ConnectException;

	boolean changeProductAddress(String userID, ProductDTO product, AddressDTO address);

}
