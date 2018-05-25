package com.nagarro.exitproject.dao;

import java.sql.Date;

import com.nagarro.exitproject.model.CashDrawer;

public interface ICashDrawerDao {
	public CashDrawer getDrawer(int id, Date date);
	public boolean createNewEntry(int employeeId, int startBalance);
	public boolean updateCash(int employeeId, int endBalance);

}
