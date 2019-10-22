package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dao.QuerryMapper;
import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.utility.DbConnection;

/**
 * Servlet implementation class SuggestFavServlet
 */
public class SuggestFavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		String user_id = request.getParameter("User_ID");
		try {
	
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
		rd.include(request, response); 
			
			GoAdminService goadmin = new GoAdminServiceImpl();
			String prodId = goadmin.suggestFreqOrderProducts(user_id);

			
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Most Suggested Product is  : "
						+ prodId + "</h2></p></div>");
			
		} catch (Exception exp) {
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in suggesting product >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response); 
			out.close();
		}
	}

}
