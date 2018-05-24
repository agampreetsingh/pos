package com.nagarro.exitproject.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date date;
	private double amount;
	private String paymentType;
//	private String status;
	
//	@ManyToMany(mappedBy="order")
//	private Set<Product> product = new HashSet<Product>();
//	
	@JsonIgnore
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private List<OrderProductEntries> productEntries = new ArrayList<OrderProductEntries>();
	
	
	@JsonIgnore
	@OneToOne
	private Employee employee;
	@JsonIgnore
	@OneToOne
	private Customer customer;
	
	

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public List<OrderProductEntries> getProductEntries() {
		return productEntries;
	}

	public void setProductEntries(List<OrderProductEntries> productEntries) {
		this.productEntries = productEntries;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	

}
