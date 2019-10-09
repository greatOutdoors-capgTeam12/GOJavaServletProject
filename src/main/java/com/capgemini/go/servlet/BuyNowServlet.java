package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.utility.GenerateOrderID;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

/**
 * Servlet implementation class BuyNowServlet
 */
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		String  retid = request.getParameter("retid");
		String  addressId = request.getParameter("addid");

		try {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
			rd.include(request, response); 
			long millis = System.currentTimeMillis();
			java.sql.Date orderInitiationTime = new java.sql.Date(millis);
			String orderId = "ORD" + GenerateOrderID.generate( );
			OrderDTO order = new OrderDTO(orderId, retid, addressId, false, orderInitiationTime, null);
			RetailerService retailer = new RetailerServiceImpl();
			boolean status = retailer.placeOrder(order);

			if (status == true) {
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Order with  Order ID : "
						+ orderId+ " has been successfully Initiated</h2></p></div>");
			}
		} catch (Exception exp) {
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in checkout >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response); 
			out.close();
		}
	}

}
