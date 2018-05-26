package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.dto.EmployeeDto;
import com.nagarro.exitproject.model.Employee;
import com.nagarro.exitproject.service.CashDrawerService;
import com.nagarro.exitproject.service.EmployeeService;

@RestController
@RequestMapping(value=Constants.EMP_URL)
public class EmployeeController {
	Logger logger = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CashDrawerService cashDrawerService;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> employeeLogin(HttpServletRequest request, HttpServletResponse response,
			                              @RequestBody Employee employee, 			                             
			                              HttpSession session
			                              ) {
		Employee emp = this.employeeService.authenticate(employee);
		if(emp != null){  
			EmployeeDto empDto = new EmployeeDto();
			empDto.setId(emp.getId());
			empDto.setName(emp.getName());
			session.putValue(Constants.SESSION_USER, emp);
			logger.info(Constants.SUCCESSFUL_LOGIN);
			return ResponseEntity.status(HttpStatus.OK).body(empDto);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.UNSUCCESSFUL_LOGIN);
		}
	}
}  // End of class.
