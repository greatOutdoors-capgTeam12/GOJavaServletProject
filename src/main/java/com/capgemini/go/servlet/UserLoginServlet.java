package com.capgemini.go.servlet;

 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.service.UserService;
import com.capgemini.go.service.UserServiceImpl;

 

/**
 * Servlet implementation class UserLoginServlet
 */
public class UserLoginServlet extends HttpServlet {

 

    /**
	 * 
	 */
	private static final long serialVersionUID = -5545983780358395653L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            response.setContentType("text/html");  
            PrintWriter out = response.getWriter();  
                     
            String userId=request.getParameter("user-login-id");  
            String password=request.getParameter("user-login-password"); 
            
            try {
            	RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
    			rd.include(request, response);
                UserDTO user = new UserDTO(null, userId, null, password, 0L , 2, false);
                UserService existingUser= new UserServiceImpl();
                boolean status = existingUser.userLogin(user);
                if(status == true) {
                    out.println("<div id =\"msg\" class=\"container-fluid \"><p><h2>User with User ID : " + userId + "has been successfully Logged-In</h2></p2></div>");
                }
            }
            catch(Exception exp)
            {
            out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in Log-in >> " + exp.getMessage() +"</h2></p></div>");
            }  
            finally {
            	RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
    			rd.include(request, response);
                out.close();
            }
    }

 

}