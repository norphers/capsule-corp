package com.itacademy.capsulecorp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.itacademy.capsulecorp.models.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> 
{

}
