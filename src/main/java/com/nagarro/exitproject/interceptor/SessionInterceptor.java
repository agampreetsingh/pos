package com.nagarro.exitproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.model.Employee;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
	throws Exception
	{
        if(!request.getRequestURI().contains("employee/login")) {
        	Employee emp = ((Employee)request.getSession().getAttribute(Constants.SESSION_USER));
        	if(emp == null) {
                response.sendError(401, "EMPLOYEE NOT LOGGED IN");
        		return false;
        	}
        }
		return true;
	}

}
