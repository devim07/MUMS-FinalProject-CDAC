package com.project.mums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.mums.entities.Production;

public interface ProductionRepo extends JpaRepository<Production, Integer>{
	
	 @Query(value = "SELECT * FROM PROD WHERE YEAR(DATE(PRO_DATE))=?1 AND MONTH(DATE(PRO_DATE)) =?2", nativeQuery = true)
	 public List<Production> getAllByMonthYearFromDb(int year, int month );
	 
	 
	 
}
