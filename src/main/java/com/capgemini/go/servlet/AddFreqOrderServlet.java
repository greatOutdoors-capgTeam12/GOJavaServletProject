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
import com.capgemini.go.dto.FrequentOrderedDTO;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.utility.DbConnection;

/**
 * Servlet implementation class AddFreqOrderServlet
 */
public class AddFreqOrderServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2562130588458836089L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		

		String prodid = request.getParameter("prodid");
		String retid = request.getParameter("retid");
		
		try {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
			rd.include(request, response); 
			RetailerService retailer = new RetailerServiceImpl();
			FrequentOrderedDTO freqOrder = new FrequentOrderedDTO(retid, prodid, null);
			boolean status = retailer.addProductToFreqOrderDB(freqOrder);

			if (status == true) {
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Product with  Product ID : "
						+ prodid + " has been successfully added to your whishlist</h2></p></div>");
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in adding product to whishlist >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response); 
			out.close();
		}
	}

	private RetailerService RetailerServiceImpl() {
		// TODO Auto-generated method stub
		return null;
	}

}
