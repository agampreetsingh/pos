package com.nagarro.exitproject.dao;

import java.util.List;

import com.nagarro.exitproject.dto.OrderDetailDto;
import com.nagarro.exitproject.model.Order;

public interface IOrderDao {
	public boolean reloadOrder(int orderId);
	public OrderDetailDto getOrderById(int id);
	public List<Order> getOrdersByStatus(String status);
	public List<Order> getOrders(int employeeId);
	public boolean saveOrder(String paymentType, String status, int customerId, int employeeId);

}
