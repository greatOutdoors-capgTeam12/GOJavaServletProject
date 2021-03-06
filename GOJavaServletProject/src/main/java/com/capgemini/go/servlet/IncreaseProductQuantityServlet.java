package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.service.ProductMasterService;
import com.capgemini.go.service.ProductMasterServiceImpl;

/**
 * Servlet implementation class IncreaseProductQuantityServlet
 */
public class IncreaseProductQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		         
		String prodid=request.getParameter("prodid");  
		String qty = request.getParameter("qty");
		
		
		try {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
			rd.include(request, response);
			
			     ProductDTO product = new ProductDTO(prodid, 0.0, "", "", "", "", Integer.parseInt(qty), 0, "");
				ProductMasterService prodmaster = new ProductMasterServiceImpl();
				boolean status = prodmaster.addExistingProduct(product);
				
			
			if(status == true) {
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Product with Product ID : " + prodid + " has been successfully increased by " + qty +" units </h2></p></div>");
			}
		}
		catch(Exception exp)
		{
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in Increasing Product Quantity >> " + exp.getMessage() + "</h2></p></div>");
		}  
		finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response);
			out.close();
		}
	}

}
