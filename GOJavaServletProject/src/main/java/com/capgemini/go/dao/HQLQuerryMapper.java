package com.capgemini.go.dao;

public class HQLQuerryMapper {

	public static final String GET_ALL_PRODUCTS = "FROM ProductEntity prod WHERE prod.quantity >= 0 ORDER BY prod.productName";
	


	
	public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.orderId, order.userId, order.orderInitiateTime, "
			+ "opm.prodId, opm.productUIN, prod.price, prod.quantity FROM OrderEntity order "
			+ "INNER JOIN ProductEntity as prod ON opm.prodId = prod.prodId ";
			
		//"SELECT `ORDER_ID`, `USER_ID` , `ORDER_INITIATE_TIME` FROM ORDER
		//"SELECT `ORDER_ID`, `PRODUCT_ID` , `PRODUCT_UIN` FROM ORDER_PRODUCT_MAP
		//"SELECT  `PRODUCT_ID`,`PRODUCT_PRICE` , `PRODUCT_CATEGORY` FROM PRODUCT
		//, , `PRODUCT_UIN` ,  FROM `ORDER` INNER JOIN `ORDER_PRODUCT_MAP` USING (ORDER_ID) INNER JOIN `PRODUCT` USING (PRODUCT_ID)";
			

	public static final String IS_ORDER_PRESENT = "FROM OrderEntity WHERE orderId = :ordID";
	

	public static final String UPDATE_ORDER_PRODUCT_MAP="UPDATE OrderProductMapEntity opm SET opm.productStatus=0 WHERE ORDER_ID=:orderId ";

	// Time Report Queries
	public static final String GET_ITEMS_FOR_MONTH = "";
	// End of Time Report Queries
}
//INNER JOIN OrderProductMapPK as opm ON order.orderId = opm.orderId 