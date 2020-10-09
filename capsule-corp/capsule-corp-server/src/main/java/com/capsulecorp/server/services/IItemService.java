package com.capsulecorp.server.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.capsulecorp.server.dto.Item;

public interface IItemService {
	
	//create
	public Item newItem(Item item) throws Exception;
	
	//read
	public List<Item> getAll();
	
	//update
	public Item editItem(Item item) throws Exception;
	
	//delete
	public void deleteItem(UUID id);
	
	//checking exist algorithm
	public boolean itemExist(Item item); //done comparingByUUID
	
	public Optional<Item> itemById(UUID id);

}
