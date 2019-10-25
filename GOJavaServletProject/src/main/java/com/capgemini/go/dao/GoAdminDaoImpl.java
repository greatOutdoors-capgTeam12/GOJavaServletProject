package com.capgemini.go.dao;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.dto.ReturnReportRequestDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.dto.ViewDetailedSalesReportByProductDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.dto.WrongProductNotificationDTO;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.exception.ProductMasterException;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.utility.Constants;
import com.capgemini.go.utility.DbConnection;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.PropertiesLoader;

public class GoAdminDaoImpl implements GoAdminDao {

	Properties exceptionProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";
	private static final String GO_PROPERTIES_FILE = "goUtility.properties";

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : viewProductMaster - Input Parameters : connection - Return
	 * Type : void - Throws : - Author : Agnibha - Creation Date : 21/9/2019 -
	 * Description : to get List of all product master
	 * 
	 * @throws GoAdminException
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public List<UserDTO> viewProductMaster() throws GoAdminException, ConnectException {
		List<UserDTO> productMasters = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QuerryMapper.GET_ALL_PRODUCT_MASTER);
			while (resultSet.next()) {

				String userName = resultSet.getString(1);
				String userId = resultSet.getString(2);
				String userMail = resultSet.getString(3);
				long contact = resultSet.getLong(4);
				UserDTO productMaster = new UserDTO(userName, userId, userMail, null, contact, 0, false);
				productMasters.add(productMaster);

			}
		} catch (DatabaseException | SQLException | IOException e) {

			GoLog.logger.error(exceptionProps.getProperty("view_product_master_error"));
			throw new GoAdminException(exceptionProps.getProperty("view_product_master_error"));
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return productMasters;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : sendNotification - Input Parameters : order Return Database
	 * - Return Type : List of OrderReturnEntity - Throws : - Author : AGNIBHA - Creation
	 * Date : 21/9/2019 - Description : to send Notification if Wrong Product
	 * Shipped
	 * 
	 * @throws GoAdminException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public List<WrongProductNotificationDTO> getNotification() throws GoAdminException, ConnectException {
		Connection connection = null;
		List<WrongProductNotificationDTO> notifications = new ArrayList<WrongProductNotificationDTO>();
		try {
			
			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(QuerryMapper.GET_WRONG_PRODUCT_NOTIFICATION);
			while (resultset.next()) {
				String returnProduct = resultset.getString(1);
				String customer = resultset.getString(2);
				int customerType = resultset.getInt(3);
				java.sql.Date returnDate = resultset.getDate(4);

				WrongProductNotificationDTO notify = new WrongProductNotificationDTO(1, returnProduct, customer,
						customerType, returnDate);
				notifications.add(notify);
			}

		} catch (DatabaseException |IOException | SQLException e) {

			throw new GoAdminException(exceptionProps.getProperty("wrong_product_error") + ">>>" + e.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}

		return notifications;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : sendNotification - Input Parameters : Date List and mode -
	 * Return Type : boolean - Throws : - Author : AGNIBHA - Creation Date :
	 * 21/9/2019 - Description : retrieve the return report
	 ********************************************************************************************************/

	public boolean retrieveReturnReport(List<Date> dateList, int mode) {

		return false;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : addProductMaster - Input Parameters :User - Return Type :
	 * boolean - Throws : - Author : AGNIBHA - Creation Date : 21/9/2019 -
	 * Description : add product Master
	 * 
	 * @throws ProductMasterException
	 * @throws GoAdminException
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public boolean addProductMaster(UserDTO productmaster) throws GoAdminException, ConnectException {
		Connection connection = null;
		boolean productMasterRegistration = false;
		UserDao user = new UserDaoImpl();
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			productMasterRegistration = user.userRegistration(productmaster);
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.PRODUCT_MASTER_REGISTRATION);
			statement.setString(1, productmaster.getUserId());
			statement.executeUpdate();
		} catch (DatabaseException |UserException | SQLException | IOException e) {

			GoLog.logger.error(exceptionProps.getProperty("product_master_registering_error"));
			throw new GoAdminException(exceptionProps.getProperty("product_master_registering_error") +">>> \t "+ e.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return productMasterRegistration;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : setBonus
	 * Input Parameters : userID, bonus value 
	 * Return Type : void
	 * Throws : GoAdmin Exception
	 * Author : Aniket 
	 * Creation Date : 21/9/2019 
	 * Description : To set bonus of a sales representative
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public void setBonus(SalesRepDTO sr, double bonus) throws ConnectException {
		Connection connection = null;
		try {
			// Connecting Database
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE `SALES_REP` SET `BONUS`=" + bonus + " WHERE USER_ID='" + sr.getUserId() + "'";
			stmt.executeUpdate(sql);
		} catch (DatabaseException | SQLException | IOException e) {
			System.out.println("DATABASE EXCEPTION");

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
	}

	public double getBonus(SalesRepDTO sr) throws SalesRepresentativeException, ConnectException {
		Connection connection = null;
		double bonus = -1;
		// Finds the bonus from the database
		try {
			// Connecting Database
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			// Searching for the bonus information
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.SELECT_SALES_REP_BONUS);
			statement.setString(1, sr.getUserId());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				bonus = rs.getDouble("BONUS");
			}
			else {
				throw new SalesRepresentativeException(exceptionProps.getProperty("SALES_REP_NOT_FOUND"));
			}
		} catch (DatabaseException | SQLException | IOException | SalesRepresentativeException e) {
			GoLog.logger.error(e.getMessage());
			throw new SalesRepresentativeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		// returns -1 if bonus not found.
		return bonus;
	}

	/*******************************************************************************************************
	 * Function Name : setTarget
	 * Input Parameters : userID, target value 
	 * Return Type : void 
	 * Throws : GoAdmin Exception
	 * Author : CAPGEMINI 
	 * Creation Date : 21/9/2019 
	 * Description : To set target of a sales representative
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public void setTarget(SalesRepDTO sr, double target) throws ConnectException {
		Connection connection = null;
		try {
			// Connecting Database
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE `SALES_REP` SET `TARGET_SALES`=" + target + " WHERE USER_ID='" + sr.getUserId() + "'";
			stmt.executeUpdate(sql);
		} catch (DatabaseException | SQLException | IOException e) {
			System.out.println("EXCEPTION");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {	
				throw new ConnectException(Constants.connectionError);
			}
		}
	}

	public double getTarget(SalesRepDTO sr) throws SalesRepresentativeException, ConnectException {
		Connection connection = null;
		double target = -1;
		// Finds the bonus from the database
		try {
			// Connecting Database
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			// Searching for the bonus information
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.SELECT_SALES_REP_TARGET);
			statement.setString(1, sr.getUserId());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				target = rs.getDouble("TARGET_SALES");

			} else {
				throw new SalesRepresentativeException(exceptionProps.getProperty("SALES_REP_NOT_FOUND"));
			}
		} catch (DatabaseException | SQLException | IOException | SalesRepresentativeException e) {
			GoLog.logger.error(e.getMessage());
			throw new SalesRepresentativeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		// returns -1 if bonus not found.
		return target;
	}
	
	/*******************************************************************************************************
	 * Function Name : setDiscount
	 * Input Parameters : userID, discount value 
	 * Return Type : void 
	 * Throws : GoAdmin Exception
	 * Author : CAPGEMINI 
	 * Creation Date : 21/9/2019 
	 * Description : To set discount of a retailer
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public void setDiscount(RetailerDTO ret, double discount) throws ConnectException {
		Connection connection = null;
		try {
			// Connecting Database
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE `RETAILER` SET `DISCOUNT`=" + discount + " WHERE USER_ID='" + ret.getUserId() + "'";
			stmt.executeUpdate(sql);
		} catch (DatabaseException | SQLException | IOException e) {
			System.out.println("EXCEPTION");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {	
				throw new ConnectException(Constants.connectionError);
			}
		}
	}

	public double getDiscount(RetailerDTO ret) throws RetailerException, ConnectException {
		Connection connection = null;
		double discount = -1;
		// Finds the bonus from the database
		try {
			// Connecting Database
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			// Searching for the bonus information
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.SELECT_RETAILER_DISCOUNT);
			statement.setString(1, ret.getUserId());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				discount = rs.getDouble("DISCOUNT");
			} else {
				throw new RetailerException(exceptionProps.getProperty("RETAILER NOT FOUND"));
			}
		} catch (DatabaseException | SQLException | IOException | RetailerException e) {
			GoLog.logger.error(e.getMessage());
			throw new RetailerException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		// returns -1 if bonus not found.
		return discount;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : suggestFreqOrderProducts - Input Parameters : - Return Type
	 * : boolean - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : Admin will suggest products to retailer
	 * 
	 * @throws GoAdminException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public String suggestFreqOrderProducts(String retailerId) throws GoAdminException, ConnectException {
		Connection connection = null;
		Properties goProps = null;
		String prodId = null;
		try {

			connection = DbConnection.getInstance().getConnection();

			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.SUGGEST_FREQ_PRODUCTS);
			statement.setString(1, retailerId);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				if (rs.getInt("count(*)") > 3) {
					prodId = rs.getString("PRODUCT_ID");
					return prodId;
				}
			}
		}

		catch (DatabaseException |SQLException | IOException e) {

			GoLog.logger.error(exceptionProps.getProperty("no_freq_product"));
			throw new GoAdminException(exceptionProps.getProperty("no_freq_product"));
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return prodId;
	}

	
	
	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Function Name : getMonthlyTimeReport 
	 * - Input Parameters : RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean> 
	 * - Throws : N/A 
	 * - Author : Vikas 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products and their Monthly Shelf time periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getMonthlyShelfTime(RetailerInventoryDTO queryArguments) throws ConnectException {
		// Declaring List where valid objects returned by query will be stored
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean>();
		// Storing given arguments
		String retailerUserId = queryArguments.getRetailerUserId();
		Connection connection = null;
		try {
			// this line can throw IO Exception
//			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
//			sessionFactory = HibernateUtil.getSessionFactory();
//			session = sessionFactory.getCurrentSession();
//			transaction = session.beginTransaction();
//			
			Query stmt =session.createQuery(HQLQuerryMapper.GET_ITEMS_FOR_MONTH);
//			stmt.setParam
//			List <RetailerInventoryBean> result  = stmt.list();
			
			
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					QuerryMapper.GET_PRODUCTS_AND_SHELFTIMEPERIOD_BY_RETAILER_ID_AND_GIVEN_YEAR_AND_MONTH);
			// filling query statement fields
			stmt.setString(1, retailerUserId);
			stmt.setInt(2, queryArguments.getProductShelfTimeOut().get(Calendar.YEAR));
			stmt.setInt(3, queryArguments.getProductShelfTimeOut().get(Calendar.MONTH));
			// end of filling query statement fields
			// executing query
			ResultSet resultSet = stmt.executeQuery();
			// populating beans with resultant objects
			while (resultSet.next()) {
				RetailerInventoryBean temp = new RetailerInventoryBean();
				temp.setRetailerUserId(retailerUserId);
				temp.setProductCategory(resultSet.getInt(1));
				temp.setProductUIN(resultSet.getString(2));
				Period p = Period.of(0, 0, resultSet.getInt(3));
				temp.setProductDeliveryTimePeriod(null);
				temp.setProductShelfTimePeriod(p);
				result.add(temp);
			}
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {	
				throw new ConnectException(Constants.connectionError);
			}
		}
		return result;
	}
	
	/*******************************************************************************************************
	 * - Function Name : getQuarterlyTimeReport 
	 * - Input Parameters :RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A 
	 * - Author : Vikas 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products and their Quarterly Shelf time periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getQuarterlyShelfTime(RetailerInventoryDTO queryArguments) throws ConnectException {
		// Declaring List where valid objects returned by query will be stored
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean>();
		// Storing given arguments
		String retailerUserId = queryArguments.getRetailerUserId();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					QuerryMapper.GET_PRODUCTS_AND_SHELFTIMEPERIOD_BY_RETAILER_ID_AND_GIVEN_YEAR_AND_QUARTER);
			// filling query statement fields
			stmt.setString(1, retailerUserId);
			stmt.setInt(2, queryArguments.getProductShelfTimeOut().get(Calendar.YEAR));
			stmt.setInt(3, queryArguments.getProductShelfTimeOut().get(Calendar.MONTH)); // input expected is first
																							// month of quarter
			stmt.setInt(4, queryArguments.getProductShelfTimeOut().get(Calendar.MONTH) + 2);
			// end of filling query statement fields
			// executing query
			ResultSet resultSet = stmt.executeQuery();
			// populating beans with resultant objects
			while (resultSet.next()) {
				RetailerInventoryBean temp = new RetailerInventoryBean();
				temp.setRetailerUserId(retailerUserId);
				temp.setProductCategory(resultSet.getInt(1));
				temp.setProductUIN(resultSet.getString(2));
				Period p = Period.of(0, 0, resultSet.getInt(3));
				temp.setProductDeliveryTimePeriod(null);
				temp.setProductShelfTimePeriod(p);
				result.add(temp);
			}
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return result;
	}
	/*******************************************************************************************************
	 * - Function Name : getYearlyTimeReport 
	 * - Input Parameters : RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean> 
	 * - Throws : N/A 
	 * - Author : Vikas 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products and their Yearly Shelf time periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getYearlyShelfTime(RetailerInventoryDTO queryArguments) throws ConnectException {
		// Declaring List where valid objects returned by query will be stored
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean>();
		// Storing given arguments
		String retailerUserId = queryArguments.getRetailerUserId();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(QuerryMapper.GET_PRODUCTS_AND_SHELFTIMEPERIOD_BY_RETAILER_ID_AND_GIVEN_YEAR);
			// filling query statement fields
			stmt.setString(1, retailerUserId);
			stmt.setInt(2, queryArguments.getProductShelfTimeOut().get(Calendar.YEAR));
			// end of filling query statement fields
			// executing query
			ResultSet resultSet = stmt.executeQuery();
			// populating beans with resultant objects
			while (resultSet.next()) {
				RetailerInventoryBean temp = new RetailerInventoryBean();
				temp.setRetailerUserId(retailerUserId);
				temp.setProductCategory(resultSet.getInt(1));
				temp.setProductUIN(resultSet.getString(2));
				Period p = Period.of(0, 0, resultSet.getInt(3));
				temp.setProductDeliveryTimePeriod(null);
				temp.setProductShelfTimePeriod(p);
				result.add(temp);
			}
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return result;
	}
	
	/*******************************************************************************************************
	 * - Function Name : getOutlierProductCategoryDeliveryTime 
	 * - Input Parameters :RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A 
	 * - Author : Kunal 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all product categories and their Delivery time periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getOutlierProductCategoryDeliveryTime(RetailerInventoryDTO queryArguments) throws ConnectException {
		String retailerId = queryArguments.getRetailerUserId();
		// Declaring List where valid objects returned by query will be stored
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean>();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					QuerryMapper.GET_PRODUCTS_AND_DELIVERYTIMEPERIOD_BY_RETAILER_ID_AND_PRODUCTCATEGORY);
			// filling query statement fields
			stmt.setString(1, retailerId);
			// end of filling query statement fields
			// executing query
			ResultSet resultSet = stmt.executeQuery();
			// populating beans with resultant objects
			while (resultSet.next()) {
				RetailerInventoryBean temp = new RetailerInventoryBean();
				temp.setRetailerUserId(retailerId);
				temp.setProductCategory(resultSet.getInt(1));
				Period p = Period.of(0, 0, resultSet.getInt(2));
				temp.setProductDeliveryTimePeriod(p);
				temp.setProductShelfTimePeriod(null);
				result.add(temp);
			}
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return result;
	}
	
	/*******************************************************************************************************
	 * - Function Name : getOutlierItemDeliveryTime 
	 * - Input Parameters : RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A 
	 * - Author : Kunal 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products and their Delivery time periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getOutlierItemDeliveryTime(RetailerInventoryDTO queryArguments) throws ConnectException {
		String retailerId = queryArguments.getRetailerUserId();
		// Declaring List where valid objects returned by query will be stored
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean>();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(QuerryMapper.GET_PRODUCTS_AND_DELIVERYTIMEPERIOD_BY_RETAILER_ID);
			// filling query statement fields
			stmt.setString(1, retailerId);
			// end of filling query statement fields
			// executing query
			ResultSet resultSet = stmt.executeQuery();
			// populating beans with resultant objects
			while (resultSet.next()) {
				RetailerInventoryBean temp = new RetailerInventoryBean();
				temp.setRetailerUserId(retailerId);
				temp.setProductCategory(resultSet.getInt(1));
				temp.setProductUIN(resultSet.getString(2));
				Period p = Period.of(0, 0, resultSet.getInt(3));
				temp.setProductDeliveryTimePeriod(p); // Average Delivery Time Period for that product category
				temp.setProductShelfTimePeriod(null);
				result.add(temp);
			}
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return result;
	}
	
	/*******************************************************************************************************
	 * - Function Name : getOutlierItemInOutlierProductCategoryDeliveryTime 
	 * - Input Parameters : RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A 
	 * - Author : Kunal 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products in outlier categories and their Delivery time periods
	 * @throws ConnectException 
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getOutlierItemInOutlierProductCategoryDeliveryTime(
			RetailerInventoryDTO queryArguments) throws ConnectException {
		// get outlier product categories
		List<RetailerInventoryBean> outlierCategories = this.getOutlierProductCategoryDeliveryTime(queryArguments);
		int minDeliveryTimeCategory = outlierCategories.get(0).getProductCategory();
		int maxDeliveryTimeCategory = outlierCategories.get(outlierCategories.size() - 1).getProductCategory();
		String retailerId = queryArguments.getRetailerUserId();
		// Declaring List where valid objects returned by query will be stored
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean>();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					QuerryMapper.GET_OUTLIER_PRODUCTS_AND_DELIVERYTIMEPERIOD_BY_RETAILER_ID_AND_PRODUCTCATEGORY);
			// filling query statement fields
			stmt.setString(1, retailerId);
			stmt.setInt(2, minDeliveryTimeCategory);
			// end of filling query statement fields
			// executing query
			ResultSet resultSet = stmt.executeQuery();
			// populating beans with resultant objects
			while (resultSet.next()) {
				RetailerInventoryBean temp = new RetailerInventoryBean();
				temp.setRetailerUserId(retailerId);
				temp.setProductCategory(resultSet.getInt(1));
				temp.setProductUIN(resultSet.getString(2));
				Period p = Period.of(0, 0, resultSet.getInt(3));
				temp.setProductDeliveryTimePeriod(p);
				temp.setProductShelfTimePeriod(null);
				result.add(temp);
			}
			stmt = connection.prepareStatement(
					QuerryMapper.GET_OUTLIER_PRODUCTS_AND_DELIVERYTIMEPERIOD_BY_RETAILER_ID_AND_PRODUCTCATEGORY);
			// filling query statement fields
			stmt.setString(1, retailerId);
			stmt.setInt(2, maxDeliveryTimeCategory);
			// end of filling query statement fields
			// executing query
			resultSet = stmt.executeQuery();
			// populating beans with resultant objects
			while (resultSet.next()) {
				RetailerInventoryBean temp = new RetailerInventoryBean();
				temp.setRetailerUserId(retailerId);
				temp.setProductCategory(resultSet.getInt(1));
				temp.setProductUIN(resultSet.getString(2));
				Period p = Period.of(0, 0, resultSet.getInt(3));
				temp.setProductDeliveryTimePeriod(p);
				temp.setProductShelfTimePeriod(null);
				result.add(temp);
			}
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return result;
	}
	
	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers 
	 * - Input Parameters : N/A
	 * - Return Type : List<RetailerInventoryBean> 
	 * - Throws : N/A 
	 * - Author : Kunal 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all retailers in database
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getListOfRetailers () throws ConnectException {
		List<RetailerInventoryBean> retailerList = new ArrayList<RetailerInventoryBean>();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement(QuerryMapper.GET_ALL_RETAILERS_IN_DATABASE);
			ResultSet resultSet = stmt.executeQuery();
			// populating beans with resultant objects
			while (resultSet.next()) {
				RetailerInventoryBean temp = new RetailerInventoryBean();
				temp.setRetailerUserId(resultSet.getString(1));
				retailerList.add(temp);
			}
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return retailerList;
	}
	// end of Shelf Time Report and Delivery Time Report

	// ------------------------ GreatOutdoor Application --------------------------
	
	/*******************************************************************************************************
	 * Function Name : viewSalesRepData 
	 * Input Parameters : salesRepId 
	 * Return Type : SalesRepDTO
	 * boolean Throws : - 
	 * Author : CAPGEMINI 
	 * Creation Date : 21/9/2019 
	 * Description : To view report of specific sales representative
	 * @throws ConnectException 
	 * @throws UserDoesNotExist
	 ********************************************************************************************************/
	public SalesRepDTO viewSalesRepData(String salesRepId) throws GoAdminException, ConnectException {

		SalesRepDTO salesRep = new SalesRepDTO();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();

			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			if (connection == null)
				throw new GoAdminException(exceptionProps.getProperty("NO_CONNECTION"));
			if (salesRepId == null)
				throw new GoAdminException(exceptionProps.getProperty("INVALID_USERID"));

			PreparedStatement stmt = connection.prepareStatement(QuerryMapper.SELECT_USER);
			stmt.setString(1, salesRepId);
			ResultSet user = stmt.executeQuery();
			if (!user.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("USER_DOES_NOT_EXISTS"));

			PreparedStatement statement = connection.prepareStatement(QuerryMapper.SELECT_SALES_REP);
			statement.setString(1, salesRepId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				salesRep.setUserId(salesRepId);
				salesRep.setTargetStatus(rs.getInt("TARGET_STATUS"));
				salesRep.setCurrentTargetStatus(rs.getDouble("CURRENT_SALES"));
				salesRep.setTarget(rs.getDouble("TARGET_SALES"));
				salesRep.setBonus(rs.getDouble("BONUS"));

			} else {
				throw new GoAdminException(exceptionProps.getProperty("USER_DOES_NOT_EXISTS"));
			}

		} catch (DatabaseException |SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty(e.getMessage()));

		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return salesRep;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewSalesRepData 
	 * Input Parameters : 
	 * Return Type : List
	 * Throws : 
	 * Author : CAPGEMINI 
	 * Creation Date : 21/9/2019 
	 * Description : To view report of all sales representative
	 * @throws GoAdminException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public List<SalesRepDTO> viewAllSalesRepData() throws GoAdminException, ConnectException {

		List<SalesRepDTO> salesRep = new ArrayList<SalesRepDTO>();
		SalesRepDTO temp;
		Connection connection = null;
		Statement statement = null;
		try {

			connection = DbConnection.getInstance().getConnection();

			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			if (connection == null)
				throw new GoAdminException(exceptionProps.getProperty("NO_CONNECTION"));

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(QuerryMapper.SELECT_ALL_SALES_REP);

			if (!rs.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("EMPTY_DATABASE"));

			while (rs.next()) {

				temp = new SalesRepDTO();

				temp.setUserId(rs.getString("USER_ID"));
				temp.setTargetStatus(rs.getInt("TARGET_STATUS"));
				temp.setCurrentTargetStatus(rs.getDouble("CURRENT_SALES"));
				temp.setTarget(rs.getDouble("TARGET_SALES"));
				temp.setBonus(rs.getDouble("BONUS"));
				salesRep.add(temp);

			}

		} catch (DatabaseException |SQLException | IOException e) {

			GoLog.logger.error(exceptionProps.getProperty(e.getMessage()));

		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return salesRep;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewRetailerData 
	 * Input Parameters : RetailerId
	 * Return Type : RetailerDTO 
	 * Throws : GoAdminException
	 * Author : CAPGEMINI 
	 * Creation Date : 21/9/2019 
	 * Description : To view report of specific retailer
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public RetailerDTO viewRetailerData(String RetailerId) throws GoAdminException, ConnectException {

		RetailerDTO retailer = new RetailerDTO();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			if (connection == null)
				throw new GoAdminException(exceptionProps.getProperty("NO_CONNECTION"));

			if (RetailerId == null)
				throw new GoAdminException(exceptionProps.getProperty("INVALID_USERID"));

			PreparedStatement stmt = connection.prepareStatement(QuerryMapper.SELECT_USER);
			stmt.setString(1, RetailerId);
			ResultSet user = stmt.executeQuery();
			if (!user.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("EMPTY_DATABASE"));

			PreparedStatement statement = connection.prepareStatement(QuerryMapper.SELECT_RETAILER);
			statement.setString(1, RetailerId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {

				retailer.setUserId(RetailerId);
				retailer.setDiscount(rs.getDouble("DISCOUNT"));
			} else
				throw new GoAdminException("USER_DOES_NOT_EXISTS");

		} catch (DatabaseException |SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty(e.getMessage()));
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return retailer;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewAllRetailerData 
	 * Input Parameters : 
	 * Return Type : List
	 * Throws : GoAdminException
	 * Author : CAPGEMINI 
	 * Creation Date : 21/9/2019 
	 * Description : To view report of all the retailer
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public List<RetailerDTO> viewAllRetailerData() throws GoAdminException, ConnectException {

		List<RetailerDTO> retailer = new ArrayList<RetailerDTO>();

		RetailerDTO temp;
		Connection connection = null;
		try {

			 connection = DbConnection.getInstance().getConnection();

			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			if (connection == null)
				throw new GoAdminException(exceptionProps.getProperty("NO_CONNECTION"));

			PreparedStatement statement = connection.prepareStatement(QuerryMapper.SELECT_ALL_RETAILER);
			ResultSet rs = statement.executeQuery();

			if (!rs.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("EMPTY_DATABASE"));

			while (rs.next()) {
				temp = new RetailerDTO();
				temp.setUserId(rs.getString("USER_ID"));
				temp.setDiscount(rs.getDouble("DISCOUNT"));
				retailer.add(temp);

			}

		} catch (DatabaseException |SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty(e.getMessage()));

		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return retailer;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewSalesReportByUser Input Parameters : entry , exit ,
	 * TargetuserId Return Type : boolean Throws : Author : CAPGEMINI Creation Date
	 * : 21/9/2019 Description : To view sales report of specific user ID within
	 * given date
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public List<ViewSalesReportByUserDTO> viewSalesReportByCategory(Date entry, Date exit, int cat)
			throws GoAdminException, ConnectException {

		List<ViewSalesReportByUserDTO> viewSales = new ArrayList<ViewSalesReportByUserDTO>();
		Connection connection = null;
		Statement statement = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			if (connection == null)
				throw new GoAdminException(exceptionProps.getProperty("NO_CONNECTION"));

			if (cat < 1 || cat > 5)
				throw new GoAdminException(exceptionProps.getProperty("INVALID_CATEGORY"));
			if (entry == null || exit == null)
				throw new GoAdminException(exceptionProps.getProperty("INVALID_DATE"));

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(QuerryMapper.SELECT_DATA_FROM_DATABASE);

			if (!rs.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("EMPTY_DATABASE"));

			while (rs.next()) {

				ViewSalesReportByUserDTO temp = new ViewSalesReportByUserDTO();
				if (rs.getInt("PRODUCT_CATEGORY") == cat) {

					temp.setUserId(rs.getString("USER_ID"));
					temp.setDate(rs.getDate("ORDER_INITIATE_TIME"));
					temp.setOrderId(rs.getString("ORDER_ID"));
					temp.setProductId(rs.getString("PRODUCT_ID"));
					temp.setProductPrice(rs.getDouble("PRODUCT_PRICE"));
					temp.setProductCategory(rs.getInt("PRODUCT_CATEGORY"));
					viewSales.add(temp);
				}

			}
			return viewSales;

		} catch (DatabaseException |SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty(e.getMessage()));

		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return viewSales;

	}
	//Currently not used
	public List<ViewSalesReportByUserDTO> viewSalesReportByUser(Date entry, Date exit, String TargetuserId)
			throws GoAdminException, ConnectException {

		List<ViewSalesReportByUserDTO> viewSalesList = new ArrayList<ViewSalesReportByUserDTO>();

		ViewSalesReportByUserDTO temp1;

		Statement statement = null;
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();

			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			if (connection == null)
				throw new GoAdminException(exceptionProps.getProperty("NO_CONNECTION"));
			if (TargetuserId == null)
				throw new GoAdminException(exceptionProps.getProperty("INVALID_USERID"));
			if (entry == null || exit == null)
				throw new GoAdminException(exceptionProps.getProperty("INVALID_DATE"));

			PreparedStatement stmt = connection.prepareStatement(QuerryMapper.SELECT_USER);
			stmt.setString(1, TargetuserId);
			ResultSet user = stmt.executeQuery();
			if (!user.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("USER_DOES_NOT_EXISTS"));

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(QuerryMapper.SELECT_DATA_FROM_DATABASE);

			if (!rs.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("EMPTY_DATABASE"));

			while (rs.next()) {
				if (TargetuserId.equalsIgnoreCase(rs.getString("USER_ID"))) {
					temp1 = new ViewSalesReportByUserDTO();
					temp1.setUserId(rs.getString("USER_ID"));
					temp1.setDate(rs.getDate("ORDER_INITIATE_TIME"));
					temp1.setOrderId(rs.getString("ORDER_ID"));
					temp1.setProductId(rs.getString("PRODUCT_ID"));
					temp1.setProductPrice(rs.getDouble("PRODUCT_PRICE"));
					temp1.setProductCategory(rs.getInt("PRODUCT_CATEGORY"));

					viewSalesList.add(temp1);

				}

			}

		} catch (DatabaseException |SQLException | IOException e) {

			GoLog.logger.error(exceptionProps.getProperty(e.getMessage()));
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return viewSalesList;
	}


	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewSalesReportByUserAndCategory
	 * Input Parameters : entry ,exit , targetuserId, category 
	 * Return Type : List 
	 * Throws : GoAdmin Exception
	 * Author : CAPGEMINI 
	 * Creation Date : 21/9/2019 
	 * Description : To view userId, Order Date, Order Id, Product Id, Product Category, Product Price using the given inputs
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public List<ViewSalesReportByUserDTO> viewSalesReportByUserAndCategory(Date entry, Date exit, String TargetuserId,
			int category) throws GoAdminException, ConnectException {

		Statement statement = null;

		List<ViewSalesReportByUserDTO> viewSales = new ArrayList<ViewSalesReportByUserDTO>();
		ViewSalesReportByUserDTO temp;
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			if (connection == null)
				throw new GoAdminException(exceptionProps.getProperty("NO_CONNECTION"));
			if (entry == null || exit == null)
				throw new GoAdminException(exceptionProps.getProperty("INVALID_DATE"));

//			PreparedStatement stmt = connection.prepareStatement(QuerryMapper.SELECT_USER);
//			stmt.setString(1, TargetuserId);
//			ResultSet user = stmt.executeQuery();
//			if (!user.isBeforeFirst() && !(TargetuserId.equalsIgnoreCase("ALL")))
//				throw new GoAdminException(exceptionProps.getProperty("USER_DOES_NOT_EXISTS"));

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(QuerryMapper.SELECT_DATA_FROM_DATABASE);

			if (!rs.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("EMPTY_DATABASE"));

			while (rs.next()) {

				if (TargetuserId.equalsIgnoreCase(rs.getString("USER_ID"))
						&& category == rs.getInt("PRODUCT_CATEGORY")) {

					temp = new ViewSalesReportByUserDTO();
					temp.setUserId(rs.getString("USER_ID"));
					temp.setDate(rs.getDate("ORDER_INITIATE_TIME"));
					temp.setOrderId(rs.getString("ORDER_ID"));
					temp.setProductId(rs.getString("PRODUCT_ID"));
					temp.setProductPrice(rs.getDouble("PRODUCT_PRICE"));
					temp.setProductCategory(rs.getInt("PRODUCT_CATEGORY"));
					viewSales.add(temp);
				} else if (TargetuserId.equalsIgnoreCase("ALL") && category == rs.getInt("PRODUCT_CATEGORY")) {
					temp = new ViewSalesReportByUserDTO();
					temp.setUserId(rs.getString("USER_ID"));
					temp.setDate(rs.getDate("ORDER_INITIATE_TIME"));
					temp.setOrderId(rs.getString("ORDER_ID"));
					temp.setProductId(rs.getString("PRODUCT_ID"));
					temp.setProductPrice(rs.getDouble("PRODUCT_PRICE"));
					temp.setProductCategory(rs.getInt("PRODUCT_CATEGORY"));
					viewSales.add(temp);
				} else if (TargetuserId.equalsIgnoreCase("ALL") && category == 6) {
					temp = new ViewSalesReportByUserDTO();
					temp.setUserId(rs.getString("USER_ID"));
					temp.setDate(rs.getDate("ORDER_INITIATE_TIME"));
					temp.setOrderId(rs.getString("ORDER_ID"));
					temp.setProductId(rs.getString("PRODUCT_ID"));
					temp.setProductPrice(rs.getDouble("PRODUCT_PRICE"));
					temp.setProductCategory(rs.getInt("PRODUCT_CATEGORY"));
					viewSales.add(temp);

				}

			}

		} catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty(e.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new ConnectException(Constants.connectionError);
			}
		}
//		for(int i=0;i<viewSales.size();i++)
//			System.out.println(viewSales.get(i).getUserId());
		return viewSales;
	}
	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewDetailedSalesReportByProduct 
	 * Input Parameters : entry ,exit , category 
	 * Return Type : boolean 
	 * Throws : GoAdmin Exception
	 * Author : CAPGEMINI 
	 * Creation Date : 21/9/2019 
	 * Description : To view amount change, percentage change,
	 * color code, month to month, quarter to quarter, year to year change of
	 * specific product
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public List<ViewDetailedSalesReportByProductDTO> viewDetailedSalesReportByProduct(Date entry, Date exit,
			int category) throws GoAdminException, ConnectException {

		List<ViewDetailedSalesReportByProductDTO> viewDetailedSalesReportByProduct = new ArrayList<ViewDetailedSalesReportByProductDTO>();
		List<ViewDetailedSalesReportByProductDTO> growthListfinal = new ArrayList<ViewDetailedSalesReportByProductDTO>();
		
		ViewDetailedSalesReportByProductDTO temp;

		Statement stmt = null;
		int startYear = entry.getYear();
		int endYear = exit.getYear();
		int j = 0;
		double prevM = 0.0, prevQ = 0.0, prevY = 0.0;

		double[] amtM = new double[12];
		double[] perChngM = new double[12];
		String[] codeM = new String[12];

		double[] amtQ = new double[4];
		double[] perChngQ = new double[4];
		String[] codeQ = new String[4];

		double amtY = 0.0, perChngY = 0.0;
		String codeY;

		double[] arrRevM = new double[12];
		double[] arrRevQ = new double[4];
		double arrRevY = 0.0;

		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();

			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);

			if (connection == null)
				throw new GoAdminException(exceptionProps.getProperty("NO_CONNECTION"));
			
			if (entry == null || exit == null)
				throw new GoAdminException(exceptionProps.getProperty("INVALID_DATE"));

			DbConnection.getInstance();
			connection = DbConnection.getConnection();

			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(QuerryMapper.SELECT_REVENUE_BY_MONTH);

			if (!rs.isBeforeFirst())
				throw new GoAdminException(exceptionProps.getProperty("EMPTY_DATABASE"));

			// loop from start year to end year
			for (int index = startYear; index <= endYear; index++) {
				// loop for going through order list
				while (rs.next()) {

					// if (category == rs.getInt("PRODUCT_CATEGORY")) {
					

					int month = rs.getInt(3);
					int year = rs.getInt(4) - 1900;
					for (j = 0; j <= 11; j++) {

						if (month == j && year == index) {
							

							arrRevM[j] += rs.getDouble("PRODUCT_PRICE");

						}

					}
					// }
				}
				/// }

				// loop for going from January to December
				for (j = 0; j <= 11; j++) {

					temp = new ViewDetailedSalesReportByProductDTO();
					// initialising the amount change of current month and previous month

					if (j == 0) {

						amtM[j] = arrRevM[j] - prevM;
						perChngM[j] = Math.round((100 * (amtM[j]) / prevM)*100)/100D ;

					} else {
						amtM[j] = arrRevM[j] - arrRevM[j - 1];
						perChngM[j] = Math.round((100 * (amtM[j]) / arrRevM[j - 1])*100)/100D;

					}

					// checking the necessary condition for color code
					if (perChngM[j] >= 10.0)
						codeM[j] = "GREEN";
					else if (perChngM[j] >= 2.0 && perChngM[j] <= 10)
						codeM[j] = "AMBER";
					else
						codeM[j] = "RED";

					temp.setPeriod(j);
					temp.setRevenue(arrRevM[j]);
					temp.setAmountChange(amtM[j]);
					temp.setPercentageGrowth(perChngM[j]);
					temp.setCode(codeM[j]);
					temp.setType("MONTH");

					viewDetailedSalesReportByProduct.add(temp);

				}

				// Initialising previous month as last month of current year
				prevM = arrRevM[11];

				int k = 0;
				for (j = 0; j <= 3; j++) {
					arrRevQ[j] = arrRevM[k] + arrRevM[k + 1] + arrRevM[k + 2];
					k += 3;

				}

				for (j = 0; j <= 3; j++) {

					temp = new ViewDetailedSalesReportByProductDTO();
					// initialising the amount change of current month and previous month

					if (j == 0) {

						amtQ[j] = arrRevQ[j] - prevQ;
						perChngM[j] = Math.round((100 * (amtQ[j]) / prevQ)*100)/100D;

					} else {

						amtQ[j] = arrRevQ[j] - arrRevQ[j - 1];
						perChngQ[j] = Math.round((100 * (amtQ[j]) / arrRevQ[j - 1])*100)/100D;

					}

					// checking the necessary condition for color code
					if (perChngQ[j] >= 10.0)
						codeQ[j] = "GREEN";
					else if (perChngQ[j] >= 2.0 && perChngQ[j] <= 10)
						codeQ[j] = "AMBER";
					else
						codeQ[j] = "RED";

					temp.setPeriod(j);
					temp.setRevenue(arrRevQ[j]);
					temp.setAmountChange(amtQ[j]);
					temp.setPercentageGrowth(perChngQ[j]);
					temp.setCode(codeQ[j]);
					temp.setType("QUARTER");

					viewDetailedSalesReportByProduct.add(temp);

				}
				// initialising the amount change of previous quarter as last quarter
				prevQ = arrRevQ[3];

				// year to year
				arrRevY = arrRevQ[0] + arrRevQ[1] + arrRevQ[2] + arrRevQ[3];
				amtY = arrRevY - prevY;
				perChngY = Math.round((100 * (amtY / prevY))) ;
				if (perChngY >= 10.0)
					codeY = "GREEN";
				else if (perChngY >= 2.0 && perChngY <= 10)
					codeY = "AMBER";
				else
					codeY = "RED";

				temp = new ViewDetailedSalesReportByProductDTO();
				temp.setPeriod((index+1900));
				temp.setRevenue(arrRevY);
				temp.setAmountChange(amtY);
				temp.setPercentageGrowth(perChngY);
				temp.setCode(codeY);
				temp.setType("YEAR");
				viewDetailedSalesReportByProduct.add(temp);
				prevY = arrRevY;
				for (j = 0; j <= 11; j++) {

					amtM[j] = 0.0;
					perChngM[j] = 0.0;
					codeM[j] = "NA";
					arrRevM[j] = 0.0;

				}
				for (j = 0; j <= 3; j++) {
					amtQ[j] = 0.0;
					perChngQ[j] = 0.0;
					codeQ[j] = "NA";
					arrRevQ[j] = 0.0;

				}
				amtY = 0.0;
				perChngY = 0.0;
				codeY = "NA";
				arrRevY = 0.0;

			}
			

		} catch (DatabaseException | SQLException | IOException e) {
			e.printStackTrace();
			GoLog.logger.error(exceptionProps.getProperty(e.getMessage()));

		} 
		finally 
		{
			
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();

				throw new ConnectException(Constants.connectionError);
			}
			int n=viewDetailedSalesReportByProduct.size(),index;
			if(category==1)
			{
				for(index=0;index<n;index++)
				{
					
					if(viewDetailedSalesReportByProduct.get(index).getType().equalsIgnoreCase("MONTH"))
						growthListfinal.add(viewDetailedSalesReportByProduct.get(index));
				}
				
			}
			else if(category==2)
			{
				for(index=0;index<n;index++)
				{
					if(viewDetailedSalesReportByProduct.get(index).getType().equalsIgnoreCase("QUARTER"))
						growthListfinal.add(viewDetailedSalesReportByProduct.get(index));
				}
				
			}
			else if(category==3)
			{
				for(index=0;index<n;index++)
				{
					if(viewDetailedSalesReportByProduct.get(index).getType().equalsIgnoreCase("YEAR"))
						growthListfinal.add(viewDetailedSalesReportByProduct.get(index));
				}
			}
			return growthListfinal;
		}


	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : retrieveReturnReport - Input Parameters : Return Report
	 * request connection - Return Type : boolean- Throws : - Author : Agnibha -
	 * Creation Date : 21/9/2019 - Description : to retrieve statistics for product
	 * return
	 *
	 * @throws GoAdminException
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public List<Double> retrieveReturnReport(ReturnReportRequestDTO request) throws GoAdminException, ConnectException {
		List<Double> returnReport = new ArrayList<Double>();
		Connection connection = null;
		try {

			connection = DbConnection.getInstance().getConnection();
			Properties goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			int mode = request.getMode();
			int reason = request.getMode();

			// Monthly Return Report

			if (mode == Integer.parseInt(goProps.getProperty("MONTHLY"))) {
				Date startDate = null;
				Date endDate = null;
				try {
					startDate = request.getDateInterval().get(0);
					endDate = request.getDateInterval().get(1);
				} catch (IndexOutOfBoundsException e) {
					GoLog.logger.error(e.getMessage());
					throw new GoAdminException(e.getMessage());
				}
				if (startDate.after(endDate) || startDate.equals(endDate)) {
					GoLog.logger.error(exceptionProps.getProperty("date_range_mismatch"));
					throw new GoAdminException(exceptionProps.getProperty("date_range_mismatch"));
				}

				Calendar startCalendar = new GregorianCalendar();
				startCalendar.setTime(startDate);
				Calendar endCalendar = new GregorianCalendar();
				endCalendar.setTime(endDate);

				int startingMonth = startCalendar.get(Calendar.MONTH);
				int endingMonth = endCalendar.get(Calendar.MONTH);
				int startingYear = startCalendar.get(Calendar.YEAR);
				int endingYear = endCalendar.get(Calendar.YEAR);
				int month_diff = (endingYear - startingYear) * 12 + (endingMonth - startingMonth) + 1;

				if (reason == Integer.parseInt(goProps.getProperty("WRONG_PRODUCT"))) {
					PreparedStatement preStmt = connection
							.prepareStatement(QuerryMapper.RETURN_REPORT_FOR_WRONG_PRODUCT_MONTHLY);
					int year = startingYear;
					int month = startingMonth;
					while (!(year == endingYear && month == endingMonth)) {
						preStmt.setInt(1, year);
						preStmt.setInt(2, month + 1);
						ResultSet resultSet = preStmt.executeQuery();
						resultSet.next();
						returnReport.add(resultSet.getDouble(1));
						month = month + 1;
						if (month == 12) {
							month = 0;
							year = year + 1;
						}
					}

				} else if (reason == Integer.parseInt(goProps.getProperty("INCOMPLETE_PRODUCT"))) {

					PreparedStatement preStmt = connection
							.prepareStatement(QuerryMapper.RETURN_REPORT_FOR_INCOMPLETE_PRODUCT_MONTHLY);
					int year = startingYear;
					int month = startingMonth;
					while (!(year == endingYear && month == endingMonth)) {
						preStmt.setInt(1, year);
						preStmt.setInt(2, month + 1);
						ResultSet resultSet = preStmt.executeQuery();
						returnReport.add(resultSet.getDouble(1));
						if (month == 12) {
							month = 0;
							year = year + 1;
						}
					}

				}

			}

			// QUATERLY RETURN REPORT

			else if (mode == Integer.parseInt(goProps.getProperty("QUATERLY"))) {

				Date year = null;
				try {
					year = request.getDateInterval().get(0);

				} catch (IndexOutOfBoundsException e) {
					GoLog.logger.error(e.getMessage());
					throw new GoAdminException(e.getMessage());
				}

				Calendar reportYear = new GregorianCalendar();
				reportYear.setTime(year);
				int inputYear = reportYear.get(Calendar.YEAR);

				if (reason == Integer.parseInt(goProps.getProperty("WRONG_PRODUCT"))) {
					PreparedStatement preStmt = connection
							.prepareStatement(QuerryMapper.RETURN_REPORT_FOR_WRONG_PRODUCT_QUATERLY);
					preStmt.setInt(1, inputYear);
					preStmt.setInt(2, 0);
					preStmt.setInt(3, 2);
					ResultSet reset = preStmt.executeQuery();
					returnReport.add(reset.getDouble(1));
					preStmt.setInt(2, 3);
					preStmt.setInt(3, 5);
					reset = preStmt.executeQuery();
					returnReport.add(reset.getDouble(1));
					preStmt.setInt(2, 6);
					preStmt.setInt(3, 8);
					reset = preStmt.executeQuery();
					returnReport.add(reset.getDouble(1));
					preStmt.setInt(2, 9);
					preStmt.setInt(3, 11);
					reset = preStmt.executeQuery();
					returnReport.add(reset.getDouble(1));
				}

				else if (reason == Integer.parseInt(goProps.getProperty("INCOMPLETE_PRODUCT"))) {
					System.out.println("Agni");
					PreparedStatement preStmt = connection
							.prepareStatement(QuerryMapper.RETURN_REPORT_FOR_INCOMPLETE_PRODUCT_QUATERLY);
					preStmt.setInt(1, inputYear);
					preStmt.setInt(2, 0);
					preStmt.setInt(3, 2);
					ResultSet reset = preStmt.executeQuery();
					reset.next();
					returnReport.add(reset.getDouble(1));
					preStmt.setInt(2, 3);
					preStmt.setInt(3, 5);
					reset = preStmt.executeQuery();
					reset.next();
					returnReport.add(reset.getDouble(1));
					preStmt.setInt(2, 6);
					preStmt.setInt(3, 8);
					reset = preStmt.executeQuery();
					reset.next();
					returnReport.add(reset.getDouble(1));
					preStmt.setInt(2, 9);
					preStmt.setInt(3, 11);
					reset = preStmt.executeQuery();
					reset.next();
					returnReport.add(reset.getDouble(1));
				}

			}

			// YEARLY RETURN REPORT

			else if (mode == Integer.parseInt(goProps.getProperty("YEARLY"))) {
				Date startDate = null;
				Date endDate = null;
				try {
					startDate = request.getDateInterval().get(0);
					endDate = request.getDateInterval().get(1);
				} catch (IndexOutOfBoundsException e) {
					GoLog.logger.error(e.getMessage());
					throw new GoAdminException(e.getMessage());
				}
				if (startDate.after(endDate) || startDate.equals(endDate)) {
					GoLog.logger.error(exceptionProps.getProperty("date_range_mismatch"));
					throw new GoAdminException(exceptionProps.getProperty("date_range_mismatch"));
				}

				Calendar startCalendar = new GregorianCalendar();
				startCalendar.setTime(startDate);
				Calendar endCalendar = new GregorianCalendar();
				endCalendar.setTime(endDate);

				int startingYear = startCalendar.get(Calendar.YEAR);
				int endingYear = endCalendar.get(Calendar.YEAR);

				if (reason == Integer.parseInt(goProps.getProperty("WRONG_PRODUCT"))) {
					PreparedStatement preStmt = connection
							.prepareStatement(QuerryMapper.RETURN_REPORT_FOR_WRONG_PRODUCT_YEARLY);
					for (int index = startingYear; index <= endingYear; index++) {
						preStmt.setInt(1, index);
						ResultSet resultSet = preStmt.executeQuery();
						resultSet.next();
						returnReport.add(resultSet.getDouble(1));
					}
				} else if (reason == Integer.parseInt(goProps.getProperty("INCOMPLETE_PRODUCT"))) {
					PreparedStatement preStmt = connection
							.prepareStatement(QuerryMapper.RETURN_REPORT_FOR_INCOMPLETE_PRODUCT_YEARLY);
					for (int index = startingYear; index <= endingYear; index++) {
						preStmt.setInt(1, index);
						ResultSet resultSet = preStmt.executeQuery();
						resultSet.next();
						returnReport.add(resultSet.getDouble(1));
					}
				}

			}
		} catch (DatabaseException |GoAdminException | IOException | SQLException e) {

			GoLog.logger.error(e.getMessage());
			throw new GoAdminException(e.getMessage());

		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}

		return returnReport;
	}

}
