package com.project.mums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mums.entities.Order;

public interface OrderRepo extends JpaRepository<Order,Integer>{
	
}

