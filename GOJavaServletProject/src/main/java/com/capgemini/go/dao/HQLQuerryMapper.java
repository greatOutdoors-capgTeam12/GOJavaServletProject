package com.capgemini.go.dao;

public class HQLQuerryMapper {

	public static final String GET_ALL_PRODUCTS = "FROM ProductEntity prod WHERE prod.quantity >= 0 ORDER BY prod.productName";
	
	
	
	public static final String IS_ORDER_PRESENT = "FROM OrderEntity WHERE orderId = :ordID";
	
	
}
