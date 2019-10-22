package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.service.ProductMasterService;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;

/**
 * Servlet implementation class AddItemServlet
 */
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String prodid=request.getParameter("productid");  
		String qty=request.getParameter("quantity");
		String  retid = request.getParameter("retid");

		try {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
			rd.include(request, response); 
			CartDTO cartItem = new CartDTO(prodid, retid, Integer.parseInt(qty));
			RetailerService retailer = new RetailerServiceImpl();
			boolean status = retailer.addItemToCart(cartItem);

			if (status == true) {
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Product with  Product ID : "
						+ prodid + " has been successfully added to your cart</h2></p></div>");
			}
		} catch (Exception exp) {
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in adding item to cart >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response); 
			out.close();
		}
	}

}
