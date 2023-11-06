package com.xyz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.entity.Employee;

public interface EmpRepo extends JpaRepository<Employee, Long> {

}
