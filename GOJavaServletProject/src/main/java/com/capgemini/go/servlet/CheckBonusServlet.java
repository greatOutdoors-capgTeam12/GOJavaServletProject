package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.service.SalesRepresentativeService;
import com.capgemini.go.service.SalesRepresentativeServiceImpl;

@WebServlet("/CheckBonusServlet")
public class CheckBonusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SalesRepresentativeService salesRepService = new SalesRepresentativeServiceImpl();
	String message = null;

	public CheckBonusServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("UserIdCheckBonus");

		try {
			message = salesRepService.checkBonus(userId);
			out.print("<!Doctype html>"
					+ "<head>"
					+ "<title>"
					+ "</title>"
					+ "</head>"
					+ "<body>"
					+ "<h4>Hello !</h4>"
					+ "<span id='message'>" + message + "</span>"
		 			+ "<br>Go to previous page or close the window"
					+ "</body>"
      				+ "</html>"	);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<!Doctype html>"
					+ "<head>"
					+ "<title>"
					+ "</title>"
					+ "</head>"
					+ "<body>"
					+ "<h3> Hello !</h3>"
					+ "<span id='errMessage'>" + e.getMessage() + "</span>"
					+ "<br>Go to previous page or close the window"
					+ "</body>"
					+ "</html>"	);
			// GoLog.logger.error(exceptionProps.getProperty("order_cant_be_cancelled"));
		} finally {
			out.close();
		}
	}
}
