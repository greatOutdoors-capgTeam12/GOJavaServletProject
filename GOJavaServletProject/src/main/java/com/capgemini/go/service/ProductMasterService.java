package com.capgemini.go.service;

import java.net.ConnectException;
import java.sql.Connection;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ProductMasterException;

public interface ProductMasterService {

	boolean addProduct(ProductDTO product) throws ProductMasterException, ConnectException;

	boolean updateProduct(ProductDTO product) throws ProductMasterException, ConnectException;

	boolean deleteProduct(String productId) throws ProductMasterException, ConnectException;

	boolean addExistingProduct(ProductDTO product) throws ProductMasterException, ConnectException;

}
