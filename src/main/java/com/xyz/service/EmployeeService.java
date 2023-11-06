package com.xyz.service;

import java.util.List;

import com.xyz.entity.Employee;

public interface EmployeeService {

	public Employee save(Employee ee);
	public List<Employee> getAllEmp();
	public Employee getEmpById(Long id);
	public boolean delete(Long id);
	
}
