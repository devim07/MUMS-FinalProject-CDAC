package com.project.mums.services;

import java.util.List;

import com.project.mums.payload.ProductionDto;

public interface ProductionService {
	
	List<ProductionDto> getAllProduction();		
	List<ProductionDto> getAllByMonthYear(int year ,int month);
	
	
}