package com.nagarro.exitproject.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.CartDaoImpl;

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
	public void deleteFromCart(String pid, String cid) {
		System.out.println("Cart Deleter service.");
		this.cartDao.deleteFromCart(Integer.parseInt(pid), Integer.parseInt(cid));
	}
	
	
}
