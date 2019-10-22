package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dao.GoAdminDao;
import com.capgemini.go.dao.GoAdminDaoImpl;
import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.AuthenticationConstants;
import com.google.protobuf.TextFormat.ParseException;

/**
 * Servlet implementation class SalesRepServlet
 */
@WebServlet("/AllCategoriesServlet")
public class AllCategoriesServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2882318023577648265L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {

			GoAdminDao go = new GoAdminDaoImpl();

			String userId = request.getParameter("UserId");
			Date dentry = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dEntry"));
			Date dexit = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dExit"));

			int n, i = 0;

			out.println("<html><head><style>\r\n" + "table, th, td {\r\n" + "  border: 1px solid black;\r\n"
					+ "  border-collapse: collapse;\r\n" + "}\r\n" + "th, td {\r\n" + "  padding: 15px;\r\n" + "}\r\n"
					+ "</style>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Cancel Order</title>\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" + "<script>\r\n"
					+ "    src = \"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\">\r\n"
					+ "</script>\r\n" + "<script src=\"js/bootstrap.min.js\"></script>\r\n"
					+ "<link rel=\"stylesheet\"\r\n"
					+ "    href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n"
					+ "<script\r\n"
					+ "    src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n"
					+ "<script\r\n"
					+ "    src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n"
					+ "<script\r\n"
					+ "    src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n"
					+ "</head>");
			List<ViewSalesReportByUserDTO> reports = null;
			if (userId.equalsIgnoreCase("ALL")) {

				reports = go.viewSalesReportALLUserAndCategory(dentry, dexit);

				n = reports.size();
				out.println(
						"<body><table><tr><th>User ID</th><th>Date</th><th>Order ID</th><th>Product Category</th><th>Product ID</th><th>Product Price</th></tr>");

				while (i < n) {
					out.println("<tr><td>" + reports.get(i).getUserId() + "</td><td>" + reports.get(i).getDate()
							+ "</td><td>" + reports.get(i).getOrderId() + "</td><td>"
							+ reports.get(i).getProductCategory() + "</td><td>" + reports.get(i).getProductId()
							+ "</td><td>" + reports.get(i).getProductPrice() + "</td></tr>");

					i++;
				}
				out.println("<button type=\"button\" class=\"btn btn-secondary custom\"\r\n"
						+ "						onclick=\"location.href='RevenueReports.html';\"\r\n"
						+ "						style=\"left-align: 50px\">GO BACK TO CATEGORY SELECTION</button>");

			} else {

				ViewSalesReportByUserDTO report = go.viewSalesReportByUser(dentry, dexit, userId).get(0);

				out.println(
						"<body><table><tr><th>User ID</th><th>Date</th><th>Order ID</th><th>Product Category</th><th>Product ID</th><th>Product Price</th></tr>");

				out.println("<tr><td>" + report.getUserId() + "</td><td>" + report.getDate() + "</td><td>"
						+ report.getOrderId() + "</td><td>" + report.getProductCategory() + "</td><td>"
						+ report.getProductId() + "</td><td>" + report.getProductPrice() + "</td></tr>");
				out.println("<button type=\"button\" class=\"btn btn-secondary custom\"\r\n"
						+ "						onclick=\"location.href='RevenueReports.html';\"\r\n"
						+ "						style=\"left-align: 50px\">GO BACK TO CATEGORY SELECTION</button>");

			}
		}

		catch (GoAdminException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
