package com.nagarro.exitproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String code;
	private String name;
	private String description;
	private int stock;
	private double price;
	
	@OneToMany(mappedBy="product")
	private List<OrderProductEntries> orderProductEntries = new ArrayList<OrderProductEntries>();
    
	@OneToMany(mappedBy="product")
	private List<CartProductEntries> cartProductEntries = new ArrayList<CartProductEntries>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<OrderProductEntries> getOrderProductEntries() {
		return orderProductEntries;
	}

	public void setOrderProductEntries(List<OrderProductEntries> orderProductEntries) {
		this.orderProductEntries = orderProductEntries;
	}

	public List<CartProductEntries> getCartProductEntries() {
		return cartProductEntries;
	}

	public void setCartProductEntries(List<CartProductEntries> cartProductEntries) {
		this.cartProductEntries = cartProductEntries;
	}
	
	
	
	
	
	
    
	
	

	


	
}
