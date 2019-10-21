package com.capgemini.go.controller;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.dto.ViewDetailedSalesReportByProductDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.utility.GoLog;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Path("/reports")
public class ReportsController {
	public static Calendar getDateFromString (String date) {
		// format of string: yyyy-mm-dd
		Calendar result = Calendar.getInstance();
		result.set(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)));
		return result;
	}
	
	/*
	 * @OPTIONS public Response filter () { Response response = null;
	 * ResponseBuilder respBuilder = null; respBuilder = Response.status(Status.OK);
	 * respBuilder.header("Access-Control-Allow-Origin", "*"); return response; }
	 */

	@POST
	@Consumes("application/json")
	@Path("/deliveryTimeReport/loadRetailers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfAvailableRetailers(ReportFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;

		List<RetailerInventoryBean> list = null;
		try {
			GoAdminService goadmin = new GoAdminServiceImpl();
			list = goadmin.getListOfRetailers();
			JsonArray dataList = new JsonArray();
			for (RetailerInventoryBean bean : list) {
				JsonObject dataObj = new JsonObject();
				dataObj.addProperty("retailerUserId", bean.getRetailerUserId());
				dataList.add(dataObj);
			}
			respBuilder = Response.status(Status.OK);
			respBuilder.header("Access-Control-Allow-Origin", "*");
			respBuilder.header("Access-Control-Allow-Methods", "GET, POST");
			respBuilder.header("Access-Control-Allow-Headers", "X-Requested-With,content-type");
			respBuilder.header("Access-Control-Allow-Credentials", true);
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
	public Response deliveryTimeReportJaxRsService(ReportFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;
		GoLog.logger.debug("Retailer ID: " + input.retailerId + " Report Type: " + input.reportType);
		String retailerId = input.retailerId;
		String reportType = input.reportType;
		int rt = Integer.parseInt(reportType);
		List<RetailerInventoryBean> list = null;
		try {
			GoAdminService.ReportType repType = GoAdminService.ReportType.OUTLIER_ITEM_DELIVERY_TIME;
			if (rt == 1) {
				repType = GoAdminService.ReportType.OUTLIER_ITEM_DELIVERY_TIME;
			} else if (rt == 2) {
				repType = GoAdminService.ReportType.OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME;
			} else if (rt == 3) {
				repType = GoAdminService.ReportType.OUTLIER_ITEM_IN_OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME;
			}
			GoAdminService goadmin = new GoAdminServiceImpl();
			list = goadmin.getDeliveryTimeReport(repType, retailerId, 0);
			JsonArray dataList = new JsonArray();
			for (RetailerInventoryBean bean : list) {
				JsonObject dataObj = new JsonObject();
				dataObj.addProperty("retailerUserId", bean.getRetailerUserId());
				dataObj.addProperty("productCategory", bean.getProductCategory());
				dataObj.addProperty("productUIN", bean.getProductUIN());
				dataObj.addProperty("productDeliveryTimePeriod",
						RetailerInventoryBean.periodToString(bean.getProductDeliveryTimePeriod()));
				dataObj.addProperty("productShelfTimePeriod",
						RetailerInventoryBean.periodToString(bean.getProductShelfTimePeriod()));
				dataList.add(dataObj);
			}
			respBuilder = Response.status(Status.OK);
			respBuilder.header("Access-Control-Allow-Origin", "*");
			respBuilder.header("Access-Control-Allow-Methods", "GET, POST");
			respBuilder.header("Access-Control-Allow-Headers", "X-Requested-With,content-type");
			respBuilder.header("Access-Control-Allow-Credentials", true);
			respBuilder.entity(dataList.toString());
			response = respBuilder.build();
		} catch (Exception e) {

		}
		return response;
	}
	
	@POST
	@Consumes("application/json")
	@Path("/shelfTimeReport")
	@Produces(MediaType.APPLICATION_JSON)
	public Response shelfTimeReportJaxRsService(ReportFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;
		GoLog.logger.debug("Retailer ID: " + input.retailerId + " Report Type: " 
				+ input.reportType + " Start-Date: " + input.startDate + " End-Date:" + input.endDate);
		String retailerId = input.retailerId;
		String reportType = input.reportType;
		Calendar dateSelection = getDateFromString (input.startDate);
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
			GoAdminService goadmin = new GoAdminServiceImpl();
			list = goadmin.getShelfTimeReport(repType, retailerId, dateSelection);
			JsonArray dataList = new JsonArray();
			for (RetailerInventoryBean bean : list) {
				JsonObject dataObj = new JsonObject();
				dataObj.addProperty("retailerUserId", bean.getRetailerUserId());
				dataObj.addProperty("productCategory", bean.getProductCategory());
				dataObj.addProperty("productUIN", bean.getProductUIN());
				dataObj.addProperty("productDeliveryTimePeriod",
						RetailerInventoryBean.periodToString(bean.getProductDeliveryTimePeriod()));
				dataObj.addProperty("productShelfTimePeriod",
						RetailerInventoryBean.periodToString(bean.getProductShelfTimePeriod()));
				dataList.add(dataObj);
			}
			respBuilder = Response.status(Status.OK);
			respBuilder.header("Access-Control-Allow-Origin", "*");
			respBuilder.header("Access-Control-Allow-Methods", "GET, POST");
			respBuilder.header("Access-Control-Allow-Headers", "X-Requested-With,content-type");
			respBuilder.header("Access-Control-Allow-Credentials", true);
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
	public Response getRevenueReportData(ReportFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;
		GoLog.logger.debug("Retailer ID: " + input.retailerId + " Category Type: " + input.reportType + " Start Date: "
				+ input.startDate + " End Date: " + input.endDate);
		String userId = input.retailerId;
		int categoryType = Integer.parseInt(input.reportType);
		String startDate = input.startDate;
		String endDate = input.endDate;
		List<ViewSalesReportByUserDTO> list = null;
		try {
			Date dentry = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date dexit = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			GoAdminService goadmin = new GoAdminServiceImpl();
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
			respBuilder.header("Access-Control-Allow-Methods", "GET, POST");
			respBuilder.header("Access-Control-Allow-Headers", "X-Requested-With,content-type");
			respBuilder.header("Access-Control-Allow-Credentials", true);
			respBuilder.entity(dataList.toString());
			response = respBuilder.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Consumes("application/json")
	@Path("/growthReport")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getgrowthReportData(ReportFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;
		GoLog.logger.debug(" ReportType Type: " + input.reportType + " Start Date: " + input.startDate + " End Date: "
				+ input.endDate);
		int categoryType = Integer.parseInt(input.reportType);
		String startDate = input.startDate;
		String endDate = input.endDate;
		List<ViewDetailedSalesReportByProductDTO> list = null;
		try {
			Date dentry = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date dexit = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			GoAdminService goadmin = new GoAdminServiceImpl();
			list = goadmin.viewDetailedSalesReportByProduct(dentry, dexit, categoryType);
			JsonArray dataList = new JsonArray();
			for (ViewDetailedSalesReportByProductDTO bean : list) {
				JsonObject dataObj = new JsonObject();

				if (categoryType == 1) {
					dataObj.addProperty("period", Month.of(bean.getPeriod() + 1).name());
				} else if (categoryType == 2) {
					dataObj.addProperty("period", "Q" + Integer.toString((bean.getPeriod()) + 1));
				} else {
					dataObj.addProperty("period", "YEAR:" + Integer.toString(bean.getPeriod()));
				}
				dataObj.addProperty("revenue", Double.toString(bean.getRevenue()));
				dataObj.addProperty("amountChange", Double.toString(bean.getAmountChange()));
				dataObj.addProperty("percentageGrowth", Double.toString(bean.getPercentageGrowth()));
				dataObj.addProperty("colorCode", bean.getCode());
				dataList.add(dataObj);
			}
			respBuilder = Response.status(Status.OK);
			respBuilder.header("Access-Control-Allow-Origin", "*");
			respBuilder.header("Access-Control-Allow-Methods", "GET, POST");
			respBuilder.header("Access-Control-Allow-Headers", "X-Requested-With,content-type");
			respBuilder.header("Access-Control-Allow-Credentials", true);
			respBuilder.entity(dataList.toString());
			response = respBuilder.build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

//	@POST
//	@Consumes("application/json")
//	@Path("/salesReport")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getsalesReportData(ReportFormBean input) {
//		Response response = null;
//		ResponseBuilder respBuilder = null;
//		GoLog.logger.debug(" ReportType Type: " + input.reportType + " Start Date: " + input.startDate + " End Date: "
//				+ input.endDate);
//		int categoryType = Integer.parseInt(input.reportType);
//		String startDate = input.startDate;
//		String endDate = input.endDate;
//		List<ViewDetailedSalesReportByProductDTO> list = null;
//		try {
//			Date dentry = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
//			Date dexit = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
//			GoAdminService goadmin = new GoAdminServiceImpl();
//			list = goadmin.viewDetailedSalesReportByProduct(dentry, dexit, categoryType);
//			JsonArray dataList = new JsonArray();
//			for (ViewDetailedSalesReportByProductDTO bean : list) {
//				JsonObject dataObj = new JsonObject();
//
//				if (categoryType == 1) {
//					dataObj.addProperty("period", Month.of(bean.getPeriod() + 1).name());
//				} else if (categoryType == 2) {
//					dataObj.addProperty("period", "Q" + Integer.toString((bean.getPeriod()) + 1));
//				} else {
//					dataObj.addProperty("period", "YEAR:" + Integer.toString(bean.getPeriod()));
//				}
//				dataObj.addProperty("revenue", Double.toString(bean.getRevenue()));
//				dataObj.addProperty("amountChange", Double.toString(bean.getAmountChange()));
//				dataObj.addProperty("percentageGrowth", Double.toString(bean.getPercentageGrowth()));
//				dataObj.addProperty("colorCode", bean.getCode());
//				dataList.add(dataObj);
//			}
//			respBuilder = Response.status(Status.OK);
//			respBuilder.header("Access-Control-Allow-Origin", "*");
//			respBuilder.header("Access-Control-Allow-Methods", "GET, POST");
//			respBuilder.header("Access-Control-Allow-Headers", "X-Requested-With,content-type");
//			respBuilder.header("Access-Control-Allow-Credentials", true);
//			respBuilder.entity(dataList.toString());
//			response = respBuilder.build();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return response;
//	}

	@POST
	@Consumes("application/json")
	@Path("/salesReport")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSalesData(SalesRepFormBean input) {
		Response response = null;
		ResponseBuilder respBuilder = null;
//	        GoLog.logger.debug(" ReportType Type: " + input.reportType +
//	                " Start Date: " + input.startDate + " End Date: " + input.endDate);
		String userId = input.userId;
		Double bonus = input.setBonus;
		Double target = input.setTarget;
		System.out.println(userId+"=="+bonus+"=="+target);
		List<SalesRepDTO> list = null;
		try {
			GoAdminService goadmin = new GoAdminServiceImpl();
			System.out.println(goadmin.viewSalesRepData(userId).getUserId());
			
			if (userId.equalsIgnoreCase("ALL"))
				list = goadmin.viewAllSalesRepData();
			else {
				list = new ArrayList<SalesRepDTO>();
				list.add(goadmin.viewSalesRepData(userId));
				if(bonus>0)
					goadmin.setBonus(goadmin.viewSalesRepData(userId), bonus);
				if(target>0)
					goadmin.setTarget(goadmin.viewSalesRepData(userId), target);
			}

			JsonArray dataList = new JsonArray();
			for (SalesRepDTO bean : list) {
				
				JsonObject dataObj = new JsonObject();
				dataObj.addProperty("userID", bean.getUserId());
				dataObj.addProperty("targetSales", Double.toString(bean.getTarget()));
				dataObj.addProperty("target", Integer.toString(bean.getTargetStatus()));
				dataObj.addProperty("currentSales", Double.toString(bean.getCurrentTargetStatus()));
				dataObj.addProperty("bonus", Double.toString(bean.getBonus()));
				System.out.println(dataObj.toString());

				dataList.add(dataObj);
			}
			respBuilder = Response.status(Status.OK);
			respBuilder.header("Access-Control-Allow-Origin", "*");
			respBuilder.header("Access-Control-Allow-Methods", "GET, POST");
			respBuilder.header("Access-Control-Allow-Headers", "X-Requested-With,content-type");
			respBuilder.header("Access-Control-Allow-Credentials", true);
			respBuilder.entity(dataList.toString());
			response = respBuilder.build();
		} catch (Exception e) {

		}
		return response;
	}
    

    @POST
    @Consumes("application/json")
    @Path("/retailerReport")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRetailerData(RetailerRepFormBean input) {
    	System.out.println("in getRetialer() Data");
        Response response = null;
        ResponseBuilder respBuilder = null;
//            GoLog.logger.debug(" ReportType Type: " + input.reportType +
//                    " Start Date: " + input.startDate + " End Date: " + input.endDate);
        String userId = input.userId;
        Double discount = input.setDiscount;
        System.out.println(userId+"=="+discount);
        List<RetailerDTO> list = null;
        try {
            GoAdminService goadmin = new GoAdminServiceImpl();
            System.out.println(goadmin.viewRetailerData(userId).getUserId());
            if (userId.equalsIgnoreCase("ALL"))
                list = goadmin.viewAllRetailerData();
            else {
                list = new ArrayList<RetailerDTO>();
                list.add(goadmin.viewRetailerData(userId));
                if(discount>0)
                    goadmin.setDiscount(goadmin.viewRetailerData(userId), discount);                
            }
            JsonArray dataList = new JsonArray();
            for (RetailerDTO bean : list) {
                JsonObject dataObj = new JsonObject();
                dataObj.addProperty("userID", bean.getUserId());
                dataObj.addProperty("discount", Double.toString(bean.getDiscount()));
                System.out.println(dataObj.toString());
                dataList.add(dataObj);
            }
            respBuilder = Response.status(Status.OK);
            respBuilder.header("Access-Control-Allow-Origin", "*");
            respBuilder.header("Access-Control-Allow-Methods", "GET, POST");
            respBuilder.header("Access-Control-Allow-Headers", "X-Requested-With,content-type");
            respBuilder.header("Access-Control-Allow-Credentials", true);
            respBuilder.entity(dataList.toString());
            response = respBuilder.build();
        } catch (Exception e) {


        }
        return response;
    }
}
