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
				out.print("<!DOCTYPE html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"    <head>\r\n" + 
						"        <!-- META DATA -->\r\n" + 
						"        <meta charset=\"UTF-8\">\r\n" + 
						"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
						"        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
						"        \r\n" + 
						"        <!-- TITLE -->\r\n" + 
						"        <title>Document</title>\r\n" + 
						"        \r\n" + 
						"        <!-- Linking bootstrap4 css file from cloud -->\r\n" + 
						"        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n" + 
						"        \r\n" + 
						"        <!-- Linking bootstrap4 js library files from cloud -->\r\n" + 
						"        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
						"        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n" + 
						"        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n" + 
						"    </head>\r\n" + 
						"    <body>\r\n" + 
						"        <div class=\"jumbotron\">\r\n" + 
						"            <div class=\"table-responsive\">\r\n" + 
						"                <table class=\"table\">\r\n" + 
						"                    <thead class=\"thead-dark\">\r\n" + 
						"                        <tr>\r\n" + 
						"                            <th>Retailer Id</th>\r\n" + 
						"                            <th>Product Category</th>\r\n" + 
						"                            <th>Product UIN</th>\r\n" + 
						"                            <th>Product Shelf Time (days)</th>\r\n" + 
						"                        </tr>\r\n" + 
						"                    </thead>\r\n" + 
						"                    <tbody>"
						);
				for (RetailerInventoryBean r : result) {
					out.print("<tr>");
					out.print("<td>" + r.getRetailerUserId() + "</td>");
					out.print("<td>" + r.getProductCategory() + "</td>");
					out.print("<td>" + r.getProductUIN() + "</td>");
					out.print("<td>" + r.getProductShelfTimePeriod().getDays() + "</td>");
					out.print("</tr>");
				}
				out.print("</tbody>\r\n" + 
						"                </table>\r\n" + 
						"            </div>\r\n" + 
						"        </div>\r\n" + 
						"    </body>\r\n" + 
						"</html>"
						);
			}
		} catch (ConnectException exception) {
			out.println("Error in Retreiving Report >> " + exception.getMessage());
		} finally {
			out.close();
		}
	}
}
