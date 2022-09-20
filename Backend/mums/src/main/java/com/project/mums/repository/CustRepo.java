package com.project.mums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.project.mums.entities.Cust;

public interface CustRepo extends JpaRepository<Cust, Integer>{
	public Cust getByMobileNumber (long mobNo);
	
	@Procedure (value="CUST_SALESMAN_ALLOCATION")
	public void custSalesmanAloocation(int custno);
}