package com.itacademy.capsulecorp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")
public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idProduct;

	private String name;

	private double price;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	private Date createAt; //onCreate --> buscar standard nomenclaturas inf
	
	@OneToMany(mappedBy="product")
	@JsonIgnore
    private Set<ItemInvoice> itemInvoices;

	// ---------------------------------------- //

	@PrePersist
	public void prePersist() 
	{
		createAt = new Date();
	}
	
	// ---------------------------------------- //

	public Long getId() 
	{
		return idProduct;
	}

	public void setId(Long idProduct) 
	{
		this.idProduct = idProduct;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Double getPrice() 
	{
		return price;
	}

	public void setPrice(Double price) 
	{
		this.price = price;
	}

	public Date getCreateAt() 
	{
		return createAt;
	}

	public void setCreateAt(Date createAt) 
	{
		this.createAt = createAt;
	}

}