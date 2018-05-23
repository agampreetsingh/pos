package com.nagarro.exitproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.ProductDaoImpl;
import com.nagarro.exitproject.model.Product;


@Service
public class ProductService {

	@Autowired
	private ProductDaoImpl productDao;
	
	@Transactional
	public List<Product> getProducts() {
		System.out.println("Product service.");
		return this.productDao.getProducts();
	}
	
}
