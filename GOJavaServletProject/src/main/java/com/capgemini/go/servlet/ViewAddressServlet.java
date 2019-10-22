package com.capgemini.go.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.ProductMasterException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.service.ProductMasterService;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.service.UserService;
import com.capgemini.go.service.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UpdateProductServlet
 */
public class ViewAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


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
	    String userId = myMap.get("userId");
	    
	  
		

		
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Allow-Headers" ,"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean result=false;
		PrintWriter out =response.getWriter();
	
			
			RetailerService retailer = new RetailerServiceImpl();
			
				try {
					String addressFetched = null;
					List<AddressDTO> allAddress = retailer.viewAllAddress(userId);
					JsonArray addressList = new JsonArray();
					for (AddressDTO add : allAddress) {
						JsonObject addressObj = new JsonObject();
						addressObj.addProperty("addressId", add.getAddressId());
						addressObj.addProperty("building_number", add.getBuildingNo());
						addressObj.addProperty("city", add.getCity());
						addressObj.addProperty("state", add.getState());
						addressObj.addProperty("country", add.getCountry());
						addressObj.addProperty("zip", add.getZip());
						addressList.add(addressObj);
						
					}
					
					addressFetched = addressList.toString();
					dataResponse = mapper.readTree(addressFetched);
				
				
			} catch ( Exception e) {
				
				((ObjectNode) dataResponse).put("Error :",e.getMessage());
				
			}
			finally
			{
				out.print(dataResponse);
				out.close();
			}
			
			
		

}
}