package com.nagarro.exitproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.dto.OrderDetailDto;
import com.nagarro.exitproject.model.Order;
import com.nagarro.exitproject.service.OrderService;

@RestController
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	private int eid = 1;
	
	@RequestMapping(value="/reload", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> reloadOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String orderId) {
		if(this.orderService.reloadOrder(orderId)){
			ResponseEntity.status(HttpStatus.OK).body("ORDER SUCCESSFULLY RELOADED TO CART.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAILED TO RELOAD THE ORDER.");
	}
	
	@RequestMapping(value="/byid", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getOrderById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("orderId") String id) {
		OrderDetailDto order = this.orderService.getOrderById(id);
		if(order != null) {
			//OrderDetailDto dto = new OrderDetailDto();
			//dto.setOrder(order);
			//dto.setProductList(order.getProductEntries());
			//System.out.println(order.getProductEntries().get(0).getProduct().getName());
			return ResponseEntity.status(HttpStatus.OK).body(order);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ORDER NOT FOUND.");
	}
	
	
	@RequestMapping(value="/bystatus", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getOrdersByStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("status") String status) {
		System.out.println("Order by status controller.");
		List<Order> orders = this.orderService.getOrdersByStatus(status);
		if(orders != null) {
			return ResponseEntity.status(HttpStatus.OK).body(orders);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SUCH ORDERS.");
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> saveOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("paymentType") String pType, @RequestParam("custId") String cid,
			@RequestParam("status") String status
			) {
		System.out.println("Inside the order controller");
		if(this.orderService.saveOrder(pType, status, cid, eid)){
			return ResponseEntity.status(HttpStatus.OK).body("ORDER SUCCESSFULLY SAVED.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ORDER NOT SAVED.(QUANTITY GREATER THAN STOCK)");
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getAllOrders(HttpServletRequest request, HttpServletResponse response) {
		List<Order> orderList = this.orderService.getOrder(eid);;
		if(orderList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(orderList);
		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Fetching order list.");
	}
	
	

}
