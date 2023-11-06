package com.xyz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.entity.Department;
import com.xyz.repository.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepo dr;
	
	@Override
	public Department saveDpt(Department dpt) {
		Department dt= dr.save(dpt);
		return dt;
	}

	@Override
	public List<Department> getAllDpt() {
		// TODO Auto-generated method stub
		return dr.findAll();
	}

	@Override
	public Department getDptById(String di) {
		
		return  dr.findById(di).get();
	}

	@Override
	public boolean delete(String di) {
		Department dt = dr.findById(di).get();
		if(dt!=null) {
			dr.delete(dt);
			return true;
		}
		return false;
	}

	
	
	
}
