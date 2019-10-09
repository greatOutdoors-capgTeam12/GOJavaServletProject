package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.*;
import com.capgemini.go.service.*;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -966626700846407176L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");  
	PrintWriter out = response.getWriter();  
	         
	String userId=request.getParameter("ID");  

	try {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
		rd.include(request, response);
		
		UserDTO user= new UserDTO(null, userId,null,null, 0L, 2, false);
		UserService userLogOut= new UserServiceImpl();
		boolean status = userLogOut.userLogout(user);
		if(status == true) {
			out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>User with user ID : " + userId + " has been successfully logged out </h2></p></div>");
		}
	}
	catch(Exception exp)
	{
		out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in logging out >> " + exp.getMessage() + "</h2></p></div>");
	}  
	finally {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
		rd.include(request, response);
		out.close();
	}
}

}