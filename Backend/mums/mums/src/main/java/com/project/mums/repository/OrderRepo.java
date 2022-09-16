package com.project.mums.repository;

import com.project.mums.entities.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer>{
	
}
