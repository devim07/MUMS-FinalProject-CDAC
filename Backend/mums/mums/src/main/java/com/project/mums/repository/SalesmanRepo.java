package com.project.mums.repository;

import com.project.mums.entities.Salesman;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesmanRepo extends JpaRepository<Salesman,String>{
	
}
