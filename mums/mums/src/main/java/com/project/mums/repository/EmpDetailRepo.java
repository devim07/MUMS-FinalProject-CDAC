package com.project.mums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mums.entities.EmpDetail;

public interface EmpDetailRepo extends JpaRepository<EmpDetail, String>{
	
}
