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

import com.nagarro.exitproject.service.CartService;

@RestController
@RequestMapping(value="/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> addProductToCart(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("prodId") String pid, @RequestParam("custId") String cid) {
		System.out.println("Cart controller.");
		if(this.cartService.addProductToCart(pid, cid)){
			return ResponseEntity.ok().body("PRODUCT ADDED TO THE CART.");
		}	
		return ResponseEntity.ok().body("FAILED TO ADD THE PRODUCT.");
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> removeProductFromCart(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("prodId") String pid, @RequestParam("custId") String cid) {
		System.out.println("Delete cart controller.");
		this.cartService.deleteFromCart(pid, cid);
		return null;
	}
	
	

}
