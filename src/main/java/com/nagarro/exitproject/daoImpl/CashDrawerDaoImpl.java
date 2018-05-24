package com.nagarro.exitproject.daoImpl;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.model.CashDrawer;
import com.nagarro.exitproject.model.Employee;

@Repository
public class CashDrawerDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public int setEndBalance(int balance, int id) {
		System.out.println("INside the cash dao.");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery(
							"from CashDrawer where id=:id")
					.setParameter("id", id);
		    CashDrawer cd = (CashDrawer)query.uniqueResult();
		    cd.setDate(new Date());
		    cd.setEndBalance(cd.getEndBalance() + balance);
		    return (int)cd.getEndBalance();
		} catch(Exception e) {
			System.out.println("Set balance error: " + e);
			return -1;
		}
	}
	
	public CashDrawer setStartBalance(int balance, int id) {
		System.out.println("INside the cash dao.");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery(
							"from CashDrawer where id=:id")
					.setParameter("id", id);
		    CashDrawer cd = (CashDrawer)query.uniqueResult();
		    cd.setDate(new Date());
		    cd.setStartBalance(balance);
		    return cd;
		} catch (Exception e) {
			System.out.println("Error balance: " + e);
			return null;
		}
	}

	public CashDrawer getDrawer(int eid) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery(
							"from Employee where id=:id")
					.setParameter("id", eid);
			Employee emp = (Employee) query.uniqueResult();
			int drawerId = emp.getCashDrawer().getId();
			
			Query cashQuery = session.createQuery("from CashDrawer where id=:id and date=:d")
					                 .setParameter("id", drawerId)
			                         .setParameter("d", new Date());
			CashDrawer cd = (CashDrawer)cashQuery.uniqueResult();
			if(cd == null) {
                System.out.println("No cash drawer");				
				return null;
			}
			
			return cd;
		} catch (Exception e) {
			System.out.println("Error getting drawer: " + e);        
			return null;
		}

	}
}
