package com.nagarro.exitproject.dao;

import java.util.List;

import com.nagarro.exitproject.model.Customer;

public interface ICustomerDao {
	public Customer getCustomerById(int customerId);
	public List<Customer> getCustomers(String searchKey);

}
