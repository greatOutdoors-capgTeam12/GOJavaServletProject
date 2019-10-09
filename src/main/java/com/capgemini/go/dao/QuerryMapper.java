package com.capgemini.go.dao;

public class QuerryMapper {

	public static final String ADD_PRODUCT = "INSERT INTO PRODUCT"
			+ "(PRODUCT_ID,PRODUCT_PRICE,PRODUCT_COLOUR,PRODUCT_DIMENSION,PRODUCT_QUANTITY,PRODUCT_SPECIFICATION,PRODUCT_CATEGORY,PRODUCT_MANUFACTURER,PRODUCT_NAME)"
			+ "VALUES(?,?,?,?,?,?,?,?,?)";

	public static final String ADD_PRODUCT_WITH_UIN = "INSERT INTO PRODUCT_UIN_MAP"
			+ "(PRODUCT_ID,PRODUCT_UIN,PRODUCT_IS_PRESENT)" + "VALUES(?,?,?)";

	public static final String COUNT_NO_OF_PRODUCT = "SELECT COUNT(*) FROM PRODUCT_UIN_MAP WHERE PRODUCT_ID = ? ";

	public static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET PRODUCT_PRICE = ?, PRODUCT_COLOUR = ?, PRODUCT_DIMENSION = ?, PRODUCT_SPECIFICATION = ?, PRODUCT_CATEGORY = ?, PRODUCT_MANUFACTURER = ? , PRODUCT_NAME = ?"
			+ " WHERE PRODUCT_ID = ?";

	public static final String GET_PRODUCT_BY_PRODUCT_ID = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";

	public static final String DELETE_PRODUCT = "UPDATE PRODUCT_UIN_MAP  SET PRODUCT_IS_PRESENT = 0 WHERE PRODUCT_ID = ? ";
	public static final String SOFT_DELETE_PRODUCT = "UPDATE PRODUCT SET PRODUCT_QUANTITY = -1 WHERE PRODUCT_ID = ?";
	public static final String IS_PRODUCT_PRESENT = " SELECT COUNT(*) FROM PRODUCT_UIN_MAP WHERE PRODUCT_ID = ? AND PRODUCT_IS_PRESENT = 1 ";
	public static final String PRODUCT_PRESENT = " SELECT COUNT(*) FROM PRODUCT_UIN_MAP WHERE PRODUCT_ID = ? ";
	public static final String INCREASE_QUANTITY = "UPDATE PRODUCT SET PRODUCT_QUANTITY = ? WHERE PRODUCT_ID = ?";

	public static final String GET_PRODUCT_MAP = "SELECT * FROM ORDER_PRODUCT_MAP WHERE ORDER_ID=?";

	public static final String ADD_RETURN_ORDER = "INSERT INTO ORDER_RETURN"
			+ "(ORDER_ID,USER_ID,PRODUCT_ID,PRODUCT_UIN,ORDER_RETURN_TIME,ORDER_RETURN_REASON,ORDER_RETURN_STATUS)"
			+ "VALUES(?,?,?,?,?,?,?)";

	public static final String GET_ALL_PRODUCT = "SELECT * FROM PRODUCT  WHERE PRODUCT_QUANTITY >= 0 ORDER BY PRODUCT_NAME";
	public static final String GET_PRODUCT_BY_PRODUCT_NAME = "SELECT * FROM PRODUCT WHERE UPPER(PRODUCT_NAME) LIKE ?  AND PRODUCT_QUANTITY >= 0 ORDER BY PRODUCT_NAME";
	public static final String GET_PRODUCT_BY_FILTER = "SELECT * FROM PRODUCT WHERE UPPER(PRODUCT_NAME) LIKE ? AND UPPER(PRODUCT_COLOUR) LIKE ? AND PRODUCT_PRICE >= ? AND PRODUCT_PRICE <= ? AND PRODUCT_CATEGORY = ?  AND UPPER(PRODUCT_MANUFACTURER) LIKE ?"
			+ "AND PRODUCT_QUANTITY >= 0 ORDER BY PRODUCT_NAME";
	public static final String GET_PRODUCT_BY_FILTER_NO_CATEGORY = "SELECT * FROM PRODUCT WHERE UPPER(PRODUCT_NAME) LIKE ? AND UPPER(PRODUCT_COLOUR) LIKE ? AND PRODUCT_PRICE >= ? AND PRODUCT_PRICE <= ?   AND UPPER(PRODUCT_MANUFACTURER) LIKE ?"
			+ "AND PRODUCT_QUANTITY >= 0 ORDER BY PRODUCT_NAME";
	public static final String PRODUCT_MASTER_REGISTRATION = "UPDATE USER SET USER_CATEGORY=4 WHERE USER_ID = ?";

	public static final String COUNT_NO_OF_PRODUCT_FROM_PRODUCT = "SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_ID = ?";

	public static final String IS_ADDRESS_PRESENT = "SELECT COUNT(*) FROM ADDRESS WHERE USER_ID=?";

	public static final String ADD_FREQ_PRODUCT_TO_DB = "INSERT INTO FREQUENTLY_ORDERED_LIST"
			+ "(PRODUCT_ID, ADDRESS_ID, USER_ID)" + "VALUES (?,?,?)";

	public static final String CHECK_ORDER_DISPATCH_STATUS = "SELECT ORDER_DISPATCH_STATUS FROM `ORDER` WHERE ORDER_ID=?";

	public static final String GET_CART_ID = "SELECT CARTID FROM RETAILER WHERE RETAILER_ID=?";
	public static final String GET_NUMBER_OF_PRODUCTS = "SELECT COUNT * FROM PRODUCT_UIN_MAP WHERE PRODUCT_ID=?";

	public static final String UPDATE_CART = "INSERT INTO CART VALUES(?, ?, ?)";

	public static final String NEW_USER_REGISTRATION = "INSERT INTO USER"

			+ "(USER_NAME,USER_ID,USER_MAIL,USER_PASSWORD,USER_CONTACT,USER_CATEGORY,USER_ACTIVE_STATUS)"
			+ "VALUES(?,?,?,?,?,?,?)";

	public static final String USER_EXISTS = "SELECT * FROM USER ";

	public static final String USER_CATEGORY = "SELECT USER_CATEGORY FROM USER WHERE USER_ID=?";

	public static final String USER_PASSWORD_CHECK = "SELECT USER_PASSWORD FROM USER WHERE USER_ID=?";

	public static final String IS_ORDER_PRESENT = "SELECT * FROM `ORDER` WHERE ORDER_ID=?";

	public static final String ADD_CANCEL_ORDER = "INSERT INTO ORDER_CANCEL VALUES(?,?,?,?,?,?)";

	public static final String CHANGE_ACTIVE_STATUS = "UPDATE USER SET USER_ACTIVE_STATUS=1 WHERE USER_ID=?";

	public static final String CHANGE_ACTIVE_STATUS_OFF = "UPDATE USER SET USER_ACTIVE_STATUS=0 WHERE USER_ID=?";

	// Retailer Inventory Table access and insert and update queries
	public static final String GET_PRODUCTS_AND_SHELFTIMEPERIOD_BY_RETAILER_ID_AND_GIVEN_YEAR_AND_QUARTER = "SELECT `PRODUCT_CATEGORY`, `PRODUCT_UIN`, DATEDIFF(`PRODUCT_SALE_TIMESTAMP`, `PRODUCT_RECEIVE_TIMESTAMP`) "
			+ "AS `SHELF_TIMEPERIOD` FROM RETAILER_INVENTORY WHERE RETAILER_ID=? AND YEAR(`PRODUCT_SALE_TIMESTAMP`)=? "
			+ "AND (MONTH(`PRODUCT_SALE_TIMESTAMP`) BETWEEN ? AND ?) ORDER BY `SHELF_TIMEPERIOD`";

	public static final String GET_PRODUCTS_AND_SHELFTIMEPERIOD_BY_RETAILER_ID_AND_GIVEN_YEAR = "SELECT `PRODUCT_CATEGORY`, `PRODUCT_UIN`, DATEDIFF(`PRODUCT_SALE_TIMESTAMP`, `PRODUCT_RECEIVE_TIMESTAMP`) "
			+ "AS `SHELF_TIMEPERIOD` FROM RETAILER_INVENTORY WHERE RETAILER_ID=? AND YEAR(`PRODUCT_SALE_TIMESTAMP`)=? ORDER BY `SHELF_TIMEPERIOD`";

	public static final String GET_PRODUCTS_AND_SHELFTIMEPERIOD_BY_RETAILER_ID_AND_GIVEN_YEAR_AND_MONTH = "SELECT `PRODUCT_CATEGORY`, `PRODUCT_UIN`, DATEDIFF(`PRODUCT_SALE_TIMESTAMP`, `PRODUCT_RECEIVE_TIMESTAMP`) "
			+ "AS `SHELF_TIMEPERIOD` FROM RETAILER_INVENTORY WHERE RETAILER_ID=? AND YEAR(`PRODUCT_SALE_TIMESTAMP`)=? "
			+ "AND MONTH(`PRODUCT_SALE_TIMESTAMP`)=? ORDER BY `SHELF_TIMEPERIOD`";

	public static final String GET_PRODUCTS_AND_DELIVERYTIMEPERIOD_BY_RETAILER_ID = "SELECT `PRODUCT_CATEGORY`, `PRODUCT_UIN`, DATEDIFF(`PRODUCT_RECEIVE_TIMESTAMP`, `PRODUCT_DISPATCH_TIMESTAMP`) "
			+ "AS `DELIVERY_TIMEPERIOD` FROM RETAILER_INVENTORY WHERE RETAILER_ID=? ORDER BY `DELIVERY_TIMEPERIOD`";

	public static final String GET_PRODUCTS_AND_DELIVERYTIMEPERIOD_BY_RETAILER_ID_AND_PRODUCTCATEGORY = "SELECT `PRODUCT_CATEGORY`, AVG(DATEDIFF(`PRODUCT_RECEIVE_TIMESTAMP`, `PRODUCT_DISPATCH_TIMESTAMP`)) AS `AVG_DELIVERY_TIMEPERIOD` "
			+ "FROM RETAILER_INVENTORY WHERE RETAILER_ID=? GROUP BY PRODUCT_CATEGORY ORDER BY AVG_DELIVERY_TIMEPERIOD";

	public static final String GET_OUTLIER_PRODUCTS_AND_DELIVERYTIMEPERIOD_BY_RETAILER_ID_AND_PRODUCTCATEGORY = "SELECT `PRODUCT_CATEGORY`, `PRODUCT_UIN`, DATEDIFF(`PRODUCT_RECEIVE_TIMESTAMP`, `PRODUCT_DISPATCH_TIMESTAMP`) "
			+ "AS `DELIVERY_TIMEPERIOD` FROM RETAILER_INVENTORY WHERE RETAILER_ID=? AND PRODUCT_CATEGORY=? ORDER BY `DELIVERY_TIMEPERIOD`";

	public static final String UPDATE_PRODUCT_RECEIVE_TIMESTAMP_BY_RETAILER_ID_AND_PRODUCT_UIN = "UPDATE `RETAILER_INVENTORY` SET `PRODUCT_RECEIVE_TIMESTAMP`=? WHERE `RETAILER_ID`=? AND `PRODUCT_UIN`=?";

	public static final String UPDATE_PRODUCT_SALE_TIMESTAMP_BY_RETAILER_ID_AND_PRODUCT_UIN = "UPDATE `RETAILER_INVENTORY` SET `PRODUCT_SALE_TIMESTAMP`=? WHERE `RETAILER_ID`=? AND `PRODUCT_UIN`=?";

	// need to add one more update query when an order with items is dispatched
	// end of Retailer Inventory Table access and insert and update queries

	public static final String GET_WRONG_PRODUCT_NOTIFICATION = "SELECT PRODUCT_NAME,USER.USER_NAME,USER.USER_CATEGORY,ORDER_RETURN.ORDER_RETURN_TIME FROM ((ORDER_RETURN INNER JOIN USER ON ORDER_RETURN.USER_ID = USER.USER_ID ) INNER JOIN PRODUCT ON ORDER_RETURN.PRODUCT_ID = PRODUCT.PRODUCT_ID) WHERE UPPER(ORDER_RETURN.ORDER_RETURN_REASON) LIKE '%WRONG%' ";
	public static final String GET_USER_STATUS = "SELECT `USER_ACTIVE_STATUS`,`USER_CATEGORY` FROM USER WHERE USER_ID = ?";

	public static final String CHECK_PHONE_NUMBER = "SELECT COUNT(*) FROM USER WHERE USER_CONTACT = ?";

	public static final String GET_ORDER_PRODUCT_MAP = "SELECT * FROM ORDER_PRODUCT_MAP WHERE ORDER_ID=? AND PRODUCT_STATUS=1";

	public static final String UPDATE_ORDER_PRODUCT_MAP = "UPDATE  ORDER_PRODUCT_MAP SET PRODUCT_STATUS=0 WHERE ORDER_ID=?";

	public static final String VALIDATE_USER = "SELECT USER_ID FROM `ORDER` WHERE ORDER_ID=?";

	public static final String VALIDATE_NUMBER_EMAIL = "SELECT COUNT(*) FROM USER WHERE USER_CONTACT = ? OR USER_MAIL = ?";

	public static final String COUNT_PRODUCT = "SELECT COUNT(*) FROM ORDER_PRODUCT_MAP WHERE ORDER_ID=? AND PRODUCT_ID=? ";

	public static final String CHANGE_PRODUCT_ADDRESS = "UPDATE `FREQUENTLY_ORDERED_LIST` SET `ADDRESS_ID`=? WHERE `USER_ID`=? AND `PRODUCT_ID`=?";

	public static final String ADD_FREQ_PRODUCTS = "INSERT INTO FREQUENTLY_ORDERED_LIST (USER_ID, PRODUCT_ID, ADDRESS_ID) SELECT `ORDER`.USER_ID, `ORDER_PRODUCT_MAP`.PRODUCT_ID, `ORDER`.ADDRESS_ID FROM `ORDER` INNER JOIN `ORDER_PRODUCT_MAP` USING (ORDER_ID) WHERE USER_ID=? AND PRODUCT_ID=? GROUP BY `ADDRESS_ID`";

	public static final String SUGGEST_FREQ_PRODUCTS = "SELECT `PRODUCT_ID`, COUNT(*) FROM `ORDER` INNER JOIN `ORDER_PRODUCT_MAP` USING (ORDER_ID) WHERE USER_ID = ?  GROUP BY `PRODUCT_ID` ORDER BY COUNT(*) DESC";
	// public static final String COUNT_PRODUCT = "SELECT COUNT(*) FROM
	// ORDER_PRODUCT_MAP WHERE ORDER_ID=? AND PRODUCT_ID=?";

	public static final String UPDATE_ORDER_PRODUCT_MAP_BY_QTY = "UPDATE ORDER_PRODUCT_MAP SET PRODUCT_STATUS = 0 WHERE PRODUCT_UIN IN (SELECT * FROM (SELECT PRODUCT_UIN FROM ORDER_PRODUCT_MAP WHERE ORDER_ID = ? AND PRODUCT_ID = ? LIMIT ?) AS L)";

	public static final String GET_PRODUCT_UIN = "SELECT PRODUCT_UIN FROM ORDER_PRODUCT_MAP WHERE ORDER_ID = ? AND PRODUCT_ID = ? LIMIT ?";
	


	public static final String UPDATE_ORDER_PRODUCT_MAP_WITH_PRODUCT_UIN = "UPDATE ORDER_PRODUCT_MAP SET PRODUCT_STATUS = 0 WHERE ORDER_ID = ? AND PRODUCT_ID = ? AND PRODUCT_UIN =?";
	public static final String GET_PRODUCT_QUANTITY = "SELECT COUNT(*) FROM ORDER_PRODUCT_MAP WHERE PRODUCT_STATUS = 1 AND ORDER_ID = ? AND PRODUCT_ID = ?";
	public static final String UPDATE_ORDER_PRODUCT_MAP_CANCEL_PROD_EQUAL_QUANTITY = "UPDATE ORDER_PRODUCT_MAP SET PRODUCT_STATUS = 0 WHERE ORDER_ID =? AND PRODUCT_ID=?";
	public static final String UPDATE_ORDER_PRODUCT_MAP_CANCEL_PROD_LESS_QUANTITY  = "UPDATE ORDER_PRODUCT_MAP SET PRODUCT_STATUS = 0 WHERE PRODUCT_UIN IN (SELECT * FROM (SELECT PRODUCT_UIN FROM ORDER_PRODUCT_MAP WHERE ORDER_ID = ? AND PRODUCT_ID = ? LIMIT ?) AS L)";
	public static final String GET_ORDER_PRODUCT_MAP_CANCEL_PROD_EQUAL_QUANTITY = "SELECT * FROM ORDER_PRODUCT_MAP WHERE ORDER_ID = ? AND PRODUCT_ID=? AND PRODUCT_STATUS = 0";
	public static final String GET_ORDER_PRODUCT_MAP_CANCEL_PROD_LESS_QUANTITY = "SELECT * FROM ORDER_PRODUCT_MAP WHERE PRODUCT_UIN IN (SELECT * FROM (SELECT PRODUCT_UIN FROM ORDER_PRODUCT_MAP WHERE ORDER_ID = ? AND PRODUCT_ID = ? AND PRODUCT_STATUS = 0 LIMIT ?) AS L)";

	// Azhar Queries
	public static final String GET_CART_DETAILS_BY_RETAILER_ID = "SELECT `CART_ID` FROM `RETAILER` WHERE `USER_ID`=?";
	public static final String GET_CART_DETAILS_BY_SALES_REP_ID = "SELECT `CART_ID` FROM `SALES_REP` WHERE `USER_ID`=?";
	public static final String INSERT_PRODUCT_INTO_CART = "INSERT INTO `CART` VALUES (?, ?, ?)"; // CART_ID, PRODUCT_ID,
																									// PRODUCT_QUANTITY
	public static final String GET_USER_CATEGORY = "SELECT `USER_CATEGORY` FROM `USER` WHERE `USER_ID`=?";
	// end of Azhar Queries

	public static final String GET_ALL_PRODUCT_MASTER = "SELECT `USER_NAME`,`USER_ID`,`USER_MAIL`,`USER_CONTACT` FROM USER WHERE `USER_CATEGORY` = 4 ";

	public static final String ADD_ITEM_TO_CART = "INSERT INTO CART_ITEM (RETAILER_ID,PRODUCT_ID,QUANTITY)  VALUES(?,?,?)";
	public static final String INCREASE_CART_ITEM = "UPDATE CART_ITEM SET `QUANTITY` = ? WHERE RETAILER_ID = ? AND PRODUCT_ID = ?";
	public static final String GET_CART_ITEM = "SELECT `PRODUCT_ID`,`QUANTITY` FROM CART_ITEM WHERE RETAILER_ID=?";
	public static final String GET_PRODUCT_DETAILS = "SELECT `PRODUCT_UIN` FROM PRODUCT_UIN_MAP WHERE PRODUCT_ID = ? AND PRODUCT_IS_PRESENT = 1 LIMIT ?";
	public static final String UPDATE_PRODUCT_STORE = "UPDATE PRODUCT_UIN_MAP SET `PRODUCT_IS_PRESENT` = 0 WHERE PRODUCT_UIN = ?";
	public static final String UPDATE_PRODUCT_INVENTORY = "UPDATE PRODUCT SET PRODUCT_QUANTITY = PRODUCT_QUANTITY - ? WHERE PRODUCT_ID = ?";
	public static final String INITIATE_ORDER = "INSERT INTO `ORDER` (`ORDER_ID`,`ORDER_DISPATCH_STATUS`,`ORDER_DISPATCH_TIME`,`USER_ID`,`ADDRESS_ID`,`ORDER_INITIATE_TIME`) VALUES(?,0,NULL,?,?,?)";
	public static final String UPDATE_ORDER_PRODUCT_MAP_ENTRY = "INSERT INTO ORDER_PRODUCT_MAP (`ORDER_ID`,`PRODUCT_ID`,`PRODUCT_UIN`,`PRODUCT_STATUS`,`GIFT_STATUS`) VALUES(?,?,?,1,0)";
	public static final String GET_PROD_QTY = "SELECT COUNT(*) FROM PRODUCT_UIN_MAP WHERE PRODUCT_ID = ? AND PRODUCT_IS_PRESENT=1";
	public static final String GET_PROD_PRESENT_STATUS = "SELECT COUNT(*) FROM CART_ITEM WHERE PRODUCT_ID = ? AND RETAILER_ID = ?";
	public static final String CART_ITEM_QTY = "SELECT QUANTITY FROM CART_ITEM WHERE PRODUCT_ID = ? AND RETAILER_ID = ?";
	public static final String DELETE_ORDER = "DELETE FROM ORDER WHERE ORDER_ID = ?";
	public static final String DELETE_CART = "DELETE FROM CART_ITEM WHERE RETAILER_ID = ?";
	public static final String ORDER_COUNT = "SELECT COUNT(*) FROM `ORDER`";

	// Queries for Return Report
	public static final String RETURN_REPORT_FOR_WRONG_PRODUCT_YEARLY = "SELECT SUM(PRODUCT.PRODUCT_PRICE) FROM ORDER_RETURN INNER JOIN PRODUCT ON ORDER_RETURN.PRODUCT_ID = PRODUCT.PRODUCT_ID WHERE UPPER(ORDER_RETURN.ORDER_RETURN_REASON) LIKE '%WRONG%' "
			+ "AND ORDER_RETURN.ORDER_RETURN_STATUS = 1 AND YEAR(ORDER_RETURN.ORDER_RETURN_TIME) = ? ";
	public static final String RETURN_REPORT_FOR_INCOMPLETE_PRODUCT_YEARLY = "SELECT SUM(PRODUCT.PRODUCT_PRICE) FROM ORDER_RETURN INNER JOIN PRODUCT ON ORDER_RETURN.PRODUCT_ID = PRODUCT.PRODUCT_ID WHERE UPPER(ORDER_RETURN.ORDER_RETURN_REASON) LIKE '%INCOMPLETE%' "
			+ "AND ORDER_RETURN.ORDER_RETURN_STATUS = 1 AND YEAR(ORDER_RETURN.ORDER_RETURN_TIME) = ? ";
	public static final String RETURN_REPORT_FOR_WRONG_PRODUCT_MONTHLY = "SELECT SUM(PRODUCT.PRODUCT_PRICE) FROM ORDER_RETURN INNER JOIN PRODUCT ON ORDER_RETURN.PRODUCT_ID = PRODUCT.PRODUCT_ID WHERE UPPER(ORDER_RETURN.ORDER_RETURN_REASON) LIKE '%WRONG%' "
			+ "AND ORDER_RETURN.ORDER_RETURN_STATUS = 1 AND YEAR(ORDER_RETURN.ORDER_RETURN_TIME) = ?  AND MONTH(ORDER_RETURN.ORDER_RETURN_TIME) = ? ";
	public static final String RETURN_REPORT_FOR_INCOMPLETE_PRODUCT_MONTHLY = "SELECT SUM(PRODUCT.PRODUCT_PRICE) FROM ORDER_RETURN INNER JOIN PRODUCT ON ORDER_RETURN.PRODUCT_ID = PRODUCT.PRODUCT_ID WHERE UPPER(ORDER_RETURN.ORDER_RETURN_REASON) LIKE '%INCOMPLETE%' "
			+ "AND ORDER_RETURN.ORDER_RETURN_STATUS = 1 AND YEAR(ORDER_RETURN.ORDER_RETURN_TIME) = ?  AND MONTH(ORDER_RETURN.ORDER_RETURN_TIME) = ?";
	public static final String RETURN_REPORT_FOR_INCOMPLETE_PRODUCT_QUATERLY = "SELECT SUM(PRODUCT.PRODUCT_PRICE) FROM ORDER_RETURN INNER JOIN PRODUCT ON ORDER_RETURN.PRODUCT_ID = PRODUCT.PRODUCT_ID WHERE UPPER(ORDER_RETURN.ORDER_RETURN_REASON) LIKE '%INCOMPLETE%' "
			+ "AND ORDER_RETURN.ORDER_RETURN_STATUS = 1 AND YEAR(ORDER_RETURN.ORDER_RETURN_TIME) = ?  AND MONTH(ORDER_RETURN.ORDER_RETURN_TIME) BETWEEN ? AND ?";
	public static final String RETURN_REPORT_FOR_WRONG_PRODUCT_QUATERLY = "SELECT SUM(PRODUCT.PRODUCT_PRICE) FROM ORDER_RETURN INNER JOIN PRODUCT ON ORDER_RETURN.PRODUCT_ID = PRODUCT.PRODUCT_ID WHERE UPPER(ORDER_RETURN.ORDER_RETURN_REASON) LIKE '%WRONG%' "
			+ "AND ORDER_RETURN.ORDER_RETURN_STATUS = 1 AND YEAR(ORDER_RETURN.ORDER_RETURN_TIME) = ?  AND MONTH(ORDER_RETURN.ORDER_RETURN_TIME) BETWEEN ? AND ?";

	
	
	//sales Rep queries
	public static final String SELECT_SALES_REP_BONUS = "select BONUS from SALES_REP where USER_ID= ? ";
	public static final String SELECT_SALES_REP_TARGET = "select TARGET_SALES from SALES_REP where USER_ID= ? ";
	public static final String GET_TARGET_STATUS = "SELECT TARGET_STATUS FROM SALES_REP WHERE USER_ID = ?";
	public static final String IS_SALES_REP_ID_PRESENT = "SELECT USER_ID FROM SALES_REP WHERE USER_ID = ?";
	
	


	public static final String SELECT_RETAILER_DISCOUNT = "select DISCOUNT from RETAILER where USER_ID= ? ";

	public static final String SELECT_SALES_REP = "select USER_ID,TARGET_SALES,TARGET_STATUS,CURRENT_SALES,BONUS from SALES_REP where USER_ID= ? ";

	public static final String SELECT_ALL_SALES_REP = "select USER_ID,TARGET_SALES,TARGET_STATUS,CURRENT_SALES,BONUS from SALES_REP";

	public static final String SELECT_REVENUE_BY_MONTH = "SELECT`ORDER_ID`,`USER_ID`,MONTH(`ORDER_INITIATE_TIME`),`PRODUCT_ID`,`PRODUCT_UIN`,`PRODUCT_PRICE`,`PRODUCT_CATEGORY`FROM`ORDER` INNER JOIN `ORDER_PRODUCT_MAP`USING(ORDER_ID) INNER JOIN `PRODUCT` USING (PRODUCT_ID)";
	public static final String SELECT_DATA_FROM_DATABASE = "SELECT `ORDER_ID`, `USER_ID` , `ORDER_INITIATE_TIME` , `PRODUCT_ID` , `PRODUCT_UIN` , `PRODUCT_PRICE` , `PRODUCT_CATEGORY` FROM `ORDER` INNER JOIN `ORDER_PRODUCT_MAP` USING (ORDER_ID) INNER JOIN `PRODUCT` USING (PRODUCT_ID)";
	// public static final String SELECT_RETAILER = ""

	public static final String SELECT_RETAILER = "select USER_ID , DISCOUNT from RETAILER where USER_ID= ?";

	public static final String SELECT_ALL_RETAILER = "select USER_ID , DISCOUNT from RETAILER";
	public static final String SELECT_USER = "SELECT * FROM USER WHERE USER_ID = ?";

// Address functionality queries	
	public static final String COUNT_USERID_IN_ADDRESSDB = "SELECT `USER_ID`, COUNT(*) FROM `ADDRESS` WHERE `USER_ID` = ?";
	public static final String CHECK_USERID_AND_ADDRESSID_IN_ADDRESSDB = "SELECT * FROM ADDRESS WHERE  ADDRESS_ID=? AND  USER_ID=? ";
	public static final String CHECK_ORDERID_IN_ORDERDB = "SELECT COUNT(*) FROM ORDER WHERE ORDER_ID=?";
	public static final String INSERT_NEW_ADDRESS_IN_ADDRESSDB = "INSERT INTO `ADDRESS` (`ADDRESS_ID`, `USER_ID`, `CITY`, `STATE`, `ZIP`, `BUILDING_NO`, `COUNTRY`, `BASE_ADDRESS_STATUS`) VALUES (?,?,?,?,?,?,?,?)";
	public static final String UPDATE_ADDRESS_IN_ADDRESSDB = "UPDATE `ADDRESS` SET  `ADDRESS_ID`= ? , `USER_ID`= ? , `CITY`= ? ,`STATE`= ? ,`ZIP`= ? ,`BUILDING_NO`= ? ,`COUNTRY`= ? ,`BASE_ADDRESS_STATUS`= ? WHERE ADDRESS_ID=?";
	public static final String CHANGE_ORDER_ADDRESS = "UPDATE `ORDER` SET ADDRESS_ID = ? WHERE `ORDER_ID`= ?  AND `USER_ID`=?";
	public static final String DELETE_ADDRESS_IN_ADDRESSDB = "UPDATE `ADDRESS` SET `BASE_ADDRESS_STATUS`= 0 WHERE ADDRESS_ID=? ";

}
