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

import com.nagarro.exitproject.service.OrderService;

@RestController
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	private int eid = 1;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> saveOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("paymentType") String pType, @RequestParam("custId") String cid
			) {
		System.out.println("Inside the order controller");
		this.orderService.saveOrder(pType, "xby", cid, eid);
		return null;
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> saveOrder(HttpServletRequest request, HttpServletResponse response) {
		
		this.orderService.getOrder(eid);
		return null;
	}
	
	

}
