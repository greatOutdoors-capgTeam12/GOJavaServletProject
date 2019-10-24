package com.capgemini.go.dao;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dto.ProductFilterDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.entity.ProductEntity;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.Constants;
import com.capgemini.go.utility.DbConnection;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.HibernateUtil;
import com.capgemini.go.utility.PropertiesLoader;
import com.capgemini.go.utility.Validator;

public class UserDaoImpl implements UserDao {

	private static Properties exceptionProps = null;
	private static Properties goProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";
	private static final String GO_PROPERTIES_FILE = "goUtility.properties";

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : productDataMapping - Input Parameters : ResultSet - Return
	 * Type : Product - Throws : SQLException - Author : CAPGEMINI - Creation Date :
	 * 21/9/2019 - Description : to register a new user
	 ********************************************************************************************************/
	private ProductBean productDataMapping(ResultSet resultSet) throws SQLException {
		String productId = resultSet.getString(1);
		Double productPrice = resultSet.getDouble(2);
		String productColour = resultSet.getString(3);
		String productDimension = resultSet.getString(4);
		int productQuantity = resultSet.getInt(5);
		String productSpecification = resultSet.getString(6);
		int productCategory = resultSet.getInt(7);
		String productManufacturer = resultSet.getString(8);
		String productName = resultSet.getString(9);
		ProductBean product = new ProductBean(productId, productPrice, productColour, productDimension,
				productSpecification, productManufacturer, productQuantity, productCategory, productName);
		return (product);
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userRegistration - Input Parameters : userID, userName,
	 * userMail, userNumber, activeStatus, password, userCategory - Return Type :
	 * boolean - Throws :UserException - Author:AMAN - Creation Date : 21/9/2019 -
	 * Description : to register a new user
	 * 
	 * @throws ConnectException
	 * @throws SQLException
	 ********************************************************************************************************/

	public boolean userRegistration(UserDTO user) throws UserException, ConnectException {

		boolean userRegStatus = false;

		String userName = user.getUserName();
		String userId = user.getUserId();
		String userMail = user.getUserMail();
		String userPassword = user.getUserPassword();
		long userNumber = user.getUserNumber();
		int userCategory = user.getUserCategory();

		Connection connection = null;

		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			connection = DbConnection.getInstance().getConnection();

			if (!(userCategory == Integer.parseInt(goProps.getProperty("SALES_REP"))
					|| userCategory == Integer.parseInt(goProps.getProperty("RETAILER")))) {
				GoLog.logger.error(exceptionProps.getProperty("invalid_registration"));
				throw new UserException(exceptionProps.getProperty("invalid_registration"));

			}

			if (Validator.validatePhoneNumber(userNumber) != true) {
				GoLog.logger.error(exceptionProps.getProperty("invalid_phone_number"));
				throw new UserException(exceptionProps.getProperty("invalid_phone_number"));
			}

			if (Validator.isValid(userMail) != true) {
				GoLog.logger.error(exceptionProps.getProperty("invalid_email"));
				throw new UserException(exceptionProps.getProperty("invalid_email"));
			}

			if (Validator.validateUserId(userId) != true) {
				GoLog.logger.error(exceptionProps.getProperty("invalid_userId"));
				throw new UserException(exceptionProps.getProperty("invalid_userId"));
			}

			PreparedStatement validateNumberMail = connection.prepareStatement(QuerryMapper.VALIDATE_NUMBER_EMAIL);
			validateNumberMail.setLong(1, userNumber);
			validateNumberMail.setString(2, userMail);
			ResultSet rset = validateNumberMail.executeQuery();
			rset.next();
			if (rset.getInt(1) != 0) {
				GoLog.logger.error(exceptionProps.getProperty("number_mail_reg"));
				throw new UserException(exceptionProps.getProperty("number_mail_reg"));
			}

			Statement stmt = connection.createStatement();

			ResultSet resultSet = stmt.executeQuery(QuerryMapper.USER_EXISTS);

			while (resultSet.next()) {
				if (userId.equals(resultSet.getString("USER_ID"))) {

					GoLog.logger.error(exceptionProps.getProperty("user_exists"));
					throw new UserException(exceptionProps.getProperty("user_exists"));

				}
			}
			PreparedStatement presmt = connection.prepareStatement(QuerryMapper.CHECK_PHONE_NUMBER);
			presmt.setLong(1, userNumber);
			ResultSet rs = presmt.executeQuery();
			rs.next();
			if (rs.getInt(1) == 1) {
				GoLog.logger.error(exceptionProps.getProperty("user_phone_exists"));
				throw new UserException(exceptionProps.getProperty("user_phone_exists"));
			}

			PreparedStatement preparedStatement = connection.prepareStatement(QuerryMapper.NEW_USER_REGISTRATION);

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userId);
			preparedStatement.setString(3, userMail);
			preparedStatement.setString(4, userPassword);
			preparedStatement.setLong(5, userNumber);
			preparedStatement.setInt(6, userCategory);
			preparedStatement.setInt(7, 0);

			preparedStatement.executeUpdate();
			userRegStatus = true;

		} catch (DatabaseException | SQLException | IOException e) {

			GoLog.logger.error(exceptionProps.getProperty("registration_failed"));
			throw new UserException(" >>>" + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}
		return userRegStatus;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogin - Input Parameters : userID, password - Return
	 * Type : boolean - Throws :UserException - Author : AMAN - Creation Date
	 * :21/9/2019 - Description : to login a user
	 * 
	 * @throws UserException
	 * @throws Exception
	 ********************************************************************************************************/

	public boolean userLogin(UserDTO user) throws UserException, Exception {
		boolean userFound = false;
		boolean userLoginStatus = false;
		String userId = user.getUserId();
		String userPassword = user.getUserPassword();

		Connection connection = null;

		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement(QuerryMapper.USER_EXISTS);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {

				if (userId.equals(resultSet.getString("USER_ID"))) {
					userFound = true;
					stmt = connection.prepareStatement(QuerryMapper.USER_CATEGORY);
					stmt.setString(1, userId);
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {

						stmt = connection.prepareStatement(QuerryMapper.USER_PASSWORD_CHECK);
						stmt.setString(1, userId);
						ResultSet rSet = stmt.executeQuery();
						if (resultSet.getInt(7) == 0) {
							while (rSet.next()) {

								if (userPassword.equals(Authentication.decrypt(rSet.getString("USER_PASSWORD"),
										goProps.getProperty("SECURITY_KEY")))) {

									stmt = connection.prepareStatement(QuerryMapper.CHANGE_ACTIVE_STATUS);
									stmt.setString(1, userId);
									stmt.executeUpdate();
									userLoginStatus = true;
									break;
								}

								else {

									GoLog.logger.error(exceptionProps.getProperty("incorrect_password"));
									throw new UserException(exceptionProps.getProperty("incorrect_password"));

								}

							}
							break;

						}

						else {
							GoLog.logger.error(exceptionProps.getProperty("already_loggedin"));
							throw new UserException(exceptionProps.getProperty("already_loggedin"));

						}

					}
					break;

				}

				else {
					continue;
				}

			}
		}

		catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty("login_failure"));
			throw new UserException(" >>>" + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		if (!userFound) {
			userLoginStatus = false;
			GoLog.logger.error(exceptionProps.getProperty("invalid_user"));
			throw new UserException(exceptionProps.getProperty("invalid_user"));

		}
		return userLoginStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogout - Input Parameters : userID - Return Type :
	 * boolean - Throws :UserException - Author : AMAN - Creation Date : 21/9/2019 -
	 * Description : to logout a user
	 * 
	 * @throws SQLException
	 * @throws ConnectException
	 ********************************************************************************************************/

	@Override
	public boolean userLogout(UserDTO user) throws UserException, SQLException, ConnectException {

		boolean userFound = false;
		boolean userLogoutStatus = false;
		String userId = user.getUserId();
		Connection connection = null;

		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement(QuerryMapper.USER_EXISTS);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {

				if (userId.equals(resultSet.getString("USER_ID"))) {
					userFound = true;

					if (resultSet.getInt(7) == 1) {
						stmt = connection.prepareStatement(QuerryMapper.CHANGE_ACTIVE_STATUS_OFF);
						stmt.setString(1, userId);

						int logout = stmt.executeUpdate();
						userLogoutStatus = true;

						break;
					} else {
						GoLog.logger.error(exceptionProps.getProperty("already_loggedout"));
						throw new UserException(exceptionProps.getProperty("already_loggedout"));
					}
				} else {
					continue;

				}

			}
		} catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty("logout_failure"));
			throw new UserException(" >>>" + e.getMessage());

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		if (!userFound) {

			GoLog.logger.error(exceptionProps.getProperty("invalid_user"));
			throw new UserException(exceptionProps.getProperty("invalid_user"));

		}

		return userLogoutStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getAllProducts - Input Parameters : - Return Type : Product
	 * List - Throws : - Author : AGNIBHA - Creation Date : 21/9/2019 - Description
	 * : to get all the product from the database
	 * 
	 * @throws UserException
	 * @throws ConnectException
	 ********************************************************************************************************/

	public List<ProductBean> getAllProducts() throws UserException, ConnectException {
		List<ProductBean> allProducts = new ArrayList<ProductBean>();
		Session session = null;
		SessionFactory sessionFactory = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<ProductEntity> allProds = (List<ProductEntity>) session.createQuery(HQLQuerryMapper.GET_ALL_PRODUCTS).list();
			if (allProds != null) {
				for (ProductEntity prod : allProds) {

					ProductBean product = new ProductBean(prod.getProductId(), prod.getPrice(), prod.getColour(),
							prod.getDimension(), prod.getSpecification(), prod.getManufacturer(), prod.getQuantity(),
							prod.getProductCategory(), prod.getProductName());
					allProducts.add(product);
				}
			}

			session.getTransaction().commit();
		} catch (HibernateException | IOException exp) {
			session.getTransaction().rollback();
			throw new UserException(exceptionProps.getProperty("view_all_product_error") + " >>> " + exp.getMessage());
		}

		return allProducts;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : searchProducts - Input Parameters : product name - Return
	 * Type : Product List - Throws : - Author : CAPGEMINI - Creation Date :
	 * 21/9/2019 - Description : to search product based on the product name
	 * 
	 * @throws UserException
	 * @throws ConnectException
	 ********************************************************************************************************/

	public List<ProductBean> searchProduct(String productName) throws UserException, ConnectException {
		List<ProductBean> searchedProducts = new ArrayList<ProductBean>();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			PreparedStatement presmt = connection.prepareStatement(QuerryMapper.GET_PRODUCT_BY_PRODUCT_NAME);
			presmt.setString(1, "%" + productName.toUpperCase().trim() + "%");
			ResultSet resultSet = presmt.executeQuery();
			while (resultSet.next()) {
				ProductBean product = productDataMapping(resultSet);
				searchedProducts.add(product);

			}

		} catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(e.getMessage());
			throw new UserException(exceptionProps.getProperty("search_product_error") + " >>> " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		return searchedProducts;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : filterProducts - Input Parameters : filter category -
	 * Return Type : Product List - Throws : - Author : AGNIBHA - Creation Date :
	 * 21/9/2019 - Description : to filter product based on the filter category
	 * 
	 * @throws UserException
	 * @throws ConnectException
	 ********************************************************************************************************/

	public List<ProductBean> filterProduct(ProductFilterDTO filterProduct) throws UserException, ConnectException {

		List<ProductBean> filteredProducts = new ArrayList<ProductBean>();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			PreparedStatement presmt;
			if (filterProduct.getCategory() == 0) {
				presmt = connection.prepareStatement(QuerryMapper.GET_PRODUCT_BY_FILTER_NO_CATEGORY);
				presmt.setString(5, "%" + filterProduct.getManufacturer().toUpperCase().trim() + "%");
			} else {
				presmt = connection.prepareStatement(QuerryMapper.GET_PRODUCT_BY_FILTER);
				presmt.setInt(5, filterProduct.getCategory());
				presmt.setString(6, "%" + filterProduct.getManufacturer().toUpperCase().trim() + "%");
			}
			presmt.setString(1, "%" + filterProduct.getProductName().toUpperCase().trim() + "%");
			presmt.setString(2, "%" + filterProduct.getProductColour().toUpperCase().trim() + "%");
			presmt.setDouble(3, filterProduct.getLowRange());
			presmt.setDouble(4, filterProduct.getHighRange());

			ResultSet resultSet = presmt.executeQuery();
			while (resultSet.next()) {
				ProductBean product = productDataMapping(resultSet);
				filteredProducts.add(product);

			}

		} catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(e.getMessage());
			throw new UserException(exceptionProps.getProperty("filter_product_error") + " >>> " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}

		return filteredProducts;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userFetch - Input Parameters : userID - Return Type :User
	 * Throws :UserException - Author : AGNIBHA/AMAN - Creation Date : 21/9/2019 -
	 * Description : to fetch a user
	 * 
	 * @throws UserException
	 * @throws ConnectException
	 * 
	 * @throws SQLException
	 ********************************************************************************************************/
	@Override
	public UserDTO fetchUser(String userId) throws UserException {
		Connection connection = null;
		UserDTO loggedUser = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			PreparedStatement presmt = connection.prepareStatement(QuerryMapper.GET_USER);
			presmt.setString(1, userId);
			ResultSet resultSet = presmt.executeQuery();
			if (resultSet.next()) {
				String userName = resultSet.getString(1);
				String userid = resultSet.getString(2);
				String userMail = resultSet.getString(3);
				String userPassword = resultSet.getString(4);
				Long userNumber = resultSet.getLong(5);
				int userCategory = resultSet.getInt(6);

				loggedUser = new UserDTO(userName, userid, userMail, userPassword, userNumber, userCategory, true);
			} else {
				throw new UserException("User Does Not Exists !");
			}

		} catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(e.getMessage());
			throw new UserException(exceptionProps.getProperty("filter_product_error") + " >>> " + e.getMessage());
		}
		return loggedUser;
	}

}
