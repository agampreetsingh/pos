package com.nagarro.exitproject.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.model.Cart;
import com.nagarro.exitproject.model.CartProductEntries;
import com.nagarro.exitproject.model.Customer;
import com.nagarro.exitproject.model.Product;

@Repository
public class CartDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public void deleteFromCart(int pid, int cid) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query custQuery = session.createQuery("from Customer where id=:id")
	                 .setParameter("id", cid);
            Customer customer = (Customer)custQuery.uniqueResult();
            
            // Get the cartId
			Cart cart = (Cart)session.createQuery("from Cart where customer=:cust")
					                 .setParameter("cust", customer)
					                 .uniqueResult();
			Product product = (Product) session
					.createQuery("from Product where id=:id")
					.setParameter("id", pid).uniqueResult();
			session.close();
			
			Session s = this.sessionFactory.getCurrentSession();
			CartProductEntries cpentry = new CartProductEntries();
			cpentry.setProduct(product);
			cpentry.setCart(cart);
			s.delete(cpentry);			
		
		} catch (Exception e) {
			System.out.println("Error Deleting From the Cart: " + e);
		}
	}

	public boolean addProductToCart(int pid, int cid, int quantity) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query custQuery = session.createQuery("from Customer where id=:id")
					.setParameter("id", cid);
			Customer customer = (Customer) custQuery.uniqueResult();

			Cart cart = new Cart();
			cart.setCustomer(customer);
			session.save(cart);

			// To get the id of the newly added cart.
			Cart addedCart = (Cart) session
					.createQuery("from Cart where customer=:cust")
					.setParameter("cust", customer).uniqueResult();

			Product product = (Product) session
					.createQuery("from Product where id=:id")
					.setParameter("id", pid).uniqueResult();

			CartProductEntries cpentry = new CartProductEntries();
			cpentry.setProduct(product);
			cpentry.setCart(addedCart);
			cpentry.setQuantity(quantity);
			session.save(cpentry);
			return true;

		} catch (Exception e) {
			System.out.println("Adding product to cart error: " + e);
			return false;
		}
	}

}
