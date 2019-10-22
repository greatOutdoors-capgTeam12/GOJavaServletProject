package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dto.ProductFilterDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.service.UserService;
import com.capgemini.go.service.UserServiceImpl;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.AuthenticationConstants;

/**
 * Servlet implementation class FilterProductServlet
 */
public class FilterProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String proname = request.getParameter("pro-name");
		String brand = request.getParameter("brand");
		String qtylow = request.getParameter("qtylow");
		String qtyhigh = request.getParameter("qtyhigh");
		String category = request.getParameter("category");

		try {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/productheader.jsp"); 
			 rd.include(request, response);  
			 out.println("<div id=\"product\" class=\"container-fluid text-center \">\r\n" + 
						"  <div class=\"row justify-content-center\">");
			UserService user = new UserServiceImpl();
			ProductFilterDTO filterProduct = new ProductFilterDTO(proname, "", Double.parseDouble(qtylow), Double.parseDouble(qtyhigh), Integer.parseInt(category), brand);
			List<ProductBean> filteredProds = user.filterProduct(filterProduct);
			for(ProductBean prod : filteredProds)
			{
				
						out.println( "<div class=\"card col-lg-4 col-md-4 col-sm-4 \">\r\n" + 
						"      <img  src=\"resources/images/products/"+ prod.getProductId()+".jpg\" alt=\"Product_Name\" style=\"width:100%\" height=\"200px\">\r\n" + 
						"      <div class=\"overlay-prod\"><span class=\"fas fa-eye\"></span>\r\n" + 
						"	  <span class=\"fas fa-trash\"></span>\r\n" + 
						"       <span class=\"fas fa-arrow-up\" onclick=\"document.getElementById('incqty').style.display='block'\"></span>\r\n" + 
						"      <span class=\"fas fa-pen\"  onclick=\"document.getElementById('upprod').style.display='block'\"></span></div>\r\n" + 
						"      <h3>"+prod.getProductName()+"</h3>\r\n" + 
						"      <p class=\"price\">"+prod.getPrice()+"</p>\r\n" + 
						"      <p>Description : "+prod.getDimension()+"</p>\r\n" + 
						"      <p>Brand : "+prod.getManufacturer()+"</p>\r\n" + 
						"      <p>Colour <span class=\"fas fa-palette\" style=\"color: "+ prod.getColour() +"\"></span></p>\r\n" + 
						"    </div>\r\n" );
				}
			out.println("  </div>\r\n" +  
					"  </div>\r\n"+
						"</div>\r\n" );
			
		} catch (Exception exp) {
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in Filtering Products >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/productfooter.jsp"); 
			rd.include(request, response);
			rd=request.getRequestDispatcher("WEB-INF/pages/form.jsp"); 
			rd.include(request, response);
			out.close();
		}
	}

}
