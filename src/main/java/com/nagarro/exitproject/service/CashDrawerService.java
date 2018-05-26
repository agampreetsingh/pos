package com.nagarro.exitproject.service;



import java.sql.Date;

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
	public CashDrawer getDrawer(String id) {
		CashDrawer cd =  this.cashDrawerDao.getDrawer(Integer.parseInt(id),
				new Date(System.currentTimeMillis()));
		return cd;
		
	}	

	@Transactional
	public boolean createNewEntry(String eid, int cash) {
        return this.cashDrawerDao.createNewEntry(Integer.parseInt(eid), cash);
	}

	@Transactional
	public boolean updateCash(String eid, int cash) {
		return this.cashDrawerDao.updateCash(Integer.parseInt(eid), cash);
	}

}
