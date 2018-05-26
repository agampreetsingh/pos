package com.nagarro.exitproject.daoImpl;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.exitproject.dao.ICashDrawerDao;
import com.nagarro.exitproject.model.CashDrawer;
import com.nagarro.exitproject.model.Employee;

@Repository
public class CashDrawerDaoImpl implements ICashDrawerDao{

	@Autowired
	private SessionFactory sessionFactory;
	Logger logger = Logger.getLogger(CashDrawerDaoImpl.class);
	
	public CashDrawer getDrawer(int id, Date date) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Employee emp = (Employee)session.createQuery("from Employee where id=:id")
                    .setParameter("id", id)
                    .uniqueResult();
			
			Query query = session.createQuery("from CashDrawer where employee=:emp and date=:date")
					             .setParameter("emp", emp)
					             .setParameter("date", date);
			CashDrawer cd = null;
			cd = (CashDrawer)query.uniqueResult();
			return cd;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public boolean createNewEntry(int eid, int startBalance) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Employee emp = (Employee)session.createQuery("from Employee where id=:id")
					                        .setParameter("id", eid)
					                        .uniqueResult();
			
			CashDrawer cd = new CashDrawer();
			cd.setDate(new Date(System.currentTimeMillis()));
			cd.setStartBalance(startBalance);
			cd.setEndBalance(cd.getStartBalance());
			cd.setEmployee(emp);
			session.save(cd);
			return true;
			
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean updateCash(int eid, int endBalance) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Employee emp = (Employee)session.createQuery("from Employee where id=:id")
                    .setParameter("id", eid)
                    .uniqueResult();
			CashDrawer cd = (CashDrawer)session.createQuery("from CashDrawer where employee=:emp and date=:date")
					                           .setParameter("emp", emp)
					                           .setParameter("date", new Date(System.currentTimeMillis()))
					                           .uniqueResult();
			cd.setEndBalance(cd.getStartBalance()+endBalance);
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}


}
