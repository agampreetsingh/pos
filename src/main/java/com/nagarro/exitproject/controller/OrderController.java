package com.nagarro.exitproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.dto.ListDto;
import com.nagarro.exitproject.dto.OrderDetailDto;
import com.nagarro.exitproject.dto.PlaceOrderDto;
import com.nagarro.exitproject.model.Order;
import com.nagarro.exitproject.service.OrderService;

@RestController
@RequestMapping(value=Constants.ORDER_URL)
public class OrderController {
	Logger logger = Logger.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	int eid = 1;
	
	@RequestMapping(value="{id}/reload", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> reloadOrder(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String orderId) {
		if(this.orderService.reloadOrder(orderId)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.ORDER_RELOAD_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ORDER_FAILURE_RELOAD_MESSAGE);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getOrderById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {
		OrderDetailDto order = this.orderService.getOrderById(id);
		if(order != null) {			
			return ResponseEntity.status(HttpStatus.OK).body(order);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ORDER_NOT_FOUND);
	}
	
	
	@RequestMapping(value="/bystatus", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getOrdersByStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("status") String status) {
		List<Order> orders = this.orderService.getOrdersByStatus(status);
		ListDto dto = new ListDto();
		dto.setList(orders);
		if(orders != null) {
			return ResponseEntity.status(HttpStatus.OK).body(orders);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ORDER_NOT_FOUND);
	}
	
	
	@RequestMapping(value="/customer/{cid}/paymenttype/status", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> saveOrder(HttpServletRequest request, HttpServletResponse response,
			 @PathVariable("cid") String cid,
			 @RequestBody PlaceOrderDto orderdto
			) {
//		Employee emp = ((Employee)request.getSession().getAttribute(Constants.SESSION_USER));
//		int eid = emp.getId();
	
		if(this.orderService.saveOrder(orderdto.getPaymentType(), orderdto.getStatus(), cid, eid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.ORDER_SAVE_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ORDER_SAVE_FAILURE);
	}
	
	@RequestMapping(value="/bydate", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getAllOrders(HttpServletRequest request, HttpServletResponse response) {
//		Employee emp = ((Employee)request.getSession().getAttribute(Constants.SESSION_USER));
//		int eid = emp.getId();
		List<Order> orderList = this.orderService.getOrder(eid);
		ListDto dto = new ListDto();
		dto.setList(orderList);
		if(orderList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(orderList);
		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ORDER_FETCH_ERROR);
	}
	
	

}
