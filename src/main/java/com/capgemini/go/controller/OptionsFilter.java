package com.capgemini.go.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OptionsFilter implements Filter {

	@Override
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		enableCORS (request, response);
		chain.doFilter(request, response);
	}
	
	protected boolean enableCORS (ServletRequest request, ServletResponse response) {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;

			String requestOrigin = req.getHeader("Origin");
			if (requestOrigin == null) {
				requestOrigin = "*";
			}
			System.out.println("Request Origin = " + requestOrigin);
			resp.setHeader("Access-Control-Allow-Origin", requestOrigin);
			resp.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
			resp.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
			resp.setHeader("Access-Control-Allow-Credentials", "true");
			return true;
		} else {
			return false;
		}
	}
}
