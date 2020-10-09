package com.itacademy.capsulecorp.models;

import java.io.Serializable;

import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="itemInvoice")
public class ItemInvoice implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long idItemInvoice;

	private Integer quantity;

	@ManyToOne
	@JoinColumn(name="idProduct")
	private Product product;
	
	@ManyToOne
	private Invoice invoice;

	// ---------------------------------------- //
	
	public Long getId() 
	{
		return idItemInvoice;
	}

	public void setId(Long idItemInvoice) 
	{
		this.idItemInvoice = idItemInvoice;
	}

	public Integer getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(Integer quantity) 
	{
		this.quantity = quantity;
	}

	public Double calculateImport() 
	{
		return quantity.doubleValue() * product.getPrice();
	}

	public Product getProduct() 
	{
		return product;
	}

	public void setProduct(Product product) 
	{
		this.product = product;
	}
	
}