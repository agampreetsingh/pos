package com.nagarro.exitproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.CartDaoImpl;
import com.nagarro.exitproject.daoImpl.OrderDaoImpl;
import com.nagarro.exitproject.dto.OrderDetailDto;
import com.nagarro.exitproject.model.Order;

@Service
public class OrderService {

	@Autowired
	private OrderDaoImpl orderDao;
	@Autowired
	private CartDaoImpl cartDao;

	@Transactional
	public boolean saveOrder(String pType, String status, String cid, int eid) {
		if(this.orderDao.saveOrder(pType, status, Integer.parseInt(cid), eid)){
			this.cartDao.deleteCart(Integer.parseInt(cid));
			return true;
		}
		return false;
	}
	
	@Transactional
	public List<Order> getOrders() {
		return this.orderDao.getOrders();
	}
	
	@Transactional
	public List<Order> getOrder(int eid) {
		return this.orderDao.getOrders(eid);
	}

	@Transactional
	public List<Order> getOrdersByStatus(String status) {
       return this.orderDao.getOrdersByStatus(status);
	}

	@Transactional
	public OrderDetailDto getOrderById(String id) {
        return this.orderDao.getOrderById(Integer.parseInt(id));
	}

	@Transactional
	public boolean reloadOrder(String orderId) {
       return this.orderDao.reloadOrder(Integer.parseInt(orderId));		
	}
	
	
}
