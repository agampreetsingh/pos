package com.nagarro.exitproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.CustomerDaoImpl;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDaoImpl customerDao;

}
