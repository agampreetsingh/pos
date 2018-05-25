package com.nagarro.exitproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JsonIgnore
	@OneToOne
	private Customer customer;	
	
	@JsonIgnore
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL)
	private List<CartProductEntries> cartProductEntries = new ArrayList<CartProductEntries>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartProductEntries> getCartProductEntries() {
		return cartProductEntries;
	}

	public void setCartProductEntries(List<CartProductEntries> cartProductEntries) {
		this.cartProductEntries = cartProductEntries;
	}
	
	
	


}
