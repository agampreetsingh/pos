package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.dto.CustomerListDto;
import com.nagarro.exitproject.service.CustomerService;

@RestController
@RequestMapping(value="/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> searchCustomer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "search", required = true) String searchKey) {
		System.out.println("Get Customers Controller");	
		
		CustomerListDto cusList = new CustomerListDto();
		cusList.setCustomers(this.customerService.getCustomers(searchKey));
		//this.customerService.getCustomers(searchKey);
		//return ResponseEntity.ok().body(list);
		
		System.out.println("Leaving the controller.");
		return ResponseEntity.ok().body(cusList);
	}
}
