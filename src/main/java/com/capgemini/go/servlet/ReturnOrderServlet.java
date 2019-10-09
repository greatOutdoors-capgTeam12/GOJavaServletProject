package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
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
public class ReturnOrderServlet extends HttpServlet {
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
    public ReturnOrderServlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String userId=request.getParameter("ReturnOrderUserId");
		String orderId=request.getParameter("ReturnOrderOrderId");
		String reason=request.getParameter("ReturnOrderReason");
		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
			rd.include(request, response);
			if ((salesRepService.validateUser(orderId)).equals(userId)) {
				status = salesRepService.returnOrder(orderId, userId, reason);
				if (status == true) {
					result=exceptionProps.getProperty("return_order_processed");
				} else {
					result=exceptionProps.getProperty("failure_order");
				}
			} else {
				result=exceptionProps.getProperty("validate_user_error");
			}
		} catch (SalesRepresentativeException | IOException e) {
			result=exceptionProps.getProperty("validate_user_error");
		} finally {
			out.print("<div id = \"msg\" class=\"container-fluid \"><p><h2> " 
					+ result + " </h2></p></div>");
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response); 
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
