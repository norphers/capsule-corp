package com.itacademy.capsulecorp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itacademy.capsulecorp.models.Customer;
import com.itacademy.capsulecorp.models.Invoice;
import com.itacademy.capsulecorp.models.Product;
import com.itacademy.capsulecorp.repository.CustomerRepository;
import com.itacademy.capsulecorp.repository.InvoiceRepository;
import com.itacademy.capsulecorp.repository.ProductRepository;

@Service
public class CustomerServiceImpl implements CustomerService 
{
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() 
	{
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Customer customer) 
	{
		customerRepository.save(customer);
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findOne(Long id) 
	{
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		customerRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Customer> findAll(Pageable pageable) 
	{
		return customerRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findByName(String term) 
	{
		return productRepository.findByNameLikeIgnoreCase("%" + term + "%");
	}

	@Override
	@Transactional
	public void saveInvoice(Invoice invoice) 
	{
		invoiceRepository.save(invoice);
	}

	@Override
	@Transactional(readOnly=true)
	public Product findProductById(Long id) 
	{
		return productRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Invoice findInvoiceById(Long id) {
		return invoiceRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteInvoice(Long id) {
		invoiceRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Invoice fetchInvoiceByIdWithCustomerWhithItemInvoiceWithProduct(Long id) 
	{
		return invoiceRepository.fetchByIdWithCustomerWhithItemInvoiceWithProduct(id);
	}
}