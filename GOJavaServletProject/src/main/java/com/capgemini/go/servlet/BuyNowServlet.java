package com.capgemini.go.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.IdGenerationException;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.utility.GenerateOrderID;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Servlet implementation class UpdateProductServlet
 */
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
		}
		Map<String, String> myMap = new HashMap<String, String>();

		ObjectMapper objectMapper = new ObjectMapper();

		myMap = objectMapper.readValue(jb.toString(), HashMap.class);

		String userId = myMap.get("placeOrderCustId");
		String addressId = myMap.get("placeOrderAddrId");

		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean result = false;
		PrintWriter out = response.getWriter();

		RetailerService placeorder = new RetailerServiceImpl();
		String orderId = "";
		try {
			orderId = "ORD" + GenerateOrderID.generate();
			long millis = System.currentTimeMillis();
			java.sql.Date orderInitiationTime = new java.sql.Date(millis);
			java.sql.Timestamp orInitTimeStamp = new java.sql.Timestamp(millis);

			OrderDTO order = new OrderDTO(orderId, userId, addressId, false, orderInitiationTime, null);

			result = placeorder.placeOrder(order);

			if (result) {
				((ObjectNode) dataResponse).put("Success :", "Order is placed successfully");
			}

		} catch (Exception e) {

			((ObjectNode) dataResponse).put("Error :", e.getMessage());

		} finally {
			out.print(dataResponse);
			out.close();
		}
	}
}

//End of Buy Now Servlet


