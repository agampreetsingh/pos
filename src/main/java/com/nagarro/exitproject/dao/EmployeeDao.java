package com.nagarro.exitproject.dao;

import com.nagarro.exitproject.model.Employee;

public interface EmployeeDao {

	public Employee authenticate(String name, String password);
}
