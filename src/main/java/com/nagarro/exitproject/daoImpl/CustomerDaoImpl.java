package com.nagarro.exitproject.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.model.Customer;

@Repository
public class CustomerDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Customer> getCustomers(String searchKey) {
		List<Customer> customers = null;
		
		return null;
	}
}
