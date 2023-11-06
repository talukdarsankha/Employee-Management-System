package com.xyz.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xyz.entity.Department;
import com.xyz.entity.Employee;
import com.xyz.repository.EmpRepo;
import com.xyz.service.DepartmentService;
import com.xyz.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private EmployeeService es;
	@Autowired
	private DepartmentService ds;
	
	@GetMapping(path = "/")
	public String getIndex(Model md) {
		List<Employee> ll= es.getAllEmp();
		md.addAttribute("ll", ll);
		return "index";
	}
	
	@GetMapping(path = "/editEmp/{id}")
	public String getEdit(@PathVariable("id") Long id,Model md) {
		System.out.println(id);
		Employee ee = es.getEmpById(id);
		md.addAttribute("emp", ee);
		return "editPage";
	}
	
	@GetMapping(path = "/addEmp")
	public String getAdd() {
		return "addPage";
	}
	
	
	
	@PostMapping(path = "/saveEMP")
	public String addStu(@ModelAttribute Employee ee,HttpSession hs,@RequestParam("dptId") String di) {
		System.out.println(ee);
		 Timestamp instant= Timestamp.from(Instant.now());  
		ee.setCreatedAt(instant);
		ee.setUpdatedAt(instant);
		Department dpt = ds.getDptById(di);
		ee.setDepartmentId(dpt);
		
		Employee emp= es.save(ee);
		if(emp!=null) {
			hs.setAttribute("msg", "Employee Added Successfully....");
		}else {
			hs.setAttribute("msg", "Something Went Wrong On Server!!!");
		}
		
		return "redirect:/addEmp";
	}
	
	@PostMapping(path = "/updateDetails")
	public String UpdateEmp(@ModelAttribute Employee ee,HttpSession hs,@RequestParam("dptId") String di) {
		ee.setUpdatedAt(Timestamp.from(Instant.now()));
		ee.setCreatedAt(Timestamp.from(Instant.now()));
		Department dpt= ds.getDptById(di);
		ee.setDepartmentId(dpt);
		Employee emp= es.save(ee);
		if(emp!=null) {
			hs.setAttribute("msg", "Employee updated Successfully...");
		}else {
			hs.setAttribute("msg", "Something Went Wrong On Server!!!");
		}
		return "redirect:/";
	}
	
	@GetMapping(path = "/deleteEmp/{id}")
	public String deleteEmp(@PathVariable("id") Long id, HttpSession hs) {
		
		boolean f= es.delete(id);
		if(f) {
			hs.setAttribute("msg", "Employee Deleted Successfully...");
		}else {
			hs.setAttribute("msg", "Something Went Wrong On Server!!!");
		}
		return "redirect:/";
	}
	
	@GetMapping(path = "/dap")
	public String getDap() {
		return "DepartmentAddPage";
	}
	@GetMapping(path = "/dep/{id}")
	public String getDep(@PathVariable("id") String di,Model md) {
		Department dt = ds.getDptById(di);
		md.addAttribute("dt", dt);
		return "DepartmentEditPage";
	}
	
	@GetMapping(path = "/dip")
	public String getDip(Model md) {
		 List<Department> ll= ds.getAllDpt();
		 md.addAttribute("ll", ll);
		return "DepartmentIndexPage";
	}
	
	@GetMapping(path = "/deleteDpt/{id}")
	public String getDip(@PathVariable("id") String di,HttpSession hs) {
		boolean f= ds.delete(di);
		if(f){
			hs.setAttribute("msg", "Department Deleted Successfully.........");
			
		}else {
			hs.setAttribute("msg", "Something Went Wrong On Server!!!!");
		}
		return "redirect:/dip";
	}
	
		
	
	@PostMapping(path = "/saveDPT")
	public String svDpt(@ModelAttribute Department dt,HttpSession hs) {
		dt.setCreatedAt(Timestamp.from(Instant.now()));
		dt.setUpdatedAt(Timestamp.from(Instant.now()));
		Department dpt= ds.saveDpt(dt);
		if(dpt!=null) {
			hs.setAttribute("msg", "Department Added Successfully.........");
			
		}else {
			hs.setAttribute("msg", "Something Went Wrong On Server!!!!");
		}
		return "redirect:/dap";
	}
	
	@PostMapping(path = "/updateDtp")
	public String getUpdate(@ModelAttribute Department dt,HttpSession hs) {
		dt.setCreatedAt(Timestamp.from(Instant.now()));
		dt.setUpdatedAt(Timestamp.from(Instant.now()));
		Department dpt= ds.saveDpt(dt);
		if(dpt!=null) {
			hs.setAttribute("msg", "Department  updated Successfully...");
		}else {
			hs.setAttribute("msg", "Something Went Wrong On Server!!!");
		}
		return "redirect:/dip";
	}
	
	
	
}
