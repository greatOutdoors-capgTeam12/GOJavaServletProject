package com.capgemini.go.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dto.ProductFilterDTO;
import com.capgemini.go.service.UserService;
import com.capgemini.go.service.UserServiceImpl;

/**
 * Servlet implementation class GolfProductServlet
 */
public class GolfProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService user = new UserServiceImpl();
		try {
		ProductFilterDTO filterProduct= new ProductFilterDTO("","", 0, 1000000, 2, "");
		List<ProductBean> campProds = user.filterProduct(filterProduct);
		 request.setAttribute("productList", campProds);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

}
