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

import com.itacademy.capsulecorp.models.Employee;
import com.itacademy.capsulecorp.services.EmployeeService;
import com.itacademy.capsulecorp.services.UploadFileService;
import com.itacademy.capsulecorp.utils.PageRender;

@Controller
@SessionAttributes("employee")
public class EmployeeController 
{
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UploadFileService uploadFileService;

	// -------------------- -------------------- //
	
	@GetMapping(value="/employee-uploads/{fileName:.+}")
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

	@GetMapping(value="/employee-view/{id}")
	String viewEmployee(@PathVariable("id") Long idEmployee, Map<String,Object> model, RedirectAttributes flash) 
	{
		Employee employee = employeeService.findOne(idEmployee);
		
		if (employee==null) 
		{
			flash.addFlashAttribute("error", "Employee doesn't exist in the database");
			return "redirect:/employee-list";
		}
		
		model.put("employee", employee);
		
		model.put("title", "Detail employee: " + employee.getName());
		
		return "employee-view";
	}

	@RequestMapping(value="/employee-list", method=RequestMethod.GET)
	public String list(@RequestParam(name="page", defaultValue="0") int page, Model model) 
	{
		Pageable pageRequest = PageRequest.of(page,4);
		
		Page<Employee> employees = employeeService.findAll(pageRequest);

		PageRender<Employee> pageRender = new PageRender<Employee>("/employee-list", employees);
		
		model.addAttribute("title", "Employee's List");
		
		model.addAttribute("employees", employees);
		
		model.addAttribute("page", pageRender);
		
		return "employee-list";
	}

	// -------------------- -------------------- //
	
	@RequestMapping(value="/employee-form")
	public String create(Map<String, Object> model) 
	{
		Employee employee = new Employee();
		
		model.put("employee", employee);
		
		model.put("title", "Employee Form");
		
		return "employee-form";
	}
	
	@RequestMapping(value="/employee-form", method=RequestMethod.POST)
	public String save(@Valid Employee employee, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) 
	{
		if (result.hasErrors()) 
		{
			model.addAttribute("title", "Employee Form");
			return "employee-form";
		}

		if (!photo.isEmpty()) 
		{
			if (employee.getId()!=null && employee.getId()>0 && employee.getPhoto()!=null
					&& employee.getPhoto().length()>0) 
			{
				uploadFileService.delete(employee.getPhoto());
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

			employee.setPhoto(uniqueFilename);
		}

		String mensajeFlash = (employee.getId()!=null)?"Employee edited":"Employee created";

		employeeService.save(employee);
		
		status.setComplete();
		
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:employee-list";
	}
	
	// -------------------- -------------------- //

	@RequestMapping(value="/employee-form/{id}")
	public String edit(@PathVariable("id") Long idEmployee, Map<String, Object> model, RedirectAttributes flash)
	{
		Employee employee = null;
		
		if (idEmployee>0) 
		{
			employee = employeeService.findOne(idEmployee);
			
			if (employee==null) 
			{
				flash.addFlashAttribute("error", "ID doesn't exist in the database");
				
				return "redirect:/employee-list";
			}
		} 
		
		else 
		{
			flash.addFlashAttribute("error", "ID can't be 0");
			return "redirect:/employee-list";
		}
		
		model.put("employee", employee);
		
		model.put("title", "Edit Title");
		
		return "employee-form";
	}
	
	// -------------------- -------------------- //
	
	@RequestMapping(value="/employee-delete/{id}")
	public String delete(@PathVariable("id") Long idEmployee, RedirectAttributes flash) 
	{
		if (idEmployee>0) 
		{
			Employee employee = employeeService.findOne(idEmployee);
			employeeService.delete(idEmployee);
			flash.addFlashAttribute("success", "Employee deleted");

			if (uploadFileService.delete(employee.getPhoto())) 
			{
				flash.addFlashAttribute("info", "Photo " + employee.getPhoto() + " deleted");
			}
		}
		
		return "redirect:/employee-list";
	}
	
}