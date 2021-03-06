package com.nagarro.exitproject.model;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String password;

	@JsonIgnore
	//@OneToOne(fetch=FetchType.EAGER)
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY)
	private List<CashDrawer> cashDrawer;

	@JsonIgnore
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY)
	private List<Order> order = new ArrayList<Order>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public List<CashDrawer> getCashDrawer() {
		return cashDrawer;
	}

	public void setCashDrawer(List<CashDrawer> cashDrawer) {
		this.cashDrawer = cashDrawer;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
}
