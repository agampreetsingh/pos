package com.nagarro.exitproject.controller;

import java.util.List;



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

import com.nagarro.exitproject.dto.ProductListDto;
import com.nagarro.exitproject.model.Product;
import com.nagarro.exitproject.service.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/searchby", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> searchProductsBy(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("key") String key) {
		List<Product> list = this.productService.searchProductBy(key);
		if(list != null) {
		   return ResponseEntity.status(HttpStatus.OK).body(list);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getProductsList(HttpServletRequest request, HttpServletResponse response) {
		ProductListDto prodList = new ProductListDto();
		prodList.setList(this.productService.getProducts());
		return ResponseEntity.ok().body(prodList);
	}

}
