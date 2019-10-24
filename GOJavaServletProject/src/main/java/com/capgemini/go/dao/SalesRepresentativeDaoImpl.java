package com.capgemini.go.dao;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dto.OrderCancelDTO;
import com.capgemini.go.dto.OrderProductMapDTO;
import com.capgemini.go.dto.OrderReturnDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.entity.OrderProductMapEntity;
import com.capgemini.go.entity.ProductEntity;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.utility.Constants;
import com.capgemini.go.utility.DbConnection;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.HibernateUtil;
import com.capgemini.go.utility.PropertiesLoader;

public class SalesRepresentativeDaoImpl implements SalesRepresentativeDao {

	private static Properties exceptionProps = null;
	private static Properties goProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";
	private static final String GO_PROPERTIES_FILE = "goUtility.properties";


	/*******************************************************************************************************
	 * - Function Name : returnOrder 
	 * - Input Parameters : OrderReturnDTO
	 * - Return Type : boolean 
	 * - Throws :SalesRepresentativeException, ConnectException
	 * - Author : CAPGEMINI
	 * - Creation Date : 23/09/2019
	 * - Description : Return order adds the respective order in the order_return table in the database 
	 ********************************************************************************************************/
	@Override
	public boolean returnOrder(OrderReturnDTO or) throws SalesRepresentativeException, ConnectException {
		boolean returnOrderStatus = false;
		OrderProductMapDTO opm = null;
		Connection connection = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();

			PreparedStatement statement = connection.prepareStatement(QuerryMapper.ADD_RETURN_ORDER);
			statement.setString(1, or.getOrderId());
			statement.setString(2, or.getUserId());
			statement.setString(3, or.getProductId());
			statement.setString(4, or.getProductUIN());
			java.util.Date utilDate = or.getOrderReturnTime();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			statement.setDate(5, sqlDate);
			statement.setString(6, or.getReturnReason());

			statement.setInt(7, or.getOrderReturnStatus());
			int numberOfRows = statement.executeUpdate();
			returnOrderStatus = true;
		} catch (SQLException | IOException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("return_order_failure"));
			throw new SalesRepresentativeException(exceptionProps.getProperty("return_order_failure"));
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return returnOrderStatus;
	}

	/*******************************************************************************************************
	 * - Function Name : updateOrderProductMap
	 * - Input Parameters : String orderId
	 * - Return Type : boolean 
	 * - Throws :SalesRepresentativeException, ConnectException
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : updating Order_Product_Map in the database by setting product_status=0 for the products that have been returned
	 ********************************************************************************************************/

	/*public boolean updateOrderProductMap(String orderId) throws SalesRepresentativeException, ConnectException {
		boolean orderProductMapFlag = false;
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.UPDATE_ORDER_PRODUCT_MAP);
			statement.setString(1, orderId);
			int numberOfRows = statement.executeUpdate();
			orderProductMapFlag = true;
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("order_product_map_failure"));
			throw new SalesRepresentativeException(exceptionProps.getProperty("order_product_map_failure"));
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}

		return orderProductMapFlag;

	}*/
	
	public boolean updateOrderProductMap(String orderId)throws SalesRepresentativeException, ConnectException{
		boolean orderProductMapFlag = false;
		Session session = null;
		SessionFactory sessionFactory = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			OrderProductMapEntity opm=new OrderProductMapEntity();
			Query query=session.createQuery(HQLQuerryMapper.UPDATE_ORDER_PRODUCT_MAP);
			query.setParameter("orderId", orderId);
			int result=query.executeUpdate();
			
			

			session.getTransaction().commit();
		} catch (HibernateException | IOException exp) {
			session.getTransaction().rollback();
			session.close();
			throw new SalesRepresentativeException(exceptionProps.getProperty("order_product_map_failure"));
		}

		return orderProductMapFlag;
		
	}

	/*******************************************************************************************************
	 * - Function Name : getOrderProductMap 
	 * - Input Parameters : String orderId
	 * - Return Type :List<ORderProductMap>
	 * - Throws :SalesRepresentativeException, ConnectException 
	 * - Author : CAPGEMINI
	 * - Creation Date : 23/09/2019 
	 * - Description : getting all the products against a particular order
	 ********************************************************************************************************/

	@Override
	public List<OrderProductMapDTO> getOrderProductMap(String orderId) throws SalesRepresentativeException, ConnectException {
		List<OrderProductMapDTO> orderProductMap = new ArrayList<OrderProductMapDTO>();
		Connection connection = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.GET_ORDER_PRODUCT_MAP);
			statement.setString(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String productId = resultSet.getString("PRODUCT_ID");
				String productUIN = resultSet.getString("PRODUCT_UIN");
				int productStatus = resultSet.getInt("PRODUCT_STATUS");
				orderProductMap.add(new OrderProductMapDTO(orderId, productId, productUIN,
						productStatus == 1 ? true : false, false));
			}
		} catch (DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty("product_return_failure"));
			throw new SalesRepresentativeException(exceptionProps.getProperty("product_return_failure"));

		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return orderProductMap;

	}

	/*******************************************************************************************************
	 * - Function Name : checkDispatchStatus
	 * - Input Parameters : String orderId
	 * - Return Type :List<OrderReturnEntity>
	 * - Throws :SalesRepresentativeException, ConnectException
	 * - Author : CAPGEMINI
	 * - Creation Date : 23/09/2019
	 * - Description : checking whether the order is at all despatched
	 ********************************************************************************************************/
	@Override
	public boolean checkDispatchStatus(String orderId) throws SalesRepresentativeException, ConnectException {
		boolean dispatchStatus = false;
		Connection connection = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.CHECK_ORDER_DISPATCH_STATUS);
			statement.setString(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			dispatchStatus = (resultSet.getInt(1) == 1) ? true : false;
		} catch (SQLException | IOException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("orderId_not_found_failure"));
			throw new SalesRepresentativeException(exceptionProps.getProperty("orderId_not_found_failure"));
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return dispatchStatus;
	}

	/*******************************************************************************************************
	 * - Function Name : validateUser
	 * - Input Parameters : String orderId
	 * - Return Type :String
	 * - Throws :SalesRepresentativeException, ConnectException
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019
	 * - Description : checking whether the order is linked with a particular user
	 ********************************************************************************************************/
	@Override
	public String validateUser(String orderId) throws SalesRepresentativeException, ConnectException {
		String user = null;
		Connection connection = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.VALIDATE_USER);
			statement.setString(1, orderId);
			ResultSet resultSet = null;
			resultSet = statement.executeQuery();
			resultSet.next();

			user = resultSet.getString(1);

		} catch (SQLException | IOException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("validate_user_error"));
			throw new SalesRepresentativeException(exceptionProps.getProperty("validate_user_error"));
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return user;

	}

	/*******************************************************************************************************
	 * - Function Name : getCountProduct
	 * - Input Parameters : String orderId,String productId 
	 * - Return Type : int
	 * - Throws :SalesRepresentativeException, ConnectException 
	 * - Author : CAPGEMINI
	 * - Creation Date : 23/09/2019
	 * - Description : getting the count of the products ordered against a particular order
	 ********************************************************************************************************/

	@Override
	public int getCountProduct(String orderId, String productId) throws SalesRepresentativeException, ConnectException {
		int count = 0;

		Connection connection = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.COUNT_PRODUCT);
			statement.setString(1, orderId);
			statement.setString(2, productId);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			count = resultSet.getInt(1);

		} catch (SQLException | IOException | DatabaseException e) {
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return count;
	}

	/*******************************************************************************************************
	 * - Function Name : updateOrderProductMapByQty 
	 * - Input Parameters : String orderId, String productId, int qty 
	 * - Return Type :boolean
	 * - Throws SalesRepresentativeException 
	 * - Author : CAPGEMINI
	 * - Creation Date : 23/09/2019 
	 * - Description : Updates the respective product status in the order product map
	 * @throws SalesRepresentativeException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	@Override
	public boolean updateOrderProductMapByQty(String orderId, String productId, int qty)
			throws SalesRepresentativeException, ConnectException {
		boolean updateStatus = false;
		Connection connection = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.UPDATE_ORDER_PRODUCT_MAP_BY_QTY);
			statement.setString(1, orderId);
			statement.setString(2, productId);
			statement.setInt(3, qty);

			int update = statement.executeUpdate();
			updateStatus = true;

		} catch (SQLException | IOException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("order_product_map_error"));
			throw new SalesRepresentativeException(exceptionProps.getProperty("order_product_map_error"));
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}

		return updateStatus;

	}

	/*******************************************************************************************************
	 * - Function Name : updateOrderReturn 
	 * - Input Parameters : String orderId,
	 * - String productID, int qty ,String reason,String userId 
	 * - Return Type :boolean
	 * - Throws :SalesRepresentativeException
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Upload the respective products in the orderReturn
	 * Table
	 * @throws SalesRepresentativeException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	@Override
	public boolean updateOrderReturn(String orderId, String productId, String userId, String reason, int qty)
			throws SalesRepresentativeException, ConnectException {
		SalesRepresentativeDao salesRepDao = new SalesRepresentativeDaoImpl();
		boolean orderReturnStatus = false;
		Date dt = new Date();
		Connection connection = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);

			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(QuerryMapper.GET_PRODUCT_UIN);
			statement.setString(1, orderId);
			statement.setString(2, productId);
			statement.setInt(3, qty);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				salesRepDao.returnOrder(
						new OrderReturnDTO(orderId, userId, productId, resultSet.getString(1), dt, reason, 1));
			}
			orderReturnStatus = true;

		} catch (SQLException | IOException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("order_product_map_error"));
			throw new SalesRepresentativeException(exceptionProps.getProperty("order_product_map_error"));
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return orderReturnStatus;
	}
	
	
	
	
	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getOrderDetails(String orderId) 
	 * - Input Parameters : orderId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Checking if orderId exists and also getting order details
	 ********************************************************************************************************/

	@Override
	public String getOrderDetails(String orderId) throws Exception {

		

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String orderID = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			statement = connection.prepareStatement(QuerryMapper.IS_ORDER_PRESENT);
			statement.setString(1, orderId);
			resultSet = statement.executeQuery();
			resultSet.next();
			orderID = resultSet.getString(1);
			if(orderID != null) {
				return orderID;
			}
		} catch (Exception e) {
			GoLog.logger.error(exceptionProps.getProperty("orderId_not_found_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return orderID;


	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkSalesRepId(String userId) 
	 * - Input Parameters : userId 
	 * - Return Type : boolean
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Checking if userId exists
	 ********************************************************************************************************/

	@Override
	public boolean checkSalesRepId(String userId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean checkSalesRepIdFlag = false;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			statement = connection.prepareStatement(QuerryMapper.IS_SALES_REP_ID_PRESENT);
			statement.setString(1, userId);
			resultSet = statement.executeQuery();
			resultSet.next();
			String userID = resultSet.getString(1);
			if (userID != null) {
				checkSalesRepIdFlag = true;
				return checkSalesRepIdFlag;
			}
		} catch (Exception e) {
			GoLog.logger.error(exceptionProps.getProperty("userId_not_found_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return checkSalesRepIdFlag;

	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getProductDetails(String orderId, String productId)
	 * - Input Parameters : orderId, productId
	 * - Return Type : Product 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Check if product exists or not
	 ********************************************************************************************************/

	@Override
	public ProductDTO getProductDetails(String orderId, String productId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ProductDTO product = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			DbConnection.getInstance();
			connection = DbConnection.getConnection();
			statement = connection.prepareStatement(QuerryMapper.IS_PRODUCT_PRESENT);
			statement.setString(1, productId);
			resultSet = statement.executeQuery();
			product = resultSet.getObject(1, ProductDTO.class);
		} catch (DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("productId_not_found_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return product;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkDispatchStatusForCancelling(String orderId)
	 * - Input Parameters : orderId 
	 * - Return Type : boolean 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Check if order is dispatched
	 ********************************************************************************************************/

	@Override
	public boolean checkDispatchStatusForCancelling(String orderId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean checkDispatchStatusFlag = false;
		int index = 0;

		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(QuerryMapper.CHECK_ORDER_DISPATCH_STATUS);
			statement.setString(1, orderId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				index = resultSet.getInt(1);
			}
			if (index == 1) {
				checkDispatchStatusFlag = true;
			}
		} catch (DatabaseException | IOException | SQLException se) {
			GoLog.logger.error(exceptionProps.getProperty("productId_not_found_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return checkDispatchStatusFlag;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getOrderProductMapForCancelling(String orderId)
	 * - Input Parameters : orderId 
	 * - Return Type : List<OrderReturnEntity>
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : To get a list of type OrderReturnEntity
	 ********************************************************************************************************/

	@Override
	public List<OrderProductMapDTO> getOrderProductMapForCancelling(String orderId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		OrderProductMapDTO opm = null;
		List<OrderProductMapDTO> list = null;
		list = new ArrayList<OrderProductMapDTO>();
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(QuerryMapper.GET_PRODUCT_MAP);
			statement.setString(1, orderId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int productStatus = resultSet.getInt("PRODUCT_STATUS");
				if (productStatus != 1) {
					GoLog.logger.error(exceptionProps.getProperty("order_return_failure"));
					throw new SalesRepresentativeException(exceptionProps.getProperty("product_cancel_failure"));
				} else {
					String productId = resultSet.getString("PRODUCT_ID");
					String productUIN = resultSet.getString("PRODUCT_UIN");
					opm = new OrderProductMapDTO(orderId, productId, productUIN, (productStatus == 0 ? false : true),
							false);
					list.add(opm);
				}
			}

		} catch (SalesRepresentativeException | DatabaseException | SQLException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty("orderId_not_found_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return list;
	}

	// ------------------------ 1. GO Application --------------------------
	/**************************************************************************************************************
	 * - Function Name : cancelOrder(OrderCancelEntity orderCancel)
	 * - Input Parameters : OrderCancelEntity orderCancel 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Adding rows to OrderCancelEntity table and updating OrderReturnEntity  after canceling the product
	 **************************************************************************************************************/

	@Override
	public String cancelOrder(OrderCancelDTO orderCancel) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		String cancelOrderStatus = "Order cant be cancelled";
		int i = 0;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(QuerryMapper.ADD_CANCEL_ORDER);
			statement.setString(1, orderCancel.getOrderId());
			statement.setString(2, orderCancel.getUserId());
			statement.setString(3, orderCancel.getProductId());
			statement.setString(4, orderCancel.getProductUIN());
			java.util.Date utilDate = orderCancel.getOrderCancelTime();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			statement.setDate(5, sqlDate);
			statement.setInt(6, 1);
			i = statement.executeUpdate();
			System.out.println("The order-cancel table's " + i + " rows has been inserted");
			statement2 = connection.prepareStatement(QuerryMapper.UPDATE_ORDER_PRODUCT_MAP_WITH_PRODUCT_UIN);
			statement2.setString(1, orderCancel.getOrderId());
			statement2.setString(2, orderCancel.getProductId());
			statement2.setString(3, orderCancel.getProductUIN());
			int j = statement2.executeUpdate();
			System.out.println("The order-product-map table's " + j + " rows has been updated");
			cancelOrderStatus = "The product with the uin" + orderCancel.getProductUIN() + "has been cancelled";
		} catch (SQLException | IOException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty(" return_order_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return cancelOrderStatus;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getProductQuantityOrdered(String orderId, String productId)
	 * - Input Parameters : orderId, productId
	 * - Return Type : int 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Return the quantity of product ordered
	 ********************************************************************************************************/

	@Override
	public int getProductQuantityOrdered(String orderId, String productId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int productQuantity = 0;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(QuerryMapper.GET_PRODUCT_QUANTITY);
			statement.setString(1, orderId);
			statement.setString(2, productId);
			resultSet = statement.executeQuery();
			resultSet.next();
			productQuantity = resultSet.getInt(1);
		} catch (SQLException e) {
			GoLog.logger.error(exceptionProps.getProperty("product_quantity_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return productQuantity;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : cancelProduct(String orderId, String productId, int productQty, int qty) 
	 * - Input Parameters : orderId, productId, productQty, qty 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Updating the Order_Cancel table after canceling the product
	 ********************************************************************************************************/

	@Override
	public String cancelProduct(String orderId, String productId, int productQty, int qty) throws Exception {
		String cancelProductStatus = "Product cant be cancelled";
		Connection connection = null;
		PreparedStatement statement = null;
		int rowsChanged = 0;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			if (productQty == qty) {
				statement = connection
						.prepareStatement(QuerryMapper.UPDATE_ORDER_PRODUCT_MAP_CANCEL_PROD_EQUAL_QUANTITY);
				statement.setString(1, orderId);
				statement.setString(2, productId);
				rowsChanged = statement.executeUpdate();
			} else if (productQty > qty) {
				statement = connection
						.prepareStatement(QuerryMapper.UPDATE_ORDER_PRODUCT_MAP_CANCEL_PROD_LESS_QUANTITY);
				statement.setString(1, orderId);
				statement.setString(2, productId);
				statement.setInt(3, qty);
				rowsChanged = statement.executeUpdate();
			}
			cancelProductStatus = "The given quantity of product has been cancelled and" + String.valueOf(rowsChanged)
			+ "rows has been changed";
			System.out.println(cancelProductStatus);
			return cancelProductStatus;
		} catch (SQLException e) {
			GoLog.logger.error(exceptionProps.getProperty("product_quantity_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return cancelProductStatus;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************************
	 * - Function Name : updateOrderCancelForProduct(String orderId, String productId, int productQtyOrdered, int qty,
						String userId) 
	 * - Input Parameters : orderId, productId, productQtyOrdered, qty, userId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Adding rows to OrderCancelEntity table after canceling the product
	 ******************************************************************************************************************/

	@Override
	public String updateOrderCancelForProduct(String orderId, String productId, int productQtyOrdered, int qty,
			String userId) throws Exception {
		String statusCancelOrderForProduct = null;
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet resultSet = null;
		int rowsChanged = 0;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			if (productQtyOrdered == qty) {
				statement = connection.prepareStatement(QuerryMapper.GET_ORDER_PRODUCT_MAP_CANCEL_PROD_EQUAL_QUANTITY);
				statement.setString(1, orderId);
				statement.setString(2, productId);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					statement2 = connection.prepareStatement(QuerryMapper.ADD_CANCEL_ORDER);
					statement2.setString(1, resultSet.getString(1));
					statement2.setString(2, userId);
					statement2.setString(3, resultSet.getString(2));
					statement2.setString(4, resultSet.getString(3));
					Date date = new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					statement2.setDate(5, sqlDate);
					statement2.setInt(6, 1);
					rowsChanged = statement2.executeUpdate();
					System.out.println("The order-cancel table's " + rowsChanged + " rows has been inserted");
				}

			} else if (productQtyOrdered > qty) {
				statement = connection.prepareStatement(QuerryMapper.GET_ORDER_PRODUCT_MAP_CANCEL_PROD_LESS_QUANTITY);
				statement.setString(1, orderId);
				statement.setString(2, productId);
				statement.setInt(3, qty);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					statement2 = connection.prepareStatement(QuerryMapper.ADD_CANCEL_ORDER);
					statement2.setString(1, resultSet.getString(1));
					statement2.setString(2, userId);
					statement2.setString(3, resultSet.getString(2));
					statement2.setString(4, resultSet.getString(3));
					Date date2 = new Date();
					java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
					statement2.setDate(5, sqlDate2);
					statement2.setInt(6, 1);
					rowsChanged = statement2.executeUpdate();
					System.out.println("The order-cancel table's " + rowsChanged + " columns has been updated");
				}
			}
			statusCancelOrderForProduct = "The given quantity of product has been cancelled";
		} catch (SQLException e) {
			GoLog.logger.error(exceptionProps.getProperty(" return_order_failure"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return statusCancelOrderForProduct;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getTargetSales(String userId)
	 * - Input Parameters : userId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Return Target Sales and Target Status for a Sales Representative
	 ********************************************************************************************************/

	@Override
	public String getTargetSales(String userId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		String targetStatus = null;
		int targetSalesStatus = 0;
		String status = null;
		double targetSales = 0.0;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(QuerryMapper.SELECT_SALES_REP_TARGET);
			statement.setString(1, userId);
			resultSet = statement.executeQuery();
			resultSet.next();
			targetSales = resultSet.getDouble(1);
			statement2 = connection.prepareStatement(QuerryMapper.GET_TARGET_STATUS);
			statement2.setString(1, userId);
			resultSet2 = statement2.executeQuery();
			resultSet2.next();
			targetSalesStatus = resultSet2.getInt(1);
			if(targetSalesStatus==-1) {
				status = "exceeded";
			}else if(targetSalesStatus==0) {
				status = "met";
			}else {
				status = "not met";
			}
			targetStatus = "Your target sales is " + String.valueOf(targetSales) + " and target status is "
					+ status;
		} catch (SQLException | IOException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("sales representative not found"));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return targetStatus;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getBonus(String userId) 
	 * - Input Parameters : userId 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Return Bonus offered to a Sales Representative 
	 ********************************************************************************************************/

	@Override
	public String getBonus(String userId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Double bonus = 0.0;
		String bonusForSales = null;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(QuerryMapper.SELECT_SALES_REP_BONUS);
			statement.setString(1, userId);
			resultSet = statement.executeQuery();
			resultSet.next();
			bonus = resultSet.getDouble(1);
			bonusForSales = "Your bonus is " + String.valueOf(bonus);
		} catch (SQLException | DatabaseException e) {
			GoLog.logger.error(exceptionProps.getProperty("sales representative not found"));
			throw new Exception("sales representative not found");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectException(Constants.connectionError);
			}
		}
		return bonusForSales;
	}
}
