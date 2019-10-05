package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.util.Calendar;
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
	
	public static Calendar getDateFromString (String date) {
		// format of string: yyyy-mm-dd
		Calendar result = Calendar.getInstance();
		result.set(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)));
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
		Calendar dateSelection = getDateFromString (request.getParameter("date-selection"));
		try {
			// calling admin service
			GoAdminService goAdmin = new GoAdminServiceImpl();
			List<RetailerInventoryBean> result = goAdmin.getShelfTimeReport(reportType, retailerId, dateSelection);
			if (result.size() == 0 || result == null) {
				out.println("No Report To Print");
			} else {
				for (RetailerInventoryBean r : result) {
					out.println("<br>");
					out.println(r.toString());
				}
			}
		} catch (ConnectException exception) {
			out.println("Error in Retreiving Report >> " + exception.getMessage());
		} finally {
			out.close();
		}
	}
}
