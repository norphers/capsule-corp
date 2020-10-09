package com.itacademy.capsulecorp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.itacademy.capsulecorp.models.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice,Long> 
{
	@Query("select f from Invoice f join fetch f.customer c join fetch f.items l join fetch l.product where f.id=?1")
	public Invoice fetchByIdWithCustomerWhithItemInvoiceWithProduct(Long id);	
}