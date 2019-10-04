package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;

/**
 * Servlet implementation class ShelfTimeReportServlet
 */
public class ShelfTimeReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShelfTimeReportServlet() {
		super();
	}

	@SuppressWarnings("deprecation")
	public static String printDate(Date date) {
		return "Day: " + date.getDate() + " Month: " + date.getMonth() + " Year: " + date.getYear();
	}

	public static Calendar getDateFromString (String date) {
		// format of string: yyyy-mm-dd
		Calendar result = Calendar.getInstance();
		result.set(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7))+1, Integer.valueOf(date.substring(8, 10)));
		return result;
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// setting content type
		response.setContentType("text/html");
		// initializing output printer
		PrintWriter out = response.getWriter();

		// extracting data entered in form
		String retailerId = request.getParameter("retailer-id");
		int rt = Integer.valueOf(request.getParameter("report-type"));		
		GoAdminService.ReportType reportType = null;
		if (rt == 1) {
			reportType = GoAdminService.ReportType.MONTHLY_SHELF_TIME;
		} else if (rt == 2) {
			reportType = GoAdminService.ReportType.QUARTERLY_SHELF_TIME;
		} else if (rt == 3) {
			reportType = GoAdminService.ReportType.YEARLY_SHELF_TIME;
		}
		Calendar dateSelection = getDateFromString (request.getParameter("dateSelection"));
		try {
			// calling admin service
			GoAdminService goAdmin = new GoAdminServiceImpl();

			System.out.println(retailerId + " " + rt + " " + dateSelection.get(Calendar.YEAR) + " "
					+ dateSelection.get(Calendar.MONTH) + " " + dateSelection.get(Calendar.DATE));

			List<RetailerInventoryBean> result = goAdmin.getShelfTimeReport(reportType, retailerId, dateSelection);
			for (RetailerInventoryBean r : result) {
				out.println(r.toString());
			}
		} catch (ConnectException e) {
			out.println("Error in Retreiving Report >> " + e.getMessage());
		} catch (Exception exp) {
			out.println("Error in Retreiving Report >> " + exp.getMessage());
		} finally {
			out.close();
		}
	}
}
