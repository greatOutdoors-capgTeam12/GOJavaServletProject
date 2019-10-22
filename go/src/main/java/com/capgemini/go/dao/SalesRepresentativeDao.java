package com.capgemini.go.dao;

import java.net.ConnectException;
import java.util.List;

import com.capgemini.go.dto.OrderCancelDTO;
import com.capgemini.go.dto.OrderProductMapDTO;
import com.capgemini.go.dto.OrderReturnDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.SalesRepresentativeException;

public interface SalesRepresentativeDao {

	List<OrderProductMapDTO> getOrderProductMap(String orderId) throws SalesRepresentativeException, ConnectException;

	boolean returnOrder(OrderReturnDTO or) throws SalesRepresentativeException, ConnectException;

	boolean checkDispatchStatus(String orderId) throws SalesRepresentativeException, ConnectException;

	boolean updateOrderProductMap(String orderId) throws SalesRepresentativeException, ConnectException;

	String validateUser(String orderId) throws SalesRepresentativeException, ConnectException;

	int getCountProduct(String orderId, String productId) throws SalesRepresentativeException, ConnectException;

	boolean updateOrderProductMapByQty(String orderId, String productId, int qty) throws SalesRepresentativeException, ConnectException;

	boolean updateOrderReturn(String orderId, String productId, String userId, String reason, int qty)
			throws SalesRepresentativeException, ConnectException;
	
	String getOrderDetails(String orderId) throws Exception;

	boolean checkSalesRepId(String userId) throws Exception;

	ProductDTO getProductDetails(String orderId, String productId) throws Exception;

	boolean checkDispatchStatusForCancelling(String orderId) throws Exception;
	
	List<OrderProductMapDTO> getOrderProductMapForCancelling(String orderId)  throws Exception;

	String cancelOrder(OrderCancelDTO oc) throws Exception;

	int getProductQuantityOrdered(String orderId, String productId) throws Exception;

	String cancelProduct(String orderId, String productId, int productQty, int qty) throws Exception;

	String updateOrderCancelForProduct(String orderId, String productId, int productQtyOrdered, int qty, String userId)
			throws Exception;
	
	String getTargetSales(String userId) throws Exception;
	
	String getBonus(String userId) throws Exception;


}
