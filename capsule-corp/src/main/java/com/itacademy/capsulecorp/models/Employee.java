package com.itacademy.capsulecorp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Employees")
public class Employee implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idEmployee;

	@NotEmpty
	private String name;

	@NotEmpty
	private String lastName;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String username;

	@NotEmpty
	@JsonIgnore
	private String password;

	@NotNull
	@Column(name = "createDate")
	@Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Invoice> invoices;
	
	private String photo;
		
	// ---------------------------------------- //
	
	public Employee()
	{
		
	}

	// ---------------------------------------- //
	
	public Long getId() 
	{
		return idEmployee;
	}

	public void setId(Long idEmployee) 
	{
		this.idEmployee = idEmployee;
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

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}

	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public List<Invoice> getInvoices() 
	{
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) 
	{
		this.invoices = invoices;
	}

	public String getPhoto() 
	{
		return photo;
	}

	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
}