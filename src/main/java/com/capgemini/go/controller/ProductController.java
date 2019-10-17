package com.capgemini.go.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.service.UserService;
import com.capgemini.go.service.UserServiceImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Path("/products")
public class ProductController {
	

	@GET
	@Path("/lists")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductList() {
		Response response = null;
		ResponseBuilder rb = null;
		UserService user = new UserServiceImpl();
		try {
			List<ProductBean> allProds = user.getAllProducts();
			JsonArray productList = new JsonArray();
			for (ProductBean prod : allProds) {
				JsonObject productObj = new JsonObject();
				productObj.addProperty("prodid", prod.getProductId());
				productObj.addProperty("prodName", prod.getProductName());
				productObj.addProperty("prodBrand", prod.getManufacturer());
				productObj.addProperty("prodSpec", prod.getSpecification());
				productObj.addProperty("prodDim", prod.getDimension());
				productObj.addProperty("prodQty", prod.getQuantity());
				productObj.addProperty("prodPrice", prod.getPrice());
				productObj.addProperty("prodColor", prod.getColour());
				productObj.addProperty("category", prod.getProductCategory());
				productObj.addProperty("isActive", true);
				productObj.addProperty("photoPath", "assets/images/products/" + prod.getProductId() + ".jpg");
				productList.add(productObj);
			}

			System.out.println(productList.toString());
			rb = Response.status(Status.OK);
			rb.header("Access-Control-Allow-Origin", "*");
			rb.entity(productList.toString());
			response = rb.build();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		return response;

	}
}