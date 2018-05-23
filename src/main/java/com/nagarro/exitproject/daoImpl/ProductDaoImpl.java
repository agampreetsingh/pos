package com.nagarro.exitproject.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.model.Product;

@Repository
public class ProductDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		System.out.println("Got the product dao.");
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> list = null;
		try {
			Query query = session.createQuery("from Product where stock > 0");
			list = query.list();
		} catch(Exception e) {
			System.out.println("Product fetching error: " + e);
		}
		return list;
	}
}
