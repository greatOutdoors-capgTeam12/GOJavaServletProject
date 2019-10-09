package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;

/**
 * Servlet implementation class DeleteAddressServlet
 */
public class DeleteAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		

		String ret_id = request.getParameter("ret_ID");
		String address_ID = request.getParameter("address_ID");
		try {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
			rd.include(request, response); 
			AddressDTO address = new AddressDTO(address_ID, ret_id, null, null, null, null, null, false);
			RetailerService retailer = new RetailerServiceImpl();
			boolean status = retailer.deleteAddress(address);

			if (status == true) {
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Address with  Address ID : "
						+ address_ID + " has been successfully deleted</h2></p></div>");
			}
		} catch (Exception exp) {
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in deleting address >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response); 
			out.close();
		}
	}

}
