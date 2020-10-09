package com.capsulecorp.server.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capsulecorp.server.dto.Item;

public interface IItemDAO extends JpaRepository<Item, UUID> {

}
