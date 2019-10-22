package com.capgemini.go.service;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.Properties;

import com.capgemini.go.dao.ProductMasterDao;
import com.capgemini.go.dao.ProductMasterDaoImpl;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ProductMasterException;

public class ProductMasterServiceImpl implements ProductMasterService {

	private static Properties exceptionProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";

	private ProductMasterDao productMaster = new ProductMasterDaoImpl();

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : addProduct - Input Parameters : Product - Return Type :
	 * boolean - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : to add a product in product database
	 * 
	 * @throws ProductMasterException
	 ********************************************************************************************************/
	public boolean addProduct(ProductDTO product) throws ProductMasterException, ConnectException {

		boolean productAddStatus = false;
		productAddStatus = productMaster.addProduct(product);
		return productAddStatus;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : updateProduct - Input Parameters : Product - Return Type :
	 * boolean - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : to update a particular product
	 * 
	 * @throws ProductMasterException
	 ********************************************************************************************************/
	public boolean updateProduct(ProductDTO product) throws ProductMasterException, ConnectException {

		boolean productUpdateStatus = false;
		productUpdateStatus = productMaster.updateProduct(product);
		return productUpdateStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : deleteProduct - Input Parameters : productId, userId -
	 * Return Type : boolean - Throws : - Author : CAPGEMINI - Creation Date :
	 * 21/9/2019 - Description : to delete a product in product database
	 * 
	 * @throws ProductMasterException
	 ********************************************************************************************************/
	public boolean deleteProduct(String productId) throws ProductMasterException, ConnectException {

		boolean productDeleteStatus = false;
		productDeleteStatus = productMaster.deleteProduct(productId);
		return productDeleteStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : addExistingProduct - Input Parameters : Product - Return
	 * Type : boolean - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : to increase product Qty in product database
	 * 
	 * @throws ProductMasterException
	 ********************************************************************************************************/
	public boolean addExistingProduct(ProductDTO product) throws ProductMasterException, ConnectException {

		boolean productIncreaseStatus = false;
		productIncreaseStatus = productMaster.addExistingProduct(product);
		return productIncreaseStatus;
	}

}
