package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.service.SalesRepresentativeService;
import com.capgemini.go.service.SalesRepresentativeServiceImpl;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.PropertiesLoader;

/**
 * Servlet implementation class ReturnOrderServlet
 */
public class ReturnProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Properties exceptionProps = new Properties();
	private static Properties goProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";
	private static final String GO_PROPERTIES_FILE = "goUtility.properties";
	SalesRepresentativeService salesRepService = new SalesRepresentativeServiceImpl();
	boolean status;
	String result;
    /**
     * Default constructor. 
     */
    public ReturnProductServlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String userId=request.getParameter("UserId");
		String orderId=request.getParameter("OrderId");
		String productId=request.getParameter("ProductId");
		int qty=Integer.parseInt(request.getParameter("Qty"));
		String reason=request.getParameter("Reason");
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			if ((salesRepService.validateUser(orderId)).equals(userId)) {
				status = salesRepService.returnProduct(orderId, userId, productId, qty, reason);
				if (status == true) {
					GoLog.logger.info(exceptionProps.getProperty("return_order_processed"));
				} else {
					GoLog.logger.error(exceptionProps.getProperty("failure_order"));
				}
			} else {
				GoLog.logger.error(exceptionProps.getProperty("validate_user_error"));
				GoLog.logger.error(exceptionProps.getProperty("failure_order"));
			}
		} catch (SalesRepresentativeException | IOException e) {
			GoLog.logger.error(exceptionProps.getProperty("failure_order"));
		}
		PrintWriter out=response.getWriter();
		out.print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
