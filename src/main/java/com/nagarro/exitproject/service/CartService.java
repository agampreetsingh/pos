package com.nagarro.exitproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.CartDaoImpl;
import com.nagarro.exitproject.model.CartProductEntries;

@Service
public class CartService {

	@Autowired
	private CartDaoImpl cartDao;
	
	@Transactional
	public boolean addProductToCart(String pid, String cid) {
	   System.out.println("Cart service.");
       return this.cartDao.addProductToCart(Integer.parseInt(pid), Integer.parseInt(cid), 1);
	}
	
	@Transactional
	public boolean deleteFromCart(String pid, String cid) {
		System.out.println("Cart Deleter service.");
		return this.cartDao.deleteFromCart(Integer.parseInt(pid), Integer.parseInt(cid));
	}
	
	@Transactional
	public boolean increaseQuantity(String pid, String cid) {
		System.out.println("Increasing the quantity");
		return this.cartDao.increaseQuantity(Integer.parseInt(pid), Integer.parseInt(cid));
	}

	@Transactional
	public boolean decreaseQuantity(String pid, String cid) {
		System.out.println("Decreasing the quantity.");
		return this.cartDao.decreaseQuantity(Integer.parseInt(pid), Integer.parseInt(cid));
	}

	@Transactional
	public List<CartProductEntries> getCart(String cid) {
		return this.cartDao.getCart(Integer.parseInt(cid));		
	}	
	
}
