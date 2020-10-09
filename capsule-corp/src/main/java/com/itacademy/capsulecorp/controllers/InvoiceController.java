package com.itacademy.capsulecorp.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itacademy.capsulecorp.models.Customer;
import com.itacademy.capsulecorp.models.Invoice;
import com.itacademy.capsulecorp.models.ItemInvoice;
import com.itacademy.capsulecorp.models.Product;
import com.itacademy.capsulecorp.services.CustomerService;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController 
{
	@Autowired
	private CustomerService customerService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) 
	{
		Invoice invoice = customerService.fetchInvoiceByIdWithCustomerWhithItemInvoiceWithProduct(id); // clienteService.findFacturaById(id);

		if(invoice == null) 
		{
			flash.addFlashAttribute("error", "Invoice doesn't exist in the database");
			return "redirect:/list";
		}
		
		model.addAttribute("invoice", invoice);
		model.addAttribute("title", "Invoice: ".concat(invoice.getDescription()));
		
		return "invoice/view";
	}

	@GetMapping("/form/{customerId}")
	public String create(@PathVariable(value = "customerId") Long customerId, Map<String, Object> model,
			RedirectAttributes flash) 
	{
		Customer customer = customerService.findOne(customerId);

		if (customer == null) 
		{
			flash.addFlashAttribute("error", "Customer doesn't exist in the database");
			return "redirect:/list";
		}

		Invoice invoice = new Invoice();
		invoice.setCustomer(customer);

		model.put("invoice", invoice);
		model.put("title", "Create Invoice");

		return "invoice/form";
	}

	@GetMapping(value = "/upload-products/{term}", produces = { "application/json" })
	public @ResponseBody List<Product> uploadProducts(@PathVariable String term) 
	{
		return customerService.findByName(term);
	}
	
	@PostMapping("/form")
	public String save(@Valid Invoice invoice, 
			BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "quantity[]", required = false) Integer[] quantity, 
			RedirectAttributes flash,
			SessionStatus status) 
	{	
		if (result.hasErrors()) 
		{
			model.addAttribute("title", "Create Invoice");
			return "invoice/form";
		}

		if (itemId == null || itemId.length == 0) 
		{
			model.addAttribute("title", "Create Invoice");
			model.addAttribute("error", "Error: Invoice must have one row at least");
			return "invoice/form";
		}
		
		for (int i = 0; i < itemId.length; i++) 
		{
			Product product = customerService.findProductById(itemId[i]);

			ItemInvoice row = new ItemInvoice();
			row.setQuantity(quantity[i]);
			row.setProduct(product);
			invoice.addItemInvoice(row);

			log.info("ID: " + itemId[i].toString() + ", quantity: " + quantity[i].toString());
		}

		customerService.saveInvoice(invoice);
		status.setComplete();

		flash.addFlashAttribute("success", "Invoice created");

		return "redirect:/view/" + invoice.getCustomer().getId();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) 
	{	
		Invoice invoice = customerService.findInvoiceById(id);
		
		if(invoice != null) {
			customerService.deleteInvoice(id);
			flash.addFlashAttribute("success", "Invoice deleted");
			return "redirect:/view/" + invoice.getCustomer().getId();
		}
		flash.addFlashAttribute("error", "Invoice cannot exist in the Database, it can't be deleted");
		
		return "redirect:/list";
	}

}