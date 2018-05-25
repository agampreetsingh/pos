package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.service.CartService;

@RestController
@RequestMapping(value="/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="getCart", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getProducts(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("custId") String cid) {
		System.out.println("Get Cart");
		
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.getCart(cid));
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> addProductToCart(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("prodId") String pid, @RequestParam("custId") String cid) {
		System.out.println("Cart controller.");
		if(this.cartService.addProductToCart(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body("PRODUCT ADDED TO THE CART.");
		}	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAILED TO ADD THE PRODUCT.");
	}
	
	@RequestMapping(value="delete", method=RequestMethod.DELETE)
	@ResponseBody	
	public ResponseEntity<?> removeProductFromCart(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("prodId") String pid, @RequestParam("custId") String cid) {
		System.out.println("Delete cart controller.");
		if(this.cartService.deleteFromCart(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body("DELETED THE PRODUCT FROM THE CART.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CANNOT DELETE THE PRODUCT FROM THE CART.");
	}
	
	@RequestMapping(value="inc", method=RequestMethod.PUT)
	@ResponseBody	
	public ResponseEntity<?> increaseQuantity(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("prodId") String pid, @RequestParam("custId") String cid) {
		System.out.println("Increase quantity controller.");
		if(this.cartService.increaseQuantity(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body("Quantity of the product incremented.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to increment the quantity.");
	}
	
	@RequestMapping(value="dec", method=RequestMethod.PUT)
	@ResponseBody	
	public ResponseEntity<?> decreaseQuantity(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("prodId") String pid, @RequestParam("custId") String cid) {
		System.out.println("Decrease quantity controller.");
		if(this.cartService.decreaseQuantity(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body("Quantity of the product decremented.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to decrement the quantity.");
	}
	
	

}
