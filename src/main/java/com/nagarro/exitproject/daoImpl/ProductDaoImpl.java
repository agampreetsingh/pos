package com.nagarro.exitproject.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.dao.IProductDao;
import com.nagarro.exitproject.model.Product;

@Repository
public class ProductDaoImpl implements IProductDao{

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

	@SuppressWarnings("unchecked")
	public List<Product>  searchProductBy(String key) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> list = null;
		try {
			Query query = session.createQuery("from Product where name like :key or description like :key or code like :key")
					             .setParameter("key", "%"+key+"%");
			list = query.list();
			return list;
		} catch (Exception e) {
			System.out.println("Error fetching products by search Key: " + e);
			return null;
		}
	}
}
