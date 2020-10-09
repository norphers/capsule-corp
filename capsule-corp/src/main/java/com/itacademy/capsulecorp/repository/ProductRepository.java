package com.itacademy.capsulecorp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.itacademy.capsulecorp.models.Product;

public interface ProductRepository extends CrudRepository<Product,Long>
{	 
	@Query("select p from Product p where p.name like %?1%")
	public List<Product> findByName(String term);
	public List<Product> findByNameLikeIgnoreCase(String term);
}