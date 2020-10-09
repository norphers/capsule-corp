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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customer")
public class Customer implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idCustomer;

	@NotEmpty
	private String name;

	@NotEmpty
	private String lastName;

	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Column(name = "createDate")
	@Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Invoice> invoices;

	private String photo;

	// -------------------- -------------------- //
	
	public Customer() 
	{
		invoices = new ArrayList<Invoice>();
	}

	// -------------------- -------------------- //
	
	public Long getId() 
	{
		return idCustomer;
	}

	public void setId(Long idCustomer) 
	{
		this.idCustomer = idCustomer;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}

	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public String getPhoto() 
	{
		return photo;
	}

	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}

	public List<Invoice> getInvoices() 
	{
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) 
	{
		this.invoices=invoices;
	}

	public void addInvoice(Invoice invoice) 
	{
		invoices.add(invoice);
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
	@Override
	public String toString() 
	{
		return name+" "+lastName;
	}

}