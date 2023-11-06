package com.xyz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xyz.entity.Employee;
import com.xyz.repository.EmpRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmpRepo er;
	
	@Override
	public Employee save(Employee ee) {
		Employee emp= er.save(ee);
		return emp;
	}

	@Override
	public List<Employee> getAllEmp() {
		// TODO Auto-generated method stub
		return er.findAll();
	}

	@Override
	public Employee getEmpById(Long id) {
		// TODO Auto-generated method stub
		return er.findById(id).get();
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		Employee ee = er.findById(id).get();
		if(ee!=null) {
			er.delete(ee);
			return true;
		}
		return false;
	}

	
	public void removeSession() {
		HttpSession hs= ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
	   hs.removeAttribute("msg");
	}
	
	
	
}
