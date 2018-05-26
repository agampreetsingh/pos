package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.dto.ListDto;
import com.nagarro.exitproject.service.CartService;

@RestController
@RequestMapping(value=Constants.CART_URL) // /cart
public class CartController {
	Logger logger = Logger.getLogger(CartController.class);
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/customer/{cid}", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getProductsOfCustomer(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("cid") String cid) {
		ListDto dto = new ListDto();
		dto.setList(this.cartService.getCart(cid));
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@RequestMapping(value="/customer/{cid}/addproduct/{pid}", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> addProductToCart(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pid") String pid, @PathVariable("cid") String cid) {
		if(this.cartService.addProductToCart(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.ADD_PRODUCT_MESSAGE);
		}	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.FAIL_PRODUCT_MESSAGE);
	}
	
	@RequestMapping(value="customer/{cid}/product/{pid}", method=RequestMethod.DELETE)
	@ResponseBody	
	public ResponseEntity<?> removeProductFromCart(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pid") String pid, @PathVariable("cid") String cid) {
		if(this.cartService.deleteFromCart(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.DELETE_PRODUCT_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.FAIL_DELETE_PRODUCT_MESSAGE);
	}
	
	@RequestMapping(value="/customer/{cid}", method=RequestMethod.DELETE)
	@ResponseBody	
	public ResponseEntity<?> removeCart(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("cid") String cid) {
		if(this.cartService.deleteCart(cid)) {
			return ResponseEntity.status(HttpStatus.OK).body("");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
	}
	
	@RequestMapping(value="inc/customer/{cid}/product/{pid}", method=RequestMethod.PUT)
	@ResponseBody	
	public ResponseEntity<?> increaseQuantity(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pid") String pid, @PathVariable("cid") String cid) {
		if(this.cartService.increaseQuantity(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.QUANTITY_INC_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.FAIL_QUANTITY_INC_MESSAGE);
	}
	
	@RequestMapping(value="dec/customer/{cid}/product/{pid}", method=RequestMethod.PUT)
	@ResponseBody	
	public ResponseEntity<?> decreaseQuantity(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pid") String pid, @PathVariable("cid") String cid) {
		if(this.cartService.decreaseQuantity(pid, cid)){
			return ResponseEntity.status(HttpStatus.OK).body(Constants.QUANTITY_DEC_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.FAIL_QUANTITY_DEC_MESSAGE);
	}
	
	

}
