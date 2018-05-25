package com.nagarro.exitproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nagarro.exitproject.model.Employee;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
	throws Exception
	{
		// Any request other than login must be checked.
        if(!request.getRequestURI().contains("employee/login")) {
        	Employee emp = ((Employee)request.getSession().getAttribute("employee"));
        	if(emp == null) {
        		System.out.println("NOT Authorized.");
                response.sendError(401, "EMPLOYEE NOT LOGGED IN");
        		return false;
        	}
        }
		return true;
	}

}
