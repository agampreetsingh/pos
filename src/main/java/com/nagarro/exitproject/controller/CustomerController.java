package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.dto.ListDto;
import com.nagarro.exitproject.service.CustomerService;

@RestController
@RequestMapping(value=Constants.CUSTOMER_URL)
public class CustomerController {
	Logger logger = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> searchCustomerById(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("id") String id) {
		return ResponseEntity.ok().body(this.customerService.getCustomer(Integer.parseInt(id)));		
	}
	
	@RequestMapping(value="searchby", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> searchCustomer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "search", required = true) String searchKey) {
		ListDto cusList = new ListDto();
		cusList.setList(this.customerService.getCustomers(searchKey));
		return ResponseEntity.ok().body(this.customerService.getCustomers(searchKey));
	}
}
