package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.dto.EmployeeDto;
import com.nagarro.exitproject.model.Employee;
import com.nagarro.exitproject.service.CashDrawerService;
import com.nagarro.exitproject.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CashDrawerService cashDrawerService;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> employeeLogin(HttpServletRequest request, HttpServletResponse response,
			                              @RequestBody Employee employee, 
			                              @RequestParam("balance") String balance,
			                              HttpSession session
			                              ) {
        System.out.println("INSIDE THE LOGIN CONTROLLER");
        System.out.println(employee.getName() + ": " + employee.getPassword());
		Employee emp = this.employeeService.authenticate(employee);
		if(emp != null){  // Authenticated.
			EmployeeDto empDto = new EmployeeDto();
			empDto.setId(emp.getId());
			empDto.setName(emp.getName());
			empDto.setCashDrawerId(emp.getCashDrawer().getId());
			session.putValue("employee", emp);
			this.cashDrawerService.setStartBalance(Integer.parseInt(balance), emp.getCashDrawer().getId());
			return ResponseEntity.status(HttpStatus.OK).body(empDto);
		}else {
			System.out.println("Not Valid employee");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NOT VALID EMPLOYEE");
		}
	}
	
	@RequestMapping(value="nosession", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> employeeLogin(HttpServletRequest request, HttpServletResponse response) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EMPLOYEE NOT LOGGED IN.");	
	}
	
	
	

}  // End of class.
