package com.nagarro.exitproject.service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.CashDrawerDaoImpl;
import com.nagarro.exitproject.model.CashDrawer;

@Service
public class CashDrawerService {
	
	@Autowired
	private CashDrawerDaoImpl cashDrawerDao;

	@Transactional
	public CashDrawer getDrawer(int eid) {
		CashDrawer cd =  this.cashDrawerDao.getDrawer(eid);
//		Date date = new Date();
//		if(date.compareTo(cd.getDate()) == 1) {  
//			
//		}
		return cd;
		
	}
	
	@Transactional
	public CashDrawer setStartBalance(int balance, int id) {
		return this.cashDrawerDao.setStartBalance(balance, id);
	}
	
	@Transactional
	public int setEndBalance(int balance, int id) {
		return this.cashDrawerDao.setEndBalance(balance, id);
	}

}
