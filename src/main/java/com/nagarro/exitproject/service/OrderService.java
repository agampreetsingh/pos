package com.nagarro.exitproject.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.OrderDaoImpl;

@Service
public class OrderService {

	@Autowired
	private OrderDaoImpl orderDao;

	@Transactional
	public void saveOrder(String pType, String status, String cid, int eid) {
		System.out.println("Inside the save order service.");
		this.orderDao.saveOrder(pType, status, Integer.parseInt(cid), eid);
	}
	
	@Transactional
	public void getOrder(int eid) {
		this.orderDao.getOrders(eid);
	}
	
	
}
