package com.xyz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, String> {

}
