package com.nagarro.exitproject.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.dao.ICustomerDao;
import com.nagarro.exitproject.model.Customer;

@Repository
public class CustomerDaoImpl implements ICustomerDao{

	@Autowired
	private SessionFactory sessionFactory;
	Logger logger = Logger.getLogger(CustomerDaoImpl.class);
	
	public Customer getCustomerById(int custId) {
		return (Customer) this.sessionFactory.getCurrentSession().get(Customer.class, custId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomers(String searchKey) {
		System.out.println("Customer Dao.");
		List<Customer> customers = null;
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("from Customer where email like :key or name like :key or mobile like :key")
					             .setParameter("key", "%" + searchKey + "%");
			customers = query.list();
		} catch (Exception e) {
           logger.error(e);
		}		
		return customers;
	}
}
