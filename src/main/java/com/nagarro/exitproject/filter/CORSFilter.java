package com.nagarro.exitproject.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter{
	private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200");


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("FILTER IS WORKING.");
	
		if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
			final HttpServletRequest request = (HttpServletRequest) req;
			final HttpServletResponse response = (HttpServletResponse) res;

			// Access-Control-Allow-Origin
			final String origin = request.getHeader("Origin");
			response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
			response.setHeader("Vary", "Origin");

			// Access-Control-Max-Age
			response.setHeader("Access-Control-Max-Age", "3600");

			// Access-Control-Allow-Credentials
			response.setHeader("Access-Control-Allow-Credentials", "true");

			// Access-Control-Allow-Methods
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");

			// Access-Control-Allow-Headers
			response.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
		}

    }


	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
