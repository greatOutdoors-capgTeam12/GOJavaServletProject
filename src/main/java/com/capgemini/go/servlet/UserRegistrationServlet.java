package com.capgemini.go.servlet;

 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.service.UserService;
import com.capgemini.go.service.UserServiceImpl;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.AuthenticationConstants;

 

/**
 * Servlet implementation class UserRegistrationServlet
 */

 

public class UserRegistrationServlet extends HttpServlet {
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String username=request.getParameter("Name");  
        String userId=request.getParameter("ID");   
        String email=request.getParameter("email"); 
        String password=request.getParameter("psw"); 
        String contact=request.getParameter("MNum");
        String category=request.getParameter("category"); 
        
        try {
        	RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
			rd.include(request, response);
            password =  Authentication.encrypt(password,AuthenticationConstants.secretKey);
            UserDTO user = new UserDTO(username, userId, email, password, Long.parseLong(contact), Integer.parseInt(category), false);
            UserService newUser = new UserServiceImpl();
            boolean status = newUser.userRegistration(user);
            if(status == true) {
                out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>User with User ID : " + userId + "has been successfully registered</h2></p></div>");
            }
        }
        catch(Exception exp)
        {
            out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in registering New User >> " + exp.getMessage() + "</h2></p></div>");
        }  
        finally {
        	RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response);
			out.close();
        }
    }

 

       
   
    

 

}