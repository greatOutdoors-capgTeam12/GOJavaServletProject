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

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DeliveryTimeReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Inside DeliveryTimeReport Servlet");

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
		String retailerId = myMap.get("retailerId");
		String reportType = myMap.get("reportType");
		int rt = Integer.parseInt(reportType);
		System.out.println("Here : " + retailerId + " " + reportType);

		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		PrintWriter out = response.getWriter();

		String inventoryList = null;
		try {
			GoAdminService.ReportType repType = GoAdminService.ReportType.MONTHLY_SHELF_TIME;
			if (rt == 1) {
				repType = GoAdminService.ReportType.MONTHLY_SHELF_TIME;
			} else if (rt == 2) {
				repType = GoAdminService.ReportType.QUARTERLY_SHELF_TIME;
			} else if (rt == 3) {
				repType = GoAdminService.ReportType.YEARLY_SHELF_TIME;
			}
			GoAdminService goadmin = new GoAdminServiceImpl ();
			List<RetailerInventoryBean> list = goadmin.getDeliveryTimeReport(repType, retailerId, 0);
			inventoryList = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		} catch (Exception e) {
			((ObjectNode) dataResponse).put("message", e.getMessage());
		} finally {
			out.print(inventoryList);
		}

	}
}
