package com.nagarro.exitproject.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String... args) {
		Configuration con = new Configuration()
		                        .configure()
		                        .addAnnotatedClass(Employee.class)
		                        .addAnnotatedClass(CashDrawer.class)
		                        .addAnnotatedClass(Cart.class)
		                        .addAnnotatedClass(CartProductEntries.class)
		                        .addAnnotatedClass(Customer.class)
		                        .addAnnotatedClass(Product.class)
		                        .addAnnotatedClass(Order.class)
		                        .addAnnotatedClass(OrderProductEntries.class);
	    @SuppressWarnings("deprecation")
		SessionFactory sf = con.buildSessionFactory();
	    Session s = sf.openSession();
	    s.beginTransaction();
	    
	    s.getTransaction().commit();
	    s.close();
	    sf.close();
	    
	}

}
