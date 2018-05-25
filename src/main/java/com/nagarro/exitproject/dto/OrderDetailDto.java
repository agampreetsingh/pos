package com.nagarro.exitproject.dto;

import java.util.ArrayList;
import java.util.List;

import com.nagarro.exitproject.model.Order;
import com.nagarro.exitproject.model.Product;

public class OrderDetailDto {
	
	private Order order;
	private List<Product> productList = new ArrayList<Product>();
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	
	

}
