package com.nagarro.exitproject.daoImpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.dao.EmployeeDao;
import com.nagarro.exitproject.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Employee authenticate(String name, String password) {
		System.out.println("Login Dao");
        Session session = this.sessionFactory.getCurrentSession();
        Employee emp = null;
        try {
        	Query query = session.createQuery("from Employee where name=:username and password=:pass")
        			                     .setParameter("username", name)
        			                     .setParameter("pass", password);
        	emp = (Employee)query.uniqueResult();        	
        } catch (Exception e) {
        	System.out.println("Authentication Error: " + e);
        }        
		return emp;		
	}
}
