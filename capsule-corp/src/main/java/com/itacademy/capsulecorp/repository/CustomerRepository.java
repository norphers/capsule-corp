package com.itacademy.capsulecorp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.itacademy.capsulecorp.models.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>
{
	
}