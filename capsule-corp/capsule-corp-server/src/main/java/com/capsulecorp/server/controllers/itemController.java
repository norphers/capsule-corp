package com.capsulecorp.server.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capsulecorp.server.dto.Item;
import com.capsulecorp.server.services.ItemServiceImpl;

@RestController
@RequestMapping("/api")
public class itemController {
	
	@Autowired
	ItemServiceImpl itemServiceImpl;
	
	@PostMapping("/items")
	public Item newItem(@RequestBody Item item) throws Exception {
		return itemServiceImpl.newItem(item);
	}
	
	@GetMapping("/items")
	public List<Item> getAll() {
		return itemServiceImpl.getAll();
	}
	
	@PutMapping("/items/{id}")
	public Item updateItem(@PathVariable(name="id")UUID id, @RequestBody Item item) {
		//comproba si el {id} conicideix amb un id existent, si coincideix,
		//envia les dades del @RequestBody al itemServiceImpl
		return item;
		
		
	}
	
	@DeleteMapping("/items/{id}")
	public void deleteItem(@PathVariable(name="id")UUID id) {
		itemServiceImpl.deleteItem(id);
	}

}
