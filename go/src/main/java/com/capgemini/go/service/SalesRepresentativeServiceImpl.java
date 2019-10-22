package com.capgemini.go.service;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.capgemini.go.dao.SalesRepresentativeDao;
import com.capgemini.go.dao.SalesRepresentativeDaoImpl;
import com.capgemini.go.dto.OrderCancelDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.OrderProductMapDTO;
import com.capgemini.go.dto.OrderReturnDTO;
import com.capgemini.go.exception.OrderNotFoundException;
import com.capgemini.go.exception.ProductNotFoundException;
import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.PropertiesLoader;

public class SalesRepresentativeServiceImpl implements SalesRepresentativeService {

	private static Properties exceptionProps = null;
	private static Properties goProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";
	private static final String GO_PROPERTIES_FILE = "goUtility.properties";

	private SalesRepresentativeDao salesRepDao = new SalesRepresentativeDaoImpl();

	/*******************************************************************************************************
	 * - Function Name : returnOrder - Input Parameters : String orderId ,String
	 * userId,String reason- Return Type : - Throws :SalesRepresentativeException -
	 * Author : CAPGEMINI - Creation Date : 23/09/2019 - Description : Return order
	 * calls the dao calls checkDispatchStatus ,getOrderProductMap,and returnOrder
	 * along with updateProductMap
	 ********************************************************************************************************/
	public boolean returnOrder(String orderId, String userId, String reason) throws SalesRepresentativeException {

		boolean statusOrderReturn = false;
		boolean orderProductMapStatus = false;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			if (salesRepDao.checkDispatchStatus(orderId)) {
				List<OrderProductMapDTO> opm = salesRepDao.getOrderProductMap(orderId);
				Date dt = new Date();
				for (OrderProductMapDTO index : opm) {
					OrderReturnDTO or = new OrderReturnDTO(orderId, userId, index.getProductId(), index.getProductUIN(),
							dt, reason, 1);
					statusOrderReturn = salesRepDao.returnOrder(or);
				}
				if (statusOrderReturn) {
					orderProductMapStatus = salesRepDao.updateOrderProductMap(orderId);
				}
			} else {
				GoLog.logger.error(exceptionProps.getProperty("not_yet_dispatched"));
				throw new SalesRepresentativeException(exceptionProps.getProperty("not_yet_dispatched"));
			}
		} catch (SalesRepresentativeException | IOException e) {
			throw new SalesRepresentativeException(exceptionProps.getProperty("failure_order"));

		}

		return orderProductMapStatus;
	}

	/*******************************************************************************************************
	 * - Function Name : returnProduct - Input Parameters : String orderId,String
	 * productId,int qty,String reason - Return Type :boolean- Throws
	 * :SalesRepresentativeException - Author : CAPGEMINI - Creation Date :
	 * 23/09/2019 - Description : checking whether the order is at all despatched
	 ********************************************************************************************************/
	public boolean returnProduct(String orderId, String userId, String productId, int qty, String reason)
			throws SalesRepresentativeException {
		boolean returnProductStatus = false;
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			if (salesRepDao.checkDispatchStatus(orderId)) {
				int countProd = salesRepDao.getCountProduct(orderId, productId);
				if (countProd >= qty) {
					salesRepDao.updateOrderProductMapByQty(orderId, productId, qty);
					salesRepDao.updateOrderReturn(orderId, productId, userId, reason, qty);
					returnProductStatus = true;
				} else {
					GoLog.logger.error(exceptionProps.getProperty("prod_not_ordered"));
					throw new SalesRepresentativeException(exceptionProps.getProperty("prod_not_ordered"));
				}
			} else {
				GoLog.logger.error(exceptionProps.getProperty("not_yet_dispatched"));
				throw new SalesRepresentativeException(exceptionProps.getProperty("not_yet_dispatched"));
			}
		} catch (SalesRepresentativeException | IOException e) {
			throw new SalesRepresentativeException(exceptionProps.getProperty("failure_order"));
		}

		return returnProductStatus;
	}

	

	/*******************************************************************************************************
	 * - Function Name : cancelProduct - Input Parameters : String orderId, String
	 * productID, int qty - Return Type : - Throws :SalesRepresentativeException -
	 * Author : CAPGEMINI - Creation Date : 23/09/2019 - Description : Cancel Order
	 * to database calls dao method getOrderDetails(sr)
	 * 
	 * @throws SalesRepresentativeException
	 ********************************************************************************************************/

	@Override
	public String validateUser(String orderId) throws SalesRepresentativeException, ConnectException {
		return (salesRepDao.validateUser(orderId));
	}

	//------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : cancelOrder() 
	 * - Input Parameters : String orderId, String userId
	 * - Return Type : 
	 * - Throws :SalesRepresentativeException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Cancel Order to database calls dao method getOrderDetails(orderId)
	 ********************************************************************************************************/

	public String cancelOrder(String orderId, String userId) throws Exception {

		@SuppressWarnings("unused")
		OrderDTO order = null;
		@SuppressWarnings("unused")
		OrderProductMapDTO opm = null;
		List<OrderProductMapDTO> list = null;
		String statusOrderCancel = null;
		System.out.println("Cancelling of order is being processed");
		if (salesRepDao.getOrderDetails(orderId) == null) {
			throw new OrderNotFoundException("No such order id exists");
		} else if (salesRepDao.checkSalesRepId(userId) == false) {
			throw new SalesRepresentativeException("Sales Representative id is invalid");
		} else if ((salesRepDao.checkDispatchStatusForCancelling(orderId)) == true) {
			throw new OrderNotFoundException("Order cant be cancelled as it is dispatched! Return the order");
		} else if (salesRepDao.getOrderProductMapForCancelling(orderId).isEmpty() == true) {
			throw new OrderNotFoundException("Products are not mapped with order");
		} else {
			list = salesRepDao.getOrderProductMapForCancelling(orderId);
			Iterator<OrderProductMapDTO> itr = list.iterator();
			Date dt = new Date();
			int i = 0;
			while (itr.hasNext()) {
				OrderCancelDTO oc = new OrderCancelDTO(orderId, userId, list.get(i).getProductId(),
						list.get(i).getProductUIN(), dt, 0);
				statusOrderCancel = salesRepDao.cancelOrder(oc);
				i++;
				itr.next();
			}
			statusOrderCancel = "Order has been cancelled";
		}
		return statusOrderCancel;

	}
	
	

	
	//------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : cancelProduct 
	 * - Input Parameters : String orderId, String userId, String productID, int qty 
	 * - Return Type : String 
	 * - Throws : SalesRepresentativeException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Cancel Order to database calls dao method getOrderDetails(orderId)
	 ********************************************************************************************************/

	public String cancelProduct(String orderId, String userId, String productId, int qty) throws Exception {
		@SuppressWarnings("unused")
		OrderDTO order = null;
		@SuppressWarnings("unused")
		OrderProductMapDTO opm = null;
		List<OrderProductMapDTO> list = null;
		@SuppressWarnings("unused")
		List<OrderDTO> listOrder = null;
		String statusProductCancel = null;
		if (salesRepDao.getOrderDetails(orderId) == null) {
			throw new OrderNotFoundException("No such order id exists");
		} else if (salesRepDao.checkSalesRepId(userId) == false) {
			throw new SalesRepresentativeException("Sales Representaive id is invalid");
		} else if ((salesRepDao.checkDispatchStatusForCancelling(orderId)) == true) {
			throw new OrderNotFoundException(
					"Selected Product(s) cant be cancelled as it is dispatched! Return the Product");
		} else if (salesRepDao.getOrderProductMapForCancelling(orderId).isEmpty() == true) {
			throw new OrderNotFoundException("Products are not mapped with order");
		} else {
			list = salesRepDao.getOrderProductMapForCancelling(orderId);
			@SuppressWarnings("unused")
			int totalQuantityOrdered = list.size();
			@SuppressWarnings("unused")
			String statusOrderCancelForProduct = null;
			int productQtyOrdered = salesRepDao.getProductQuantityOrdered(orderId, productId);
			if (productQtyOrdered == 0) {
				System.out.println("Invalid product id");
			} else {
				if (productQtyOrdered < qty) {
					throw new ProductNotFoundException(
							"Quantity of product cancelled cant be greater than the quantity of product ordered");
				} else if (productQtyOrdered == qty) {

					statusProductCancel = salesRepDao.cancelProduct(orderId, productId, productQtyOrdered, qty);
					statusOrderCancelForProduct = salesRepDao.updateOrderCancelForProduct(orderId, productId,
							productQtyOrdered, qty, userId);
					statusProductCancel = "The given products are canceled";
				} else if (productQtyOrdered > qty) {
					statusProductCancel = salesRepDao.cancelProduct(orderId, productId, productQtyOrdered, qty);
					statusOrderCancelForProduct = salesRepDao.updateOrderCancelForProduct(orderId, productId,
							productQtyOrdered, qty, userId);
					statusProductCancel = "The given products are canceled";
				}
			}
			return statusProductCancel;
		}
	}
	
	//------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkTargetSales 
	 * - Input Parameters : String userId 
	 * - Return Type : 
	 * - Throws : SalesRepresentativeException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Cancel Order to database calls dao method getOrderDetails(sr)
	 ********************************************************************************************************/

	public String checkTargetSales(String userId) throws Exception {

		String targetStatus = null;
		if (salesRepDao.checkSalesRepId(userId) == false) {
			throw new SalesRepresentativeException("Sales Representaive id is invalid");
		} else {
			targetStatus = salesRepDao.getTargetSales(userId);
		}

		return targetStatus;

	}
	
	//------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkBonus() 
	 * - Input Parameters : String userId 
	 * - Return Type : String
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Cancel Order to database calls dao method getOrderDetails(sr)
	 ********************************************************************************************************/

	public String checkBonus(String userId) throws Exception {
		String bonus = null;
		if (salesRepDao.checkSalesRepId(userId) == false) {
			throw new SalesRepresentativeException("Sales Representaive id is invalid");
		} else {
			bonus = salesRepDao.getBonus(userId);
		}
		return bonus;
	}

}
