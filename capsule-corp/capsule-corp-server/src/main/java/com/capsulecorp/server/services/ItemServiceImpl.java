package com.capsulecorp.server.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.capsulecorp.server.dao.IItemDAO;
import com.capsulecorp.server.dto.Item;

public class ItemServiceImpl implements IItemService {

	@Autowired
	IItemDAO iItemDAO;

	@Override
	public Item newItem(Item item) throws Exception {
		boolean itemExist=itemExist(item);
		if(itemExist) {
			throw new Exception("Item " + item.getId() + " already exist");
		} else {
			return iItemDAO.save(item);
		}
	}

	@Override
	public List<Item> getAll() {
		return iItemDAO.findAll();
	}

	@Override
	public Item editItem(Item item) throws Exception{
		boolean itemExist=itemExist(item);
		if(itemExist) {
			return iItemDAO.save(item);
		} else {
			throw new Exception("Item " + item.getId() + " doesn't exist");
		}
	}

	@Override
	public void deleteItem(UUID id) {
		iItemDAO.deleteById(id);
	}

	@Override
	public boolean itemExist(Item item) {
		boolean result = false;
		List<Item> itemList = iItemDAO.findAll();
		for (Item one : itemList) {
			if (item.getId().equals(one.getId())) {
				return result = true;
			} else {
				return result = false;
			}
		}
		return result;
	}

	@Override
	public Optional<Item> itemById(UUID id) {
		return iItemDAO.findById(id);
	}

}
