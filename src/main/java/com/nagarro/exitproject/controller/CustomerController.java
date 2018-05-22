package com.nagarro.exitproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.service.CustomerService;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	

}
