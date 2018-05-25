package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.service.CartService;

@RestController
@RequestMapping(value=Constants.CART_URL)
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="{cid}", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getProductsOfCustomer(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("cid") String cid) {
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.getCart(cid));
	}
	
	@RequestMapping(value="addProduct/{pid}/{cid}", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> addProductToCart(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pid") String pid, @PathVariable("cid") String cid) {
		if(this.cartService.addProductToCart(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.ADD_PRODUCT_MESSAGE);
		}	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAILED TO ADD THE PRODUCT.");
	}
	
	@RequestMapping(value="delete/{pid}/{cid}", method=RequestMethod.DELETE)
	@ResponseBody	
	public ResponseEntity<?> removeProductFromCart(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pid") String pid, @PathVariable("cid") String cid) {
		if(this.cartService.deleteFromCart(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.DELETE_PRODUCT_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CANNOT DELETE THE PRODUCT FROM THE CART.");
	}
	
	@RequestMapping(value="inc/{pid}/{cid}", method=RequestMethod.PUT)
	@ResponseBody	
	public ResponseEntity<?> increaseQuantity(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pid") String pid, @PathVariable("cid") String cid) {
		System.out.println("Increase quantity controller.");
		if(this.cartService.increaseQuantity(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.QUANTITY_INC_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to increment the quantity.");
	}
	
	@RequestMapping(value="dec/{pid}/{cid}", method=RequestMethod.PUT)
	@ResponseBody	
	public ResponseEntity<?> decreaseQuantity(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pid") String pid, @PathVariable("cid") String cid) {
		System.out.println("Decrease quantity controller.");
		if(this.cartService.decreaseQuantity(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.QUANTITY_DEC_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to decrement the quantity.");
	}
	
	

}
