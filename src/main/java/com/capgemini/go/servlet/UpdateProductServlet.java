package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.service.ProductMasterService;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.AuthenticationConstants;

/**
 * Servlet implementation class UpdateProductServlet
 */
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String prodid = request.getParameter("prodid");
		String prodname = request.getParameter("prodname");
		String dim = request.getParameter("dim");
		String spec = request.getParameter("spec");
		String price = request.getParameter("price");
		String prodcat = request.getParameter("prodcat");
		String col = request.getParameter("col");
		String brand = request.getParameter("brand");

		try {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
			rd.include(request, response);
			ProductDTO product = new ProductDTO(prodid, Double.parseDouble(price), col, dim, spec, brand, 0, Integer.parseInt(prodcat), prodname);
			ProductMasterService prodmaster = new ProductMasterServiceImpl();
			boolean status = prodmaster.updateProduct(product);

			if (status == true) {
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Product("+prodname +")  Product ID : "
						+ prodid + " has been successfully updated</h2></p></div>");
			}
		} catch (Exception exp) {
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in updating Product >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response);
			out.close();
		}
	}

}
