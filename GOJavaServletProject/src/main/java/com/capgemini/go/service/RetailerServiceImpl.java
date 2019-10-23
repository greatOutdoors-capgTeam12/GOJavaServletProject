package com.capgemini.go.service;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

import com.capgemini.go.dao.RetailerDao;
import com.capgemini.go.dao.RetailerDaoImpl;
import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.FrequentOrderedDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.ConnectionException;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.utility.PropertiesLoader;

public class RetailerServiceImpl implements RetailerService {

	private static Properties exceptionProps = null;

	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";

	private RetailerDao retailer = new RetailerDaoImpl();

	private CartDTO cartItem;
	private ProductDTO product;
	private boolean ITEM_ADDED = true;
	private boolean ITEM_NOT_ADDED = false;

	private RetailerDao retailerdao = new RetailerDaoImpl();

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : returnOrder - Input Parameters : userID, reason, orderID,
	 * Date - Return Type : boolean - Throws : - Author : CAPGEMINI - Creation Date
	 * : 21/9/2019 - Description : to return the order received by the retailer
	 ********************************************************************************************************/

	public boolean returnOrder(String userId, String reason, String orderId, Date date) {

		return false;
	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : returnProductByRetailer - Input Parameters : userID,
	 * orderID, Accepted Product List - Return Type : Rejected Product List- Throws
	 * : - Author : CAPGEMINI - Creation Date : 21/9/2019 - Description : to return
	 * products received by the retailer
	 ********************************************************************************************************/

	public List<ProductDTO> returnProductByRetailer(String userId, String orderId, List<ProductDTO> acceptedProducts) {

		return null;
	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
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
	public boolean addProductToWishlist(FrequentOrderedDTO freqOrder) throws RetailerException, ConnectException {
		boolean addProdToFreqDbStatus = false;
		addProdToFreqDbStatus = retailer.addProductToWishlist(freqOrder);
		return addProdToFreqDbStatus;

	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : changeProductAddress - Input Parameters : Product product,
	 * Address address - Return Type : boolean - Throws : - Author : CAPGEMINI -
	 * Creation Date : 21/9/2019 - Description : To change the address linked with
	 * product
	 ********************************************************************************************************/
	public boolean changeProductAddress(String userId, ProductDTO product, AddressDTO address) {

		return false;
	}

	

	/*******************************************************************************************************
	 * - Function Name : addItemToCart - Input Parameters : productId, quantity,
	 * retailerId - Return Type : boolean - Throws : - Author : Azhar/Agnibha-
	 * Creation Date : 24/9/2019 - Description : To add product items to cart
	 * 
	 * @throws RetailerException
	 ********************************************************************************************************/
	public boolean addItemToCart(CartDTO cartItem) throws RetailerException, ConnectException {

		boolean addItemToCartStatus = false;
		addItemToCartStatus = retailer.addItemToCart(cartItem);

		return addItemToCartStatus;
	}

	/*******************************************************************************************************
	 * - Function Name : placingOrder - Input Parameters :userId, addressId - Return
	 * Type : boolean - Throws : - Author : Azhar/Agnibha - Creation Date :
	 * 24/9/2019 - Description : To place order
	 * 
	 * @throws RetailerException
	 ********************************************************************************************************/
	public boolean placeOrder(OrderDTO order) throws RetailerException, ConnectException {
		boolean placeOrderStatus = false;
		placeOrderStatus = retailer.placeOrder(order);
		return placeOrderStatus;
	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : validateRetailerID - Input Parameters : addressId - Return
	 * Type : boolean - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : to validate existence of retailerID in database
	 ********************************************************************************************************/

	public boolean validateRetailerID(UserDTO user) throws UserException {
		boolean existsRetailerId = false;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			existsRetailerId = retailerdao.validateRetailerID(user);
		} catch (UserException | IOException e) {
			throw new UserException("USER_INVALID" + e.getMessage());
		}
		return existsRetailerId;
	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : addAddress - Input Parameters :addressID, retailerId,
	 * buildingnum, city, state, country, zip, addressStatus - Return Type : boolean
	 * - Throws: - Author : CAPGEMINI - Creation Date : 21/9/2019 - Description : to
	 * add address into the database
	 ********************************************************************************************************/
	public boolean addAddress(AddressDTO address) throws RetailerException {
		boolean addressAddStatus = false;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			addressAddStatus = retailerdao.addAddress(address);
		} catch (RetailerException | IOException e) {
			e.printStackTrace();
			throw new RetailerException(exceptionProps.getProperty("ADDRESS_NOT_ADDED") + e.getMessage());
		}

		return addressAddStatus;
	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : updateAddress - Input Parameters :addressID, retailerId,
	 * buildingnum, city, state, country, zip- Return Type : boolean - Throws: -
	 * Author : CAPGEMINI - Creation Date : 21/9/2019 - Description : to update
	 * address into the database
	 ********************************************************************************************************/
	public boolean updateAddress(AddressDTO address) throws RetailerException {
		boolean addressUpdateStatus = false;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			addressUpdateStatus = retailerdao.updateAddress(address);
		} catch (RetailerException | IOException e) {
			throw new RetailerException(exceptionProps.getProperty("ADDRESS_NOT_UPDATED" + e.getMessage()));
		}

		return addressUpdateStatus;
	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : changeAddress - Input Parameters :addressID, retailerId,
	 * buildingnum, city, state, country, zip- Return Type : boolean - Throws: -
	 * Author : CAPGEMINI - Creation Date : 21/9/2019 - Description : to change
	 * address for particular order in database
	 ********************************************************************************************************/
	public boolean changeAddress(AddressDTO address, String orderId) throws RetailerException {
		boolean addressChangeStatus = false;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			addressChangeStatus = retailerdao.changeAddress(address, orderId);
		} catch (RetailerException | IOException e) {
			throw new RetailerException(exceptionProps.getProperty("ORDER_ADDRESS_NOT_CHANGED" + e.getMessage()));
		}

		return addressChangeStatus;

	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : deleteAddress - Input Parameters :addressID, retailerId,
	 * buildingnum, city, state, country, zip- Return Type : boolean - Throws: -
	 * Author : CAPGEMINI - Creation Date : 21/9/2019 - Description : to delete
	 * address for particular retailerId in database
	 ********************************************************************************************************/
	public boolean deleteAddress(AddressDTO address) throws RetailerException {
		boolean addressDeleteStatus = false;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			addressDeleteStatus = retailerdao.deleteAddress(address);
		} catch (RetailerException | IOException e) {
			throw new RetailerException(exceptionProps.getProperty("ORDER_ADDRESS_NOT_DELETED" + e.getMessage()));
		}

		return addressDeleteStatus;
	}
	
	//------------------------ GreatOutdoor Application --------------------------
		/*******************************************************************************************************
		 * - Function Name : displayAddress - Input Parameters : userId - Return Type : AddressList
		 * - Throws : Retailer Excpetion - Author : AGNIBHA/AMAN/AYUSHI - Creation Date : 21/9/2019 - Description
		 * : to get all the address of a user  from the database
		 * @throws RetailerException 
		 * @throws ConnectException 
		 * 
		 ********************************************************************************************************/
	@Override
	public List<AddressDTO> viewAllAddress(String userId) throws RetailerException
	{
		try
		{
			
				return  retailerdao.viewAllAddress(userId);
			
		}
		catch(RetailerException | ConnectException exp)
		{
			throw new RetailerException(exp.getMessage());
		}
		
	}
	
	@Override
	public int getAddressCountFromDB (String retailerId) {
		RetailerDao retailer = new RetailerDaoImpl();
		int addressCountforRetailer = retailer.getAddressCountByRetailer(retailerId);
		return addressCountforRetailer;
	}

	@Override
	public List<ProductDTO> fetchfavproduct(String userId) throws UserException {
		try
		{
			RetailerDao retailer = new RetailerDaoImpl();
			return retailer.fetchfavproduct(userId);
		}
		catch(Exception exp)
		{
			throw new UserException(exp.getMessage());
		}
	}
	

}
