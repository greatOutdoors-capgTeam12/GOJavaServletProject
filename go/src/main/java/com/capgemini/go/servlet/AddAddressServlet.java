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
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.service.ProductMasterService;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.utility.DbConnection;

/**
 * Servlet implementation class AddAddressServlet
 */
public class AddAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		String building_num = request.getParameter("building_num");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		String ret_id = request.getParameter("ret_ID");
		try {
		Connection connection = DbConnection.getInstance().getConnection();
		PreparedStatement statement3 = connection.prepareStatement(QuerryMapper.COUNT_USERID_IN_ADDRESSDB);
		statement3.setString(1, ret_id);
		ResultSet rs3 = statement3.executeQuery();
		rs3.next();
		String address_ID = (ret_id + "AD" + rs3.getInt(2));
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/header.html"); 
		rd.include(request, response); 
			AddressDTO address = new AddressDTO(address_ID, ret_id, building_num, city, state, country, zip, true);
			RetailerService retailer = new RetailerServiceImpl();
			boolean status = retailer.addAddress(address);

			if (status == true) {
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Address with  Address ID : "
						+ address_ID + " has been successfully added</h2></p></div>");
			}
		} catch (Exception exp) {
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in adding address >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			rd.include(request, response); 
			out.close();
		}
	}

}
