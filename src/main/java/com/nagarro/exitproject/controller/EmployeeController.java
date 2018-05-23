package com.nagarro.exitproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.dto.EmployeeDto;
import com.nagarro.exitproject.model.Employee;
import com.nagarro.exitproject.model.Order;
import com.nagarro.exitproject.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> employeeLogin(HttpServletRequest request, HttpServletResponse response,
			                              @RequestBody Employee employee) {
        System.out.println("INSIDE THE LOGIN CONTROLLER");
		Employee emp = this.employeeService.authenticate(employee);
		if(emp != null){
			EmployeeDto empDto = new EmployeeDto();
			empDto.setId(emp.getId());
			empDto.setName(emp.getName());
			empDto.setCashDrawerId(emp.getCashDrawer().getId());
			return ResponseEntity.ok().body(empDto);
		}else {
			System.out.println("Not Valid employee");
			return ResponseEntity.ok().body("NOT VALID EMPLOYEE");
		}
	}
	

}  // End of class.
