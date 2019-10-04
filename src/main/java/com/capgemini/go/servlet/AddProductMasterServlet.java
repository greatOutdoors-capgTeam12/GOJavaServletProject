package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.*;
import com.capgemini.go.service.*;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.AuthenticationConstants;


/**
 * Servlet implementation class AddProductMasterServlet
 */
public class AddProductMasterServlet extends HttpServlet {
	

	//private static final long serialVersionUID = -42485206283003992L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		         
		String userId=request.getParameter("userid");  
		String username=request.getParameter("name");  
		String contact=request.getParameter("contact");  
		String email=request.getParameter("email"); 
		String password=request.getParameter("psw"); 
		
		try {
			password =  Authentication.encrypt(password,AuthenticationConstants.secretKey);
			UserDTO productMaster = new UserDTO(username, userId, email, password, Long.parseLong(contact), 2, false);
			GoAdminService goAdmin = new GoAdminServiceImpl();
			boolean status = goAdmin.addProductMaster(productMaster);
			if(status == true) {
				out.println("Product Master with Product ID : " + userId + "has been successfully registered");
			}
		}
		catch(Exception exp)
		{
			out.println("Error in registering Product Master >> " + exp.getMessage());
		}  
		finally {
			out.close();
		}
	}

}
