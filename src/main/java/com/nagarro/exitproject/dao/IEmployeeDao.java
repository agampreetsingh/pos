package com.nagarro.exitproject.dao;

import com.nagarro.exitproject.model.Employee;

public interface IEmployeeDao {

	public Employee authenticate(String name, String password);
}
