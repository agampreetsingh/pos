package com.nagarro.exitproject.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exitproject.daoImpl.EmployeeDaoImpl;
import com.nagarro.exitproject.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDaoImpl employeeDao;

	@Transactional
	public Employee authenticate(Employee employee) {
		return this.employeeDao.authenticate(employee.getName(), employee.getPassword());		
	}

}
