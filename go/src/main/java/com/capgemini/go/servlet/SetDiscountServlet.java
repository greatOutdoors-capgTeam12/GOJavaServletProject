package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.dto.ViewDetailedSalesReportByProductDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.AuthenticationConstants;
import com.google.protobuf.TextFormat.ParseException;

/**
 * Servlet implementation class SalesRepServlet
 */
@WebServlet("/SetDiscountServlet")
public class SetDiscountServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6636622832993422902L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {

			out.println("<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Cancel Order</title>\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" + "<script>\r\n"
					+ "	src = \"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\">\r\n"
					+ "</script>\r\n" + "<script src=\"js/bootstrap.min.js\"></script>\r\n"
					+ "<link rel=\"stylesheet\"\r\n"
					+ "	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n"
					+ "<script\r\n"
					+ "	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n"
					+ "<script\r\n"
					+ "	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n"
					+ "<script\r\n"
					+ "	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n"
					+ "</head>");

			GoAdminService go = new GoAdminServiceImpl();
			String userId = request.getParameter("userId");
			RetailerDTO ret = new RetailerDTO(userId);
			double discount = Double.parseDouble(request.getParameter("discount"));
			go.setDiscount(ret, discount);
			out.println("<br>Discount of " + userId + " is updated. </br>");
			out.println("Discount: " + discount);
			out.println("<button type=\"button\" class=\"btn btn-secondary custom\"\r\n"
					+ "						onclick=\"location.href='ReportType.html';\"\r\n"
					+ "						style=\"left-align: 50px\">GO BACK TO REPORT SELECTION</button>");

			/*
			 * if(dentry.getYear()!=dexit.getYear()) throw new
			 * Exception("ENTER VALID ENTRY AND EXIT DATES")
			 */

			/*
			 * int n, i = 0;
			 * 
			 * out.println("<html><head><style>\r\n" + "table, th, td {\r\n" +
			 * "  border: 1px solid black;\r\n" + "  border-collapse: collapse;\r\n" +
			 * "}\r\n" + "th, td {\r\n" + "  padding: 15px;\r\n" + "}\r\n" + "</style>\r\n"
			 * + "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Cancel Order</title>\r\n" +
			 * "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
			 * + "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" +
			 * "<script>\r\n" +
			 * "    src = \"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\">\r\n"
			 * + "</script>\r\n" + "<script src=\"js/bootstrap.min.js\"></script>\r\n" +
			 * "<link rel=\"stylesheet\"\r\n" +
			 * "    href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n"
			 * + "<script\r\n" +
			 * "    src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n"
			 * + "<script\r\n" +
			 * "    src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n"
			 * + "<script\r\n" +
			 * "    src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n"
			 * + "</head>"); out.println(
			 * "<body><table><tr><td>Month</td><td>Revenue</td><td>Amount Change</td><td>Percentage Growth</td><td>Color Code</td></tr>"
			 * ); List<ViewDetailedSalesReportByProductDTO> reports = null;
			 * 
			 * reports = go.viewDetailedSalesReportByProduct(dentry, dexit, 1);
			 * 
			 * n = reports.size();
			 * 
			 * while (i < n) { out.println("<tr><td>" + Month.of(reports.get(i).getMonth() +
			 * 1).name() + "</td><td>" + reports.get(i).getRevenue() + "</td><td>" +
			 * reports.get(i).getAmountChange() + "</td><td>" +
			 * reports.get(i).percentageGrowth() + "</td><td>" + reports.get(i).getCode() +
			 * "</td></tr>");
			 * 
			 * i++; }
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
