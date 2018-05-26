package com.nagarro.exitproject.dao;

import java.util.List;

import com.nagarro.exitproject.model.CartProductEntries;

public interface ICartDao {
	public List<CartProductEntries> getCart(int customerId);
	public boolean decreaseQuantity(int productId, int customerId);
	public boolean increaseQuantity(int productId, int customerId);
	public boolean deleteCart(int customerId);
	public boolean deleteFromCart(int productId, int customerId);
	public boolean addProductToCart(int prodcutId, int customerId, int quantity);

}
