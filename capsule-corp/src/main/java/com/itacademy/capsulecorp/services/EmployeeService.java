package com.itacademy.capsulecorp.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itacademy.capsulecorp.models.Employee;

public interface EmployeeService 
{
	public List<Employee> findAll();
	public Page<Employee> findAll(Pageable pageable);
	public Employee findOne(Long id);
	public void save(Employee employee);
	public void delete(Long id);
}