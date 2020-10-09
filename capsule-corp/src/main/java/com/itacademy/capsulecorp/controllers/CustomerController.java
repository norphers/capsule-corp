package com.itacademy.capsulecorp.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itacademy.capsulecorp.models.Customer;
import com.itacademy.capsulecorp.services.CustomerService;
import com.itacademy.capsulecorp.services.UploadFileService;
import com.itacademy.capsulecorp.utils.PageRender;

@Controller
@SessionAttributes("customer")
public class CustomerController 
{
	@Autowired
	private CustomerService customerService;

	@Autowired
	private UploadFileService uploadFileService;

	// -------------------- -------------------- //
	
	@GetMapping(value="/uploads/{fileName:.+}")
	ResponseEntity<Resource> viewPhoto(@PathVariable String filename) 
	{
		Resource resource = null;
		
		try 
		{
			resource = uploadFileService.load(filename);
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+resource.getFilename()+"\"")
				.body(resource);
	}

	@GetMapping(value="/view/{id}")
	String viewCustomer(@PathVariable("id") Long idCustomer, Map<String,Object> model, RedirectAttributes flash) 
	{
		Customer customer = customerService.findOne(idCustomer);
		
		if (customer==null) 
		{
			flash.addFlashAttribute("error", "Customer doesn't exist in the database");
			return "redirect:/list";
		}
		
		model.put("customer", customer);
		
		model.put("title", "Detail customer: " + customer.getName());
		
		return "view";
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@RequestParam(name="page", defaultValue="0") int page, Model model) 
	{
		Pageable pageRequest = PageRequest.of(page,4);
		
		Page<Customer> customers = customerService.findAll(pageRequest);

		PageRender<Customer> pageRender = new PageRender<Customer>("/list", customers);
		
		model.addAttribute("title", "Customer's List");
		
		model.addAttribute("customers", customers);
		
		model.addAttribute("page", pageRender);
		
		return "list";
	}

	// -------------------- -------------------- //
	
	@RequestMapping(value="/form")
	public String create(Map<String, Object> model) 
	{
		Customer customer = new Customer();
		
		model.put("customer", customer);
		
		model.put("title", "Customer Form");
		
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String save(@Valid Customer customer, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) 
	{
		if (result.hasErrors()) 
		{
			model.addAttribute("title", "Customer Form");
			return "form";
		}

		if (!photo.isEmpty()) 
		{
			if (customer.getId()!=null && customer.getId()>0 && customer.getPhoto()!=null
					&& customer.getPhoto().length()>0) 
			{
				uploadFileService.delete(customer.getPhoto());
			}
			
			String uniqueFilename = null;
			
			try 
			{
				uniqueFilename = uploadFileService.copy(photo);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Correct Upload '" + uniqueFilename + "'");

			customer.setPhoto(uniqueFilename);
		}

		String mensajeFlash = (customer.getId()!=null)?"Customer edited":"Customer created";

		customerService.save(customer);
		
		status.setComplete();
		
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:list";
	}
	
	// -------------------- -------------------- //

	@RequestMapping(value="/form/{id}")
	public String edit(@PathVariable("id") Long idCustomer, Map<String, Object> model, RedirectAttributes flash)
	{
		Customer customer = null;
		
		if (idCustomer>0) 
		{
			customer = customerService.findOne(idCustomer);
			
			if (customer==null) 
			{
				flash.addFlashAttribute("error", "ID doesn't exist in the database");
				
				return "redirect:/list";
			}
		} 
		
		else 
		{
			flash.addFlashAttribute("error", "ID can't be 0");
			return "redirect:/list";
		}
		
		model.put("customer", customer);
		
		model.put("title", "Edit Title");
		
		return "form";
	}
	
	// -------------------- -------------------- //
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Long idCustomer, RedirectAttributes flash) 
	{
		if (idCustomer>0) 
		{
			Customer customer = customerService.findOne(idCustomer);
			customerService.delete(idCustomer);
			flash.addFlashAttribute("success", "Customer deleted");

			if (uploadFileService.delete(customer.getPhoto())) 
			{
				flash.addFlashAttribute("info", "Photo " + customer.getPhoto() + " deleted");
			}
		}
		
		return "redirect:/list";
	}
	
}