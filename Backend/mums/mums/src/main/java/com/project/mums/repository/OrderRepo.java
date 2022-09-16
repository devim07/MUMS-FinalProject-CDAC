package com.project.mums.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.project.mums.entities.Order;

public interface OrderRepo extends JpaRepository<Order,Integer>{
	public List<Order> findByCustno (int custno);
	public List<Order> findBySalesno (String salesno);
	
	@Procedure (value="NEW_ORDER_ENTRY")
	public int newOrdeEntry(int custno, int orderUnit, Date orderDate);
}

