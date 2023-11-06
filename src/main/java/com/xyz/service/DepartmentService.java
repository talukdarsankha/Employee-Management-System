package com.xyz.service;

import java.util.List;

import com.xyz.entity.Department;

public interface DepartmentService {
	
	public Department saveDpt(Department dpt);
	public List<Department> getAllDpt();
	public Department getDptById(String di);
	public boolean delete(String di);

}
