package com.capgemini.go.service;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.dao.GoAdminDao;
import com.capgemini.go.dao.GoAdminDaoImpl;
import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.dto.ReturnReportRequestDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.dto.ViewDetailedSalesReportByProductDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.dto.WrongProductNotificationDTO;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.utility.GoLog;

public class GoAdminServiceImpl implements GoAdminService {

	GoAdminDao goAdmin = new GoAdminDaoImpl();
	GoAdminDao go = new GoAdminDaoImpl();

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : viewProductMaster - Input Parameters : connection - Return
	 * Type : void - Throws : - Author : Agnibha - Creation Date : 21/9/2019 -
	 * Description : to get List of all product master
	 * 
	 * @throws GoAdminException
	 ********************************************************************************************************/
	public List<UserDTO> viewProductMaster() throws GoAdminException, ConnectException {

		return goAdmin.viewProductMaster();

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : sendNotification - Input Parameters :orderReturnDatabase,
	 * userId - Return Type : OrderReturnList Product List - Throws : - Author :
	 * CAPGEMINI - Creation Date : 21/9/2019 - Description : to send Notification if
	 * wrong product is shipped
	 * 
	 * @throws GoAdminException
	 ********************************************************************************************************/
	public List<WrongProductNotificationDTO> getNotification() throws GoAdminException, ConnectException {

		List<WrongProductNotificationDTO> notifications = new ArrayList<WrongProductNotificationDTO>();
		notifications = goAdmin.getNotification();
		return notifications;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : retrieve return report - Input Parameters : Return Report
	 * request , connection mode of report - Return Type : boolean - Throws : -
	 * Author : CAPGEMINI - Creation Date : 21/9/2019 - Description : to retrieve
	 * return report
	 * 
	 * @throws GoAdminException
	 ********************************************************************************************************/
	@Override
	public List<Double> retrieveReturnReport(ReturnReportRequestDTO request) throws GoAdminException, ConnectException {

		List<Double> report = goAdmin.retrieveReturnReport(request);
		return report;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : addProductMaster - Input Parameters :User productMaster -
	 * Return Type : boolean - Throws : - Author : CAPGEMINI - Creation Date :
	 * 21/9/2019 - Description : to add product Master
	 * 
	 * @throws GoAdminException
	 ********************************************************************************************************/
	public boolean addProductMaster(UserDTO productmaster) throws GoAdminException, ConnectException {
		boolean productMasterRegistrationStatus = false;
		productMasterRegistrationStatus = goAdmin.addProductMaster(productmaster);

		return productMasterRegistrationStatus;
	}

	//

	// Shelf time report and delivery time report
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their shelf time
	 * periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getShelfTimeReport(ReportType reportType, String retailerId,
			Calendar dateSelection)throws ConnectException {
		RetailerInventoryDTO riDto = new RetailerInventoryDTO(retailerId, 0, null, null, null, dateSelection); // DTO
																												// object
																												// to
																												// pass
																												// arguments
		GoAdminDao goAdminDao = new GoAdminDaoImpl(); // Creating an object for Accessing Dao Layer Methods
		List<RetailerInventoryBean> result = null; // List to store results from Dao Layer Methods
		switch (reportType) {
		case MONTHLY_SHELF_TIME: {
			result = goAdminDao.getMonthlyShelfTime(riDto);
			break;
		}
		case QUARTERLY_SHELF_TIME: {
			result = goAdminDao.getQuarterlyShelfTime(riDto);
			break;
		}
		case YEARLY_SHELF_TIME: {
			result = goAdminDao.getYearlyShelfTime(riDto);
			break;
		}
		default: {
			// get monthly report for current month of current year
			riDto.setProductShelfTimeOut(Calendar.getInstance());
			result = goAdminDao.getMonthlyShelfTime(riDto);
			break;
		}
		}
		return result;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getDeliveryTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Delivery time
	 * periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getDeliveryTimeReport(ReportType reportType, String retailerId,
			int productCategory)throws ConnectException {
		RetailerInventoryDTO riDto = new RetailerInventoryDTO(retailerId, productCategory, null, null, null, null); // DTO
																													// object
																													// to
																													// pass
																													// arguments
		GoAdminDao goAdminDao = new GoAdminDaoImpl(); // Creating an object for Accessing Dao Layer Methods
		List<RetailerInventoryBean> result = null; // List to store results from Dao Layer Methods
		switch (reportType) {
		case OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME: {
			result = goAdminDao.getOutlierProductCategoryDeliveryTime(riDto);
			break;
		}
		case OUTLIER_ITEM_DELIVERY_TIME: {
			result = goAdminDao.getOutlierItemDeliveryTime(riDto);
			break;
		}
		case OUTLIER_ITEM_IN_OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME: {
			result = goAdminDao.getOutlierItemInOutlierProductCategoryDeliveryTime(riDto);
			break;
		}
		default: {
			// get monthly report for current month of current year
			result = goAdminDao.getOutlierItemDeliveryTime(riDto);
			break;
		}
		}
		return result;
	}
	// end of Shelf time report and delivery time report

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : suggestFreqOrderProducts - Input Parameters : - Return Type
	 * : void - Throws : - Author : CAPGEMINI - Creation Date : 21/9/2019 -
	 * Description : Admin will suggest products to retailer
	 * 
	 * @throws GoAdminException
	 ********************************************************************************************************/

	public String suggestFreqOrderProducts(String userID) throws GoAdminException, ConnectException {

		String pid = goAdmin.suggestFreqOrderProducts(userID);
		return pid;
	}

	public void setBonus(SalesRepDTO sr, double bonus)throws ConnectException {

		GoAdminDaoImpl ga = new GoAdminDaoImpl();
		ga.setBonus(sr, bonus);

	}

	public double getBonus(SalesRepDTO sr) throws SalesRepresentativeException, ConnectException {

		double bonus;
		GoAdminDaoImpl ga = new GoAdminDaoImpl();
		bonus = ga.getBonus(sr);
		return bonus;

	}

	public void setTarget(SalesRepDTO sr, double target)throws ConnectException{

		GoAdminDaoImpl ga = new GoAdminDaoImpl();
		ga.setTarget(sr, target);

	}

	public double getTarget(SalesRepDTO sr) throws SalesRepresentativeException, ConnectException {

		double target;
		GoAdminDaoImpl ga = new GoAdminDaoImpl();
		target = ga.getTarget(sr);
		return target;
	}

	public void setDiscount(RetailerDTO ret, double discount)throws ConnectException {

		GoAdminDaoImpl ga = new GoAdminDaoImpl();
		ga.setDiscount(ret, discount);

	}

	public double getDiscount(RetailerDTO ret) throws RetailerException, ConnectException {

		double discount;
		GoAdminDaoImpl ga = new GoAdminDaoImpl();
		discount = ga.getDiscount(ret);
		return discount;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewSalesRepData Input Parameters : salesRepId,connection
	 * Return Type : SalesRepDTO Throws : - Author : Rintu Creation Date : 21/9/2019
	 * Description : To view sales report of specific sales representative
	 ********************************************************************************************************/

	public SalesRepDTO viewSalesRepData(String salesRepId) throws GoAdminException, ConnectException {
		SalesRepDTO result = null;
		try {
			result = go.viewSalesRepData(salesRepId);
		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewAllSalesRepData - Input Parameters : connrction - Return
	 * Type : List - Throws : - Author : Rintu - Creation Date : 21/9/2019 -
	 * Description : To view sales report of all sales representative
	 ********************************************************************************************************/

	public List<SalesRepDTO> viewAllSalesRepData() throws GoAdminException, ConnectException {
		List<SalesRepDTO> result = new ArrayList<SalesRepDTO>();

		try {
			result = go.viewAllSalesRepData();

		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewAllRetailerData viewSalesRepData Input Parameters :
	 * connection RetailerId Return Type : List Throws : Author : Rintu Creation
	 * Date : 21/9/2019 Description : To view sales report of all retailer
	 ********************************************************************************************************/

	public List<RetailerDTO> viewAllRetailerData() throws GoAdminException, ConnectException {

		List<RetailerDTO> result = new ArrayList<RetailerDTO>();
		try {
			result = go.viewAllRetailerData();
		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewRetailerData Data Input Parameters :
	 * RetailerId,connection Return Type : RetailerDTO Throws : Author : Rintu
	 * Creation Date : 21/9/2019 Description : To view sales report of specific
	 * retailer
	 ********************************************************************************************************/

	public RetailerDTO viewRetailerData(String RetailerId) throws GoAdminException, ConnectException {

		RetailerDTO result = null;
		try {
			result = go.viewRetailerData(RetailerId);

		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewSalesReportByCategory Input Parameters : Category , entry
	 * , exit ,connection Return Type : List Throws : Author : Rintu Creation Date :
	 * 21/9/2019 Description : To view sales report of specific product within given
	 * dates ID within given date
	 ********************************************************************************************************/

	public List<ViewSalesReportByUserDTO> viewSalesReportByCategory(Date entry, Date exit, int cat)
			throws GoAdminException, ConnectException {
		List<ViewSalesReportByUserDTO> result = new ArrayList<ViewSalesReportByUserDTO>();
		try {
			result = go.viewSalesReportByCategory(entry, exit, cat);
		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewSalesReportByUser Input Parameters : TargetuserId , entry
	 * , exit, connection Return Type : List Throws : Author : Rintu Creation Date :
	 * 21/9/2019 Description : To view sales report of specific user within given
	 * date
	 ********************************************************************************************************/

	public List<ViewSalesReportByUserDTO> viewSalesReportByUser(Date entry, Date exit, String TargetuserId)
			throws GoAdminException, ConnectException {
		List<ViewSalesReportByUserDTO> result = new ArrayList<ViewSalesReportByUserDTO>();
		try {
			result = go.viewSalesReportByUser(entry, exit, TargetuserId);
		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewSalesReportByUserAndCategory Input Parameters :
	 * TargetuserId , entry , exit , category ,connection Return Type : List Throws
	 * : Author : Rintu Creation Date : 21/9/2019 Description : To view sales report
	 * of specific product and User within a given date
	 ********************************************************************************************************/

	public List<ViewSalesReportByUserDTO> viewSalesReportByUserAndCategory(Date entry, Date exit, String TargetuserId,
			int category) throws GoAdminException, ConnectException {
		List<ViewSalesReportByUserDTO> result = new ArrayList<ViewSalesReportByUserDTO>();
		try {
			result = go.viewSalesReportByUserAndCategory(entry, exit, TargetuserId, category);
		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewSalesReportALLUserAndCategory Input Parameters :
	 * connection , entry , exit , product Return Type : List Throws : Author :
	 * Rintu Creation Date : 21/9/2019 Description : To view sales reports of all
	 * user and all products within a given dates.
	 ********************************************************************************************************/

	public List<ViewSalesReportByUserDTO> viewSalesReportALLUserAndCategory(Date entry, Date exit)
			throws GoAdminException, ConnectException {

		List<ViewSalesReportByUserDTO> result = new ArrayList<ViewSalesReportByUserDTO>();
		try {
			result = go.viewSalesReportALLUserAndCategory(entry, exit);
		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * Function Name : viewDetailedSalesReportByProduct Input Parameters : category
	 * , entry , exit ,connection product Return Type : List Throws : Author : Rintu
	 * Creation Date : 21/9/2019 Description : To view amount change, percentage
	 * change, color code, month to month change of products
	 ********************************************************************************************************/

	public List<ViewDetailedSalesReportByProductDTO> viewDetailedSalesReportByProduct(Date entry, Date exit, int cat)
			throws GoAdminException, ConnectException {

		List<ViewDetailedSalesReportByProductDTO> result = new ArrayList<ViewDetailedSalesReportByProductDTO>();
		try {
			result = go.viewDetailedSalesReportByProduct(entry, exit, cat);
		} catch (GoAdminException e) {
			GoLog.logger.error(e.getMessage());
		}
		return result;

	}

}