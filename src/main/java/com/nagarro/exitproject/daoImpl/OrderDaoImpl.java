package com.nagarro.exitproject.daoImpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.dto.OrderDetailDto;
import com.nagarro.exitproject.model.Cart;
import com.nagarro.exitproject.model.CartProductEntries;
import com.nagarro.exitproject.model.Customer;
import com.nagarro.exitproject.model.Employee;
import com.nagarro.exitproject.model.Order;
import com.nagarro.exitproject.model.OrderProductEntries;
import com.nagarro.exitproject.model.Product;

@Repository
public class OrderDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public boolean reloadOrder(int orderId) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query ordQuery = session.createQuery("from Order where id=:id")
					.setParameter("id", orderId);
			Order order = null;
			order = (Order) ordQuery.uniqueResult();
			
			Customer customer = order.getCustomer();
			
			List<OrderProductEntries> opentries = null;
			opentries = session.createQuery("from OrderProductEntries where order=:o")
					           .setParameter("o", order)
					           .list();
			
			// Create the cart of the customer.
			Cart cart = new Cart();
			cart.setCustomer(customer);
			session.save(cart);
			
			Cart savedCart = (Cart) session
					.createQuery("from Cart where customer=:cust")
					.setParameter("cust", customer).uniqueResult();
			
			// Looping the products to add to cart.
			for(OrderProductEntries entry : opentries) {
				int quantity = entry.getQuantity();
				Product product = entry.getProduct();
				CartProductEntries cpentry = new CartProductEntries();
				cpentry.setCart(savedCart);
				cpentry.setQuantity(quantity);
				cpentry.setProduct(product);
				session.save(cpentry);
			}
			session.delete(order);
			return true;
			
		} catch (Exception e) {
			System.out.println("Error reloading the order: " + e);
			return false;
		}
	}

	public OrderDetailDto getOrderById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query ordQuery = session.createQuery("from Order where id=:id")
					.setParameter("id", id);
			Order order = null;
			order = (Order) ordQuery.uniqueResult();
			OrderDetailDto dto = new OrderDetailDto();
			if (order != null) {
				dto.setOrder(order);
				List<OrderProductEntries> list = order.getProductEntries();
				for (OrderProductEntries entry : list) {
					dto.getProductList().add(entry.getProduct());
				}
			}
			return dto;
		} catch (Exception e) {
			System.out.println("Error getting order by status: " + e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrdersByStatus(String status) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query ordQuery = session.createQuery("from Order where status=:s")
					.setParameter("s", status);
			List<Order> orders = null;
			orders = ordQuery.list();
			if (orders.size() == 0)
				return null;
			return orders;

		} catch (Exception e) {
			System.out.println("Error getting order by status: " + e);
			return null;
		}
	}

	public List<Order> getOrders(int eid) {
		System.out.println("Getting the orders");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query ordQuery = session
					.createQuery("from Order order by date DESC");
			@SuppressWarnings("unchecked")
			List<Order> orders = ordQuery.list();
			if (orders.size() == 0)
				return null;
			System.out.println(orders.get(0).getDate());
			return orders;
		} catch (Exception e) {
			System.out.println("Error Getting the orders: " + e);
			return null;
		}

	}

	public boolean saveOrder(String paymentType, String status, int cid, int eid) {
		System.out.println("Save Order dao.");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query empQuery = session.createQuery("from Employee where id=:id")
					.setParameter("id", eid);
			Employee employee = (Employee) empQuery.uniqueResult();

			Query custQuery = session.createQuery("from Customer where id=:id")
					.setParameter("id", cid);
			Customer customer = (Customer) custQuery.uniqueResult();
			// Cart of the customer.
			Cart cart = (Cart) session
					.createQuery("from Cart where customer=:cust")
					.setParameter("cust", customer).uniqueResult();

			@SuppressWarnings("unchecked")
			List<CartProductEntries> cpentries = session
					.createQuery("from CartProductEntries where cart=:cart")
					.setParameter("cart", cart).list();
			int amount = 0;
			for (CartProductEntries cpEntry : cpentries) {
				int productId = cpEntry.getProduct().getId();
				int quantity = cpEntry.getQuantity();
				Product product = (Product) session
						.createQuery("from Product where id=:id")
						.setParameter("id", productId).uniqueResult();
				int price = (int) product.getPrice();
				amount += quantity * price;
				System.out.println(amount);
			}
			Order order = new Order();
			order.setDate(new Date(System.currentTimeMillis()));
			order.setAmount(amount);
			order.setCustomer(customer);
			order.setEmployee(employee);
			order.setPaymentType(paymentType);
			order.setStatus(status);

			session.save(order);

			Order savedOrder = (Order) session
					.createQuery(
							"from Order where customer=:cust and employee=:emp")
					.setParameter("cust", customer)
					.setParameter("emp", employee).uniqueResult();

			// Order entries...
			for (CartProductEntries cpEntry : cpentries) {
				int productId = cpEntry.getProduct().getId();
				int quantity = cpEntry.getQuantity();
				Product product = (Product) session
						.createQuery("from Product where id=:id")
						.setParameter("id", productId).uniqueResult();
				int stock = product.getStock();
				if (stock - quantity < 0) {
					session.delete(savedOrder);
					return false;
				}
				product.setStock(product.getStock() - quantity);
				int amountPerProduct = (int) ((int) quantity * product
						.getPrice());
				OrderProductEntries opentry = new OrderProductEntries();
				opentry.setAmount(amountPerProduct);
				opentry.setOrder(savedOrder);
				opentry.setQuantity(quantity);
				opentry.setProduct(product);
				session.save(opentry);
			}
			return true;

		} catch (Exception e) {
			System.out.println("Error saving the order: " + e);
			return false;
		}
	}

}
