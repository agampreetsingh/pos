package com.nagarro.exitproject.daoImpl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.model.Cart;
import com.nagarro.exitproject.model.CartProductEntries;
import com.nagarro.exitproject.model.Customer;
import com.nagarro.exitproject.model.Employee;
import com.nagarro.exitproject.model.Order;
import com.nagarro.exitproject.model.Product;


@Repository
public class OrderDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void getOrders(int eid) {
		System.out.println("Getting the orders");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query empQuery = session.createQuery("from Employee where id=:id")
	                 .setParameter("id", eid);
            Employee employee = (Employee)empQuery.uniqueResult();
            
            Query ordQuery = session.createQuery("from Order where employee=:emp")
            		                .setParameter("emp", employee);
            @SuppressWarnings("unchecked")
			List<Order> order = ordQuery.list();
            System.out.println(order.size());
		} catch (Exception e) {
			System.out.println("Error Getting the orders: " + e);
		}
		
	}
	
	public void saveOrder(String paymentType, String status, int cid, int eid) {
		System.out.println("Save Order dao.");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query empQuery = session.createQuery("from Employee where id=:id")
	                 .setParameter("id", eid);
            Employee employee = (Employee)empQuery.uniqueResult();
			
			Query custQuery = session.createQuery("from Customer where id=:id")
	                 .setParameter("id", cid);
           Customer customer = (Customer)custQuery.uniqueResult();
           // Cart of the customer.
           Cart cart = (Cart)session.createQuery("from Cart where customer=:cust")
	                 .setParameter("cust", customer)
	                 .uniqueResult();
           
           @SuppressWarnings("unchecked")
		   List<CartProductEntries> cpentries =  session.
					createQuery("from CartProductEntries where cart=:cart")					
					.setParameter("cart", cart)
					.list();
           int amount= 0;
           for(CartProductEntries cpEntry : cpentries) {
        	   int productId = cpEntry.getProduct().getId();
        	   int quantity = cpEntry.getQuantity();
        	   Product product = (Product) session
   					.createQuery("from Product where id=:id")
   					.setParameter("id", productId).uniqueResult();
        	   int price = (int)product.getPrice();
        	   amount += quantity*price;
        	   System.out.println(amount);
           }
           Calendar c = Calendar.getInstance();
           
           String date = c.get(Calendar.YEAR)+"-"+
        		   c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE);
          Order order = new Order();
//           //order.setId(2);
    	   order.setCustomer(customer);
    	  // order.setDate(date);
    	   order.setAmount(100);
    	   order.setPaymentType(paymentType);
//    	   //order.setStatus(status);
    	   order.setEmployee(employee);
//    	   
    	   session.save(order);
//    	   session.flush();
    	   
		} catch (Exception e) {
			System.out.println("Error saving the order: " + e);
		}
	}
}
