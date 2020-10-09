package com.itacademy.capsulecorp.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itacademy.capsulecorp.models.Customer;
import com.itacademy.capsulecorp.models.Invoice;
import com.itacademy.capsulecorp.models.Product;

public interface CustomerService 
{
	public List<Customer> findAll();
	public Page<Customer> findAll(Pageable pageable);
	public Customer findOne(Long id);
	public void save(Customer customer);
	public void delete(Long id);
	
	public List<Product> findByName(String term);
	public Product findProductById(Long id);
	
	public Invoice findInvoiceById(Long id);
	public Invoice fetchInvoiceByIdWithCustomerWhithItemInvoiceWithProduct(Long id);
	public void saveInvoice(Invoice invoice);
	public void deleteInvoice(Long id);
}