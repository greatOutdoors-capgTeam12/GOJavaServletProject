package com.capgemini.go.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ProductMasterException;
import com.capgemini.go.service.ProductMasterService;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Servlet implementation class UpdateProductServlet
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


@SuppressWarnings("unchecked")
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {  }
		Map<String,String> myMap = new HashMap<String, String>();

		ObjectMapper objectMapper = new ObjectMapper();
		
		myMap = objectMapper.readValue(jb.toString(), HashMap.class);
	    String productId = myMap.get("prodid");
	    String productName = myMap.get("prodName");
	    double price = Double.parseDouble(myMap.get("prodPrice"));
	    String colour = myMap.get("prodColor");
		String dimension = myMap.get("prodDim");
		String specification = myMap.get("prodSpec");
		String manufacturer = myMap.get("prodBrand");
		/*********************
		 * Quantity Can only be 1,2,3,4 or 5 1 = CAMPING 2 = GOLF 3 = MOUNTAINEERING 4 =
		 * OUTDOOR 5 = PERSONAL
		 *************************/
		int quantity = Integer.parseInt(myMap.get("prodQty"));
		int productCategory = Integer.parseInt(myMap.get("category"));
		ProductDTO newProd  = new ProductDTO(productId, price, colour, dimension, specification, manufacturer, quantity, productCategory, productName);
		System.out.println(newProd);
		
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Allow-Headers" ,"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean result=false;
		PrintWriter out =response.getWriter();
	
			
			ProductMasterService prodMast = new ProductMasterServiceImpl();
			try {
				result = prodMast.addProduct(newProd);
				if(result)
				{
					((ObjectNode) dataResponse).put("Success :","New Product Added Successfully");
				}
				
			} catch (ProductMasterException e) {
				
				((ObjectNode) dataResponse).put("Error :",e.getMessage());
				
			}
			finally
			{
				out.print(dataResponse);
				out.close();
			}
			
			
		

}
}