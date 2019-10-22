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
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.AuthenticationException;
import com.capgemini.go.exception.ProductMasterException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.service.ProductMasterService;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.service.UserService;
import com.capgemini.go.service.UserServiceImpl;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.AuthenticationConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Servlet implementation class UpdateProductServlet
 */
public class UserRegistrationServlet extends HttpServlet {
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

		String userName = myMap.get("userName");
		String userId = myMap.get("userId");
		String userMail = myMap.get("userMail");
		String password = myMap.get("password");

		long userNumber = Long.parseLong(myMap.get("userNumber"));
		int userCategory = Integer.parseInt(myMap.get("userCategory"));

		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean result = false;
		PrintWriter out = response.getWriter();
		try {
			password = Authentication.encrypt(password, AuthenticationConstants.secretKey);
			UserService user = new UserServiceImpl();
			UserDTO newUser = new UserDTO(userName, userId, userMail, password, userNumber, userCategory, false);

			result = user.userRegistration(newUser);

			if (result) {
				((ObjectNode) dataResponse).put("Success :", "User Succesfully Registered");
			}

		} catch (Exception e) {

			((ObjectNode) dataResponse).put("Error :", e.getMessage());

		} finally {
			out.print(dataResponse);
			out.close();
		}

	}
}