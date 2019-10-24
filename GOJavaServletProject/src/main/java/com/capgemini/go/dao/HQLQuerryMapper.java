package com.capgemini.go.dao;

public class HQLQuerryMapper {

	public static final String GET_ALL_PRODUCTS = "FROM ProductEntity prod WHERE prod.quantity >= 0 ORDER BY prod.productName";
    public static final String CART_ITEM_QTY_FOR_PRODUCT_ID="select QUANTITY from CART_ITEM where PRODUCT_ID = :product_id";
    public static final String GET_PRODUCT_QTY_FROM_DB = "select PRODUCT_QUANTITY from PRODUCT where PRODUCT_ID = :product_id";
    public static final String UPDATE_QTY_IN_PRODUCT = "update PRODUCT set PRODUCT_QUANTITY = :quantity where PRODUCT_ID = :product_id";
    public static final String UPDATE_CART = "update CART_ITEM set QUANTITY= :quantity where PRODUCT_ID= :product_id";
}
