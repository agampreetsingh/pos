package com.nagarro.exitproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.CustomerDaoImpl;
import com.nagarro.exitproject.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDaoImpl customerDao;
	
	@Transactional
	public List<Customer> getCustomers(String searchKey) {
		return this.customerDao.getCustomers(searchKey);
	}
	
	@Transactional
	public Customer getCustomer(int id) {
		return this.customerDao.getCustomerById(id);
	}

}
