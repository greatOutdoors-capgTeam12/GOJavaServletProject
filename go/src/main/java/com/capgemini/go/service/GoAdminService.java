package com.capgemini.go.service;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.ReturnReportRequestDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.dto.ViewDetailedSalesReportByProductDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.dto.WrongProductNotificationDTO;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.SalesRepresentativeException;

public interface GoAdminService {
	// Others functions

	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Author : Kunal - Creation Date : 21/9/2019 - Description : Static
	 * Enumeration for Different Report Types
	 ********************************************************************************************************/
	public static enum ReportType {
		MONTHLY_SHELF_TIME, QUARTERLY_SHELF_TIME, YEARLY_SHELF_TIME, OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME,
		OUTLIER_ITEM_DELIVERY_TIME, OUTLIER_ITEM_IN_OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME
	}

	/*******************************************************************************************************
	 * - Function Name : getShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their shelf time
	 * periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getShelfTimeReport(ReportType reportType, String retailerId,
			Calendar dateSelection)throws ConnectException;

	/*******************************************************************************************************
	 * - Function Name : getDeliveryTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Delivery time
	 * periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getDeliveryTimeReport(ReportType reportType, String retailerId,
			int productCategory)throws ConnectException;
	// end of Shelf Time Report and Delivery Time Report

	List<WrongProductNotificationDTO> getNotification() throws GoAdminException, ConnectException;

	List<Double> retrieveReturnReport(ReturnReportRequestDTO request) throws GoAdminException, ConnectException;

	List<UserDTO> viewProductMaster() throws GoAdminException, ConnectException;

	String suggestFreqOrderProducts(String userID) throws GoAdminException, ConnectException;

	public boolean addProductMaster(UserDTO productMaster) throws GoAdminException, ConnectException;

	void setBonus(SalesRepDTO sr, double bonus)throws ConnectException;

	double getBonus(SalesRepDTO sr) throws SalesRepresentativeException, ConnectException;

	void setTarget(SalesRepDTO sr, double target)throws ConnectException;

	double getTarget(SalesRepDTO sr) throws SalesRepresentativeException, ConnectException;

	void setDiscount(RetailerDTO ret, double discount)throws ConnectException;

	double getDiscount(RetailerDTO ret) throws RetailerException, ConnectException;

	SalesRepDTO viewSalesRepData(String salesRepId) throws GoAdminException, ConnectException;

	List<SalesRepDTO> viewAllSalesRepData() throws GoAdminException, ConnectException;

	RetailerDTO viewRetailerData(String RetailerId) throws GoAdminException, ConnectException;

	List<RetailerDTO> viewAllRetailerData() throws GoAdminException, ConnectException;

	List<ViewSalesReportByUserDTO> viewSalesReportByCategory(Date entry, Date exit, int cat) throws GoAdminException, ConnectException;

	List<ViewSalesReportByUserDTO> viewSalesReportByUser(Date entry, Date exit, String TargetuserId)
			throws GoAdminException, ConnectException;

	List<ViewSalesReportByUserDTO> viewSalesReportByUserAndCategory(Date entry, Date exit, String TargetuserId,
			int category) throws GoAdminException, ConnectException;

	List<ViewSalesReportByUserDTO> viewSalesReportALLUserAndCategory(Date entry, Date exit) throws GoAdminException, ConnectException;

	List<ViewDetailedSalesReportByProductDTO> viewDetailedSalesReportByProduct(Date entry, Date exit, int cat)
			throws GoAdminException, ConnectException;

}
