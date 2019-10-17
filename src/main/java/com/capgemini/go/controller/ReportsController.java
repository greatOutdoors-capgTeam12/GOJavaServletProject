package com.capgemini.go.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.utility.GoLog;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Path("/reports")
public class ReportsController {
	@POST
	@Consumes("application/json")
	@Path("/deliveryTimeReport/loadRetailers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfAvailabelRetailers (ReportFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;
		
		List<RetailerInventoryBean> list = null;
		try {
			GoAdminService goadmin = new GoAdminServiceImpl ();
			list = goadmin.getListOfRetailers();
			JsonArray dataList = new JsonArray();
			for (RetailerInventoryBean bean : list) {
				JsonObject dataObj = new JsonObject();
				dataObj.addProperty("retailerUserId", bean.getRetailerUserId());
				dataList.add(dataObj);
			}
			respBuilder = Response.status(Status.OK);
			respBuilder.header("Access-Control-Allow-Origin", "*");
			respBuilder.entity(dataList.toString());
			response = respBuilder.build();
		} catch (Exception e) {
			
		}
		return response;
	}
	
	@POST 
	@Consumes("application/json")
	@Path("/deliveryTimeReport")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeliveryTimeReportData (ReportFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;
		GoLog.logger.debug("Retailer ID: " + input.retailerId + " Report Type: " + input.reportType);
		String retailerId = input.retailerId;
		String reportType = input.reportType;
		int rt = Integer.parseInt(reportType);
		List<RetailerInventoryBean> list = null;
		try {
			GoAdminService.ReportType repType = GoAdminService.ReportType.MONTHLY_SHELF_TIME;
			if (rt == 1) {
				repType = GoAdminService.ReportType.MONTHLY_SHELF_TIME;
			} else if (rt == 2) {
				repType = GoAdminService.ReportType.QUARTERLY_SHELF_TIME;
			} else if (rt == 3) {
				repType = GoAdminService.ReportType.YEARLY_SHELF_TIME;
			}
			GoAdminService goadmin = new GoAdminServiceImpl ();
			list = goadmin.getDeliveryTimeReport(repType, retailerId, 0);
			JsonArray dataList = new JsonArray();
			for (RetailerInventoryBean bean : list) {
				JsonObject dataObj = new JsonObject();
				dataObj.addProperty("retailerUserId", bean.getRetailerUserId());
				dataObj.addProperty("productCategory", bean.getProductCategory());
				dataObj.addProperty("productUIN", bean.getProductUIN());
				dataObj.addProperty("productDeliveryTimePeriod", RetailerInventoryBean.periodToString(bean.getProductDeliveryTimePeriod()));
				dataObj.addProperty("productShelfTimePeriod", RetailerInventoryBean.periodToString(bean.getProductShelfTimePeriod()));
				dataList.add(dataObj);
			}
			respBuilder = Response.status(Status.OK);
			respBuilder.header("Access-Control-Allow-Origin", "*");
			respBuilder.entity(dataList.toString());
			response = respBuilder.build();
		} catch (Exception e) {
			
		}	
		return response;
	}
	
	@POST 
	@Consumes("application/json")
	@Path("/revenueReport")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRevenueReportData (ReportFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;
		GoLog.logger.debug("Retailer ID: " + input.retailerId + " Category Type: " + input.reportType +
				" Start Date: " + input.startDate + " End Date: " + input.endDate);
		String userId = input.retailerId;
		int categoryType = Integer.parseInt(input.reportType);
		String startDate = input.startDate;
		String endDate = input.endDate;
		List<ViewSalesReportByUserDTO> list = null;
		try {
			Date dentry = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date dexit = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			GoAdminService goadmin = new GoAdminServiceImpl ();
			list = goadmin.viewSalesReportByUserAndCategory(dentry, dexit, userId, categoryType);
			JsonArray dataList = new JsonArray();
			for (ViewSalesReportByUserDTO bean : list) {
				JsonObject dataObj = new JsonObject();
				dataObj.addProperty("userId", bean.getUserId());
				dataObj.addProperty("date", bean.getDate().toString());
				dataObj.addProperty("orderId", bean.getOrderId());
				dataObj.addProperty("productId", bean.getProductId());
				dataObj.addProperty("productCategory", Integer.toString(bean.getProductCategory()));
				dataObj.addProperty("productPrice", Double.toString(bean.getProductPrice()));
				dataList.add(dataObj);
			}
			respBuilder = Response.status(Status.OK);
			respBuilder.header("Access-Control-Allow-Origin", "*");
			respBuilder.entity(dataList.toString());
			response = respBuilder.build();
		} catch (Exception e) {
			
		}	
		return response;
	}
}
