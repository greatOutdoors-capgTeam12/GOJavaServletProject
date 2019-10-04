package com.capgemini.go.dao;

import java.net.ConnectException;
import java.sql.Connection;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ProductMasterException;

public interface ProductMasterDao {

	boolean addProduct(ProductDTO product) throws ProductMasterException, ConnectException;

	boolean addExistingProduct(ProductDTO product) throws ProductMasterException, ConnectException;

	boolean updateProduct(ProductDTO product) throws ProductMasterException, ConnectException;

	boolean deleteProduct(String productId) throws ProductMasterException, ConnectException;

}
