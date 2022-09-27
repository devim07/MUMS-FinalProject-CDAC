package com.project.mums.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import com.project.mums.entities.Order;

public interface OrderRepo extends JpaRepository<Order,Integer>{
	@Query (value="SELECT * FROM ORDERS WHERE CNUM= ?1 ORDER BY ORDER_DATE DESC",nativeQuery=true)
	public List<Order> findByCustno (int custno);
	
	@Query (value="SELECT * FROM ORDERS WHERE SNUM= ?1 ORDER BY ORDER_DATE DESC",nativeQuery=true)
	public List<Order> findBySalesno (String salesno);
	
	@Procedure (value="NEW_ORDER_ENTRY")
	public int newOrdeEntry(int custno, int orderUnit, Date orderDate);
	
	@Query(value="SELECT SUM(ORDER_UNIT) FROM ORDERS WHERE SNUM= ?1", nativeQuery=true)
	public int getAllOrderOfSalesmanFromDb(String snum);
}
