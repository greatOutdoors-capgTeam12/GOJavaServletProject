package com.capgemini.go.dao;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.FrequentOrderedDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.utility.Constants;
import com.capgemini.go.utility.DbConnection;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.PropertiesLoader;

public class RetailerDaoImpl implements RetailerDao {

	private static Properties exceptionProps = null;
	private static Properties goProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";
	private static final String GO_PROPERTIES_FILE = "goUtility.properties";
	private boolean ITEM_ADDED = true;
	private boolean ITEM_NOT_ADDED = false;

	// ------------------------ GreatOutdoor Application
	

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

	@Override

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

		boolean addProductToFreqOrderDB = false;
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			String userID = freqOrder.getRetailerID();
			String productID = freqOrder.getProductID();

			/**
			 * Adding Product to Frequently order list
			 */

			PreparedStatement Statement = connection.prepareStatement(QuerryMapper.ADD_FREQ_PRODUCT_TO_DB);
			Statement.setString(1, userID);
			Statement.setString(2, productID);

			PreparedStatement Stmt = connection.prepareStatement(QuerryMapper.PRODUCTS_IN_FAVS);
			Stmt.setString(1, userID);
			ResultSet res = Stmt.executeQuery();

			if (res.next() == false) {
				Statement.executeUpdate();
			} else {
				do {
					if (!productID.equals(res.getString(1))) {
						Statement.executeUpdate();
						break;
					}
				} while (res.next());
			}
			addProductToFreqOrderDB = true;
		}

		catch (DatabaseException | SQLException | IOException e) {

			throw new RetailerException(exceptionProps.getProperty("whishlist_error"));
		}

		finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}
		return addProductToFreqOrderDB;
	}
	/*******************************************************************************************************
	 * Function Name : placeOrder 
	 * Input Parameters : Order
	 * Return Type :boolean 
	 * Throws :  RetailerException
	 * Author : Agnibha , Azhar -
	 * Creation Date : 21/9/2019 
	 * Description : to place order for items in the cart
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	@Override
	public boolean placeOrder(OrderDTO order) throws RetailerException, ConnectException {
		boolean checkOutStatus = false;
		boolean isCartEmpty = true;
		String retailerID = order.getUserId();
		String addressID = order.getAddressId();
		String orderID = order.getOrderId();
		Date ordInitTime = order.getOrderInitiationTime();
		PreparedStatement prestmt = null;
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			prestmt = connection.prepareStatement(QuerryMapper.GET_CART_ITEM);
			prestmt.setString(1, retailerID);
			ResultSet resultset = prestmt.executeQuery();
			PreparedStatement initOrd = connection.prepareStatement(QuerryMapper.INITIATE_ORDER);
			initOrd.setString(1, orderID);
			initOrd.setString(2, retailerID);
			initOrd.setString(3, addressID);
			initOrd.setDate(4, ordInitTime);
			initOrd.executeUpdate();
			while (resultset.next()) {
				isCartEmpty = false;
				String prodId = resultset.getString(1);
				int qty = resultset.getInt(2);
				prestmt = connection.prepareStatement(QuerryMapper.GET_PRODUCT_DETAILS);
				prestmt.setString(1, prodId);
				prestmt.setInt(2, qty);
				ResultSet rset = prestmt.executeQuery();
				boolean isProductAvailable = false;
				while (rset.next()) {
					isProductAvailable = true;
					PreparedStatement updtProdStr = connection.prepareStatement(QuerryMapper.UPDATE_PRODUCT_STORE);
					updtProdStr.setString(1, rset.getString(1));
					updtProdStr.executeUpdate();
					PreparedStatement updtOrdProdMap = connection
							.prepareStatement(QuerryMapper.UPDATE_ORDER_PRODUCT_MAP_ENTRY);
					updtOrdProdMap.setString(1, orderID);
					updtOrdProdMap.setString(2, prodId);
					updtOrdProdMap.setString(3, rset.getString(1));
					updtOrdProdMap.executeUpdate();
				}
				if (isProductAvailable == false) {
					GoLog.logger.error(exceptionProps.getProperty("prod_not_available"));
					throw new RetailerException(exceptionProps.getProperty("prod_not_available"));
				}
				PreparedStatement updtProdInv = connection.prepareStatement(QuerryMapper.UPDATE_PRODUCT_INVENTORY);
				updtProdInv.setInt(1, qty);
				updtProdInv.setString(2, prodId);
				updtProdInv.executeUpdate();
			}
			if (isCartEmpty == true) {
				PreparedStatement delOrd = connection.prepareStatement(QuerryMapper.DELETE_ORDER);
				delOrd.setString(1, orderID);
				GoLog.logger.error(exceptionProps.getProperty("cart_empty"));
				throw new RetailerException(exceptionProps.getProperty("cart_empty"));
			}
			PreparedStatement delcart = connection.prepareStatement(QuerryMapper.DELETE_CART);
			delcart.setString(1, retailerID);
			delcart.executeUpdate();
			checkOutStatus = true;

		} catch (DatabaseException | RetailerException | SQLException | IOException e) {

			GoLog.logger.error(e.getMessage());
			throw new RetailerException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		return checkOutStatus;
	}

	
	/*******************************************************************************************************
	 * Function Name : addItemToCart 
	 * Input Parameters : CartDTO
	 * Return Type : boolean
	 * Throws : RetailerException
	 * Author : Agnibha, Azhar 
	 * Creation Date : 27/9/2019
	 * Description : to add item to a cart
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	@Override
	public boolean addItemToCart(CartDTO cartItem) throws RetailerException, ConnectException {
		boolean itemAddedToCart = false;
		String retailerId = cartItem.getRetailerId();
		String productId = cartItem.getProductId();
		int quantity = cartItem.getQuantity();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			PreparedStatement isProdPres = connection.prepareStatement(QuerryMapper.GET_PROD_PRESENT_STATUS);
			isProdPres.setString(1, productId);
			isProdPres.setString(2, retailerId);
			ResultSet rset = isProdPres.executeQuery();
			rset.next();
			if (rset.getInt(1) == 1) {
				PreparedStatement cartItemQty = connection.prepareStatement(QuerryMapper.CART_ITEM_QTY);
				cartItemQty.setString(1, productId);
				cartItemQty.setString(2, retailerId);
				ResultSet resultSet = cartItemQty.executeQuery();
				resultSet.next();
				int beforeQty = resultSet.getInt(1);
				quantity = quantity + beforeQty;
				PreparedStatement getProdCount = connection.prepareStatement(QuerryMapper.GET_PROD_QTY);
				getProdCount.setString(1, productId);
				resultSet = getProdCount.executeQuery();
				resultSet.next();
				int initQty = resultSet.getInt(1);
				if (initQty > quantity) {
					PreparedStatement addCartItem = connection.prepareStatement(QuerryMapper.INCREASE_CART_ITEM);
					addCartItem.setString(2, retailerId);
					addCartItem.setString(3, productId);
					addCartItem.setInt(1, quantity);
					addCartItem.executeUpdate();
					itemAddedToCart = true;
				} else {
					GoLog.logger.error(exceptionProps.getProperty("prod_not_available"));
					throw new RetailerException(exceptionProps.getProperty("prod_not_available"));
				}
			} else {
				PreparedStatement getProdCount = connection.prepareStatement(QuerryMapper.GET_PROD_QTY);
				getProdCount.setString(1, productId);
				ResultSet resultSet = getProdCount.executeQuery();
				resultSet.next();
				int initQty = resultSet.getInt(1);
				if (initQty > quantity) {
					PreparedStatement addCartItem = connection.prepareStatement(QuerryMapper.ADD_ITEM_TO_CART);
					addCartItem.setString(1, retailerId);
					addCartItem.setString(2, productId);
					addCartItem.setInt(3, quantity);
					addCartItem.executeUpdate();
					itemAddedToCart = true;
				} else {
					GoLog.logger.error(exceptionProps.getProperty("prod_not_available"));
					throw new RetailerException(exceptionProps.getProperty("prod_not_available"));
				}
			}

		} catch (DatabaseException | RetailerException | IOException | SQLException e) {

			throw new RetailerException(e.getMessage());

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		return itemAddedToCart;
	}
	// end of Azhar Functions

	// Functions for Retailer Inventory Manipulation
	@Override
	public boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments) throws ConnectException {
		int querySuccess = -1;
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(QuerryMapper.UPDATE_PRODUCT_RECEIVE_TIMESTAMP_BY_RETAILER_ID_AND_PRODUCT_UIN);
			// filling query statement fields
			java.sql.Date c = new java.sql.Date(queryArguments.getProductRecieveTime().getTimeInMillis());
			stmt.setString(1, c.toString());
			stmt.setString(2, queryArguments.getRetailerUserId());
			stmt.setString(3, queryArguments.getProductUIN());
			// end of filling query statement fields
			querySuccess = stmt.executeUpdate();
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}
		if (querySuccess > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments) throws ConnectException {
		int querySuccess = -1;
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(QuerryMapper.UPDATE_PRODUCT_SALE_TIMESTAMP_BY_RETAILER_ID_AND_PRODUCT_UIN);
			// filling query statement fields
			java.sql.Date c = new java.sql.Date(queryArguments.getProductShelfTimeOut().getTimeInMillis());
			stmt.setString(1, c.toString());
			stmt.setString(2, queryArguments.getRetailerUserId());
			stmt.setString(3, queryArguments.getProductUIN());
			// end of filling query statement fields
			querySuccess = stmt.executeUpdate();
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}
		if (querySuccess > 0) {
			return true;
		} else {
			return false;
		}
	}
	// end of Functions for Retailer Inventory Manipulation

	public boolean validateRetailerID(UserDTO user) throws UserException, ConnectException {
		boolean existsRetailerId = false;
		String retailerID = user.getUserId();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			PreparedStatement statement = connection.prepareStatement(QuerryMapper.USER_EXISTS);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				if (retailerID.equals(resultset.getString(2))) {
					existsRetailerId = true;
					break;
				}
			}
		} catch (DatabaseException | IOException | SQLException e) {
			GoLog.logger.error(exceptionProps.getProperty(EXCEPTION_PROPERTIES_FILE));
			throw new UserException("....." + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}
		return existsRetailerId;
	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : addAddress 
	 * - Input Parameters : address
	 * - Return Type : boolean
	 * - Throws : ConnectException
	 * - Author : CAPGEMINI
	 * - Creation Date : 21/9/2019 
	 * - Description : to add address to the database
	 ********************************************************************************************************/
	public boolean addAddress(AddressDTO address) throws RetailerException, ConnectException {
		boolean addAddressState = address.isAddressStatus();
		String addressID = address.getAddressId();
		String retailerID = address.getRetailerId();
		String city = address.getCity();
		String state = address.getState();
		String zip = address.getZip();
		String buildingNum = address.getBuildingNo();
		String country = address.getCountry();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			PreparedStatement statement = connection.prepareStatement(QuerryMapper.INSERT_NEW_ADDRESS_IN_ADDRESSDB);
			statement.setString(1, addressID);
			statement.setString(2, retailerID);
			statement.setString(3, city);
			statement.setString(4, state);
			statement.setString(5, zip);
			statement.setString(6, buildingNum);
			statement.setString(7, country);
			statement.setBoolean(8, addAddressState);
			int row = statement.executeUpdate();
			if (row == 1)
				return true;
		} catch (DatabaseException | IOException | SQLException e)// SQLException
		{
			GoLog.logger.error(exceptionProps.getProperty(EXCEPTION_PROPERTIES_FILE));
			throw new RetailerException("....." + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		return addAddressState;
	}

	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : updateAddress
	 * - Input Parameters : address
	 * - Return Type :boolean 
	 * - Throws :RetailerException,ConnectException
	 * - Author : CAPGEMINI
	 * - Creation Date : 21/9/2019 
	 * -Description : to update address to the database
	 ********************************************************************************************************/
	public boolean updateAddress(AddressDTO address) throws RetailerException, ConnectException {
		boolean addAddressState = true;
		String addressID = address.getAddressId();
		String retailerID = address.getRetailerId();
		String city = address.getCity();
		String state = address.getState();
		String zip = address.getZip();
		String buildingNum = address.getBuildingNo();
		String country = address.getCountry();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			PreparedStatement statement = connection
					.prepareStatement(QuerryMapper.CHECK_USERID_AND_ADDRESSID_IN_ADDRESSDB);

			statement.setString(1, addressID);
			statement.setString(2, retailerID);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == true) {

				PreparedStatement statement2 = connection.prepareStatement(QuerryMapper.UPDATE_ADDRESS_IN_ADDRESSDB);
				statement2.setString(9, addressID);
				if (rs.getString(1).equals(addressID) && rs.getString(2).equals(retailerID)) {

					statement2.setString(1, addressID);
					statement2.setString(2, retailerID);
					statement2.setString(3, city);
					statement2.setString(4, state);
					statement2.setString(5, zip);
					statement2.setString(6, buildingNum);
					statement2.setString(7, country);
					statement2.setBoolean(8, addAddressState);
				}

				int row = 0;
				row = statement2.executeUpdate();

				if (row == 1) {
					return true;
				} else {
					System.out.println("cannot update address");
				}

			}

		} catch (DatabaseException | IOException | SQLException e)// SQLException
		{
			GoLog.logger.error(exceptionProps.getProperty(EXCEPTION_PROPERTIES_FILE));
			throw new RetailerException("....." + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		return addAddressState;
	}

	
	// ------------------------ GreatOutdoor Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : deleteAddress
	 * - Input Parameters : address
	 * - Return Type : boolean 
	 * - Throws :RetailerException,ConnectException
	 * - Author : CAPGEMINI
	 * - Creation Date : 21/9/2019 
	 * -Description : to delete address of a particular order in the database
	 ********************************************************************************************************/

	public boolean deleteAddress(AddressDTO address) throws RetailerException, ConnectException {
		String addressID = address.getAddressId();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			PreparedStatement statement = connection.prepareStatement(QuerryMapper.DELETE_ADDRESS_IN_ADDRESSDB);
			statement.setString(1, addressID);
			int row = 0;
			row = statement.executeUpdate();

			if (row == 1)
				return true;
		}

		catch (DatabaseException | IOException | SQLException e)// SQLException
		{
			GoLog.logger.error(exceptionProps.getProperty(EXCEPTION_PROPERTIES_FILE));
			throw new RetailerException("....." + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		return false;
	}

//------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : displayAddress - Input Parameters : userId - Return Type :
	 * AddressList - Throws : Retailer Excpetion - Author : AGNIBHA/AMAN/AYUSHI -
	 * Creation Date : 21/9/2019 - Description : to get all the address of a user
	 * from the database
	 * 
	 * @throws RetailerException
	 * @throws ConnectException
	 * 
	 ********************************************************************************************************/
	@Override
	public List<AddressDTO> viewAllAddress(String userId) throws RetailerException, ConnectException {
		List<AddressDTO> allAddress = new ArrayList<AddressDTO>();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			PreparedStatement smt = connection.prepareStatement(QuerryMapper.GET_USER_ADDRESSS);
			smt.setString(1, userId);
			ResultSet resultSet = smt.executeQuery();
			while (resultSet.next()) {
				String addressId = resultSet.getString(1);
				String city = resultSet.getString(3);
				String state = resultSet.getString(4);
				String zip = resultSet.getString(5);
				String building_no = resultSet.getString(6);
				String country = resultSet.getString(7);
				boolean addressStatus = resultSet.getBoolean(8);
				AddressDTO address = new AddressDTO(addressId, userId, city, state, zip, building_no, country,
						addressStatus);
				allAddress.add(address);
			}
		} catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(e.getMessage());
			throw new RetailerException(exceptionProps.getProperty("view_address_error") + " >>> " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}
		return allAddress;

	}

	@Override
	public int getAddressCountByRetailer(String retailerId) {
		int result = 0;
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.COUNT_USERID_IN_ADDRESSDB);
			statement.setString(1, retailerId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getInt(2);
		} catch (DatabaseException | IOException | SQLException e) {
			// GoLog.logger.error(exceptionProps.getProperty(EXCEPTION_PROPERTIES_FILE));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// throw new ConnectException(Constants.connectionError);
			}
		}
		return result;
	}

	@Override
	public List<ProductDTO> fetchfavproduct(String userId) throws UserException {
		Connection connection = null;
		List<ProductDTO> allfavproduct = new ArrayList<ProductDTO>();
		try {
			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			PreparedStatement presmt = connection.prepareStatement(QuerryMapper.VIEW_FAV);
			presmt.setString(1, userId);
			ResultSet resultSet = presmt.executeQuery();
			while (resultSet.next()) {
				String productid = resultSet.getString(1);
				double price = resultSet.getDouble(2);
				String colour = resultSet.getString(3);
				String dimension = resultSet.getString(4);
				String specification = resultSet.getString(6);
				String manufacturer = resultSet.getString(8);
				int quantity = resultSet.getInt(5);
				int productCategory = resultSet.getInt(7);
				String productName = resultSet.getString(9);
				ProductDTO favproduct = new ProductDTO(productid, price, colour, dimension, specification, manufacturer,
						quantity, productCategory, productName);
				allfavproduct.add(favproduct);
			}

		}

		catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(e.getMessage());
			throw new UserException(exceptionProps.getProperty("filter_product_error") + " >>> " + e.getMessage());
		}

		return allfavproduct;
	}
}
