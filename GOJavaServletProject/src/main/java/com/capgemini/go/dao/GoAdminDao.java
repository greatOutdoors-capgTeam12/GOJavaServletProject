package com.capgemini.go.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.dto.ReturnReportRequestDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.dto.ViewDetailedSalesReportByProductDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.dto.WrongProductNotificationDTO;
import com.capgemini.go.exception.DeliveryTimeReportException;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.exception.ShelfTimeReportException;

public interface GoAdminDao {

	public List<WrongProductNotificationDTO> getNotification() throws GoAdminException, ConnectException;

	public List<Double> retrieveReturnReport(ReturnReportRequestDTO request)
			throws GoAdminException, ConnectException;

	boolean addProductMaster(UserDTO productmaster) throws GoAdminException, ConnectException;

	public List<UserDTO> viewProductMaster() throws GoAdminException, ConnectException;

	public String suggestFreqOrderProducts(String retailerId) throws GoAdminException, ConnectException;

	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Function Name : getMonthlyTimeReport 
	 * - Input Parameters : RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean> 
	 * - Throws : N/A 
	 * - Author : Vikas 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products and their Monthly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getMonthlyShelfTime(RetailerInventoryDTO queryArguments)throws ConnectException, ShelfTimeReportException;
       
	/*******************************************************************************************************
	 * - Function Name : getQuarterlyTimeReport 
	 * - Input Parameters :RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A 
	 * - Author : Vikas 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products and their Quarterly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getQuarterlyShelfTime(RetailerInventoryDTO queryArguments)throws ConnectException,  ShelfTimeReportException;

	/*******************************************************************************************************
	 * - Function Name : getYearlyTimeReport 
	 * - Input Parameters : RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean> 
	 * - Throws : N/A 
	 * - Author : Vikas 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products and their Yearly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getYearlyShelfTime(RetailerInventoryDTO queryArguments)throws ConnectException, ShelfTimeReportException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierProductCategoryDeliveryTime 
	 * - Input Parameters :RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A 
	 * - Author : Kunal 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all product categories and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getOutlierProductCategoryDeliveryTime(RetailerInventoryDTO queryArguments)throws ConnectException, DeliveryTimeReportException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemDeliveryTime 
	 * - Input Parameters : RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A 
	 * - Author : Kunal 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getOutlierItemDeliveryTime(RetailerInventoryDTO queryArguments)throws ConnectException, DeliveryTimeReportException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemInOutlierProductCategoryDeliveryTime 
	 * - Input Parameters : RetailerInventory queryArguments 
	 * - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A 
	 * - Author : Kunal 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all products in outlier categories and their Delivery time periods
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getOutlierItemInOutlierProductCategoryDeliveryTime(
			RetailerInventoryDTO queryArguments) throws ConnectException, DeliveryTimeReportException;
	
	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers 
	 * - Input Parameters : N/A
	 * - Return Type : List<RetailerInventoryBean> 
	 * - Throws : N/A 
	 * - Author : Kunal 
	 * - Creation Date : 21/9/2019 
	 * - Description : to get List of all retailers in database
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getListOfRetailers () throws ConnectException;
	// end of Shelf Time Report and Delivery Time Report

	public void setBonus(SalesRepDTO sr, double bonus)throws ConnectException;

	public double getBonus(SalesRepDTO sr) throws SalesRepresentativeException,ConnectException;

	public void setTarget(SalesRepDTO sr, double target)throws ConnectException;

	public double getTarget(SalesRepDTO sr) throws SalesRepresentativeException,ConnectException;

	public void setDiscount(RetailerDTO ret, double discount)throws ConnectException;

	public double getDiscount(RetailerDTO ret) throws RetailerException,ConnectException;

	SalesRepDTO viewSalesRepData(String salesRepId) throws GoAdminException,ConnectException;

	List<SalesRepDTO> viewAllSalesRepData() throws GoAdminException,ConnectException;

	RetailerDTO viewRetailerData(String RetailerId) throws GoAdminException,ConnectException;

	List<RetailerDTO> viewAllRetailerData() throws GoAdminException,ConnectException;

	List<ViewSalesReportByUserDTO> viewSalesReportByCategory(Date entry, Date exit, int cat)
			throws GoAdminException,ConnectException;

	List<ViewSalesReportByUserDTO> viewSalesReportByUser(Date entry, Date exit, String TargetuserId)
			throws GoAdminException,ConnectException;

	List<ViewSalesReportByUserDTO> viewSalesReportByUserAndCategory(Date entry, Date exit, String TargetuserId,
			int category) throws GoAdminException,ConnectException;

	List<ViewSalesReportByUserDTO> viewSalesReportALLUserAndCategory(Date entry, Date exit)
			throws GoAdminException,ConnectException;

	List<ViewDetailedSalesReportByProductDTO> viewDetailedSalesReportByProduct(Date entry, Date exit, int cat)
			 throws GoAdminException,ConnectException;

}
