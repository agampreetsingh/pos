package com.nagarro.exitproject.daoImpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.dao.IEmployeeDao;
import com.nagarro.exitproject.model.Employee;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Employee authenticate(String name, String password) {
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
