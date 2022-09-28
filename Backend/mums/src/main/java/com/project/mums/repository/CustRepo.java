package com.project.mums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import com.project.mums.entities.Cust;

public interface CustRepo extends JpaRepository<Cust, Integer>{
	public Cust getByMobileNumber (long mobNo);
	
	@Procedure (value="CUST_SALESMAN_ALLOCATION")
	public void custSalesmanAloocation(int custno);
	
	
	@Query(value="SELECT SUM(ORDER_UNIT) FROM ORDERS WHERE CNUM= ?1", nativeQuery=true)
	public int getAllOrderOfCustomerFromDb(int cnum);
}