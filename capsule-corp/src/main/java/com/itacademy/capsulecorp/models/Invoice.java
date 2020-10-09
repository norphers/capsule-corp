package com.itacademy.capsulecorp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="invoice")
public class Invoice implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idInvoice;

	@NotEmpty
	private String description;

	private String notes;

	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	private Date createAt;

	@ManyToOne
	@JoinColumn(name="idCustomer")
	private Customer customer;

	@OneToMany(mappedBy="invoice",  cascade = CascadeType.ALL)
	//@JoinColumn(name="idItemInvoice")
	private List<ItemInvoice> items;

	// ---------------------------------------- //
	
	public Invoice() 
	{
		this.items = new ArrayList<ItemInvoice>();
	}

	@PrePersist
	public void prePersist() 
	{
		createAt = new Date();
	}

	// ---------------------------------------- //
	
	public Long getId() 
	{
		return idInvoice;
	}

	public void setId(Long idInvoice) 
	{
		this.idInvoice = idInvoice;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes = notes;
	}

	public Date getCreateAt() 
	{
		return createAt;
	}

	public void setCreateAt(Date createAt) 
	{
		this.createAt = createAt;
	}

	public Customer getCustomer() 
	{
		return customer;
	}

	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}

	public List<ItemInvoice> getItems() 
	{
		return items;
	}

	public void setItems(List<ItemInvoice> items) 
	{
		this.items = items;
	}

	public void addItemInvoice(ItemInvoice item) 
	{
		this.items.add(item);
	}

	public Double getTotal() 
	{
		Double total = 0.0;
		int size = items.size();
		for (int i = 0; i < size; i++) 
		{
			total += items.get(i).calculateImport();
		}
		return total;
	}

}