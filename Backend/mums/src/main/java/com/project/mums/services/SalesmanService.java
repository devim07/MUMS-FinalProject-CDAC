package com.project.mums.services;

import java.util.List;
import com.project.mums.payload.SalesmanDto;

public interface SalesmanService {
//	SalesmanDto createSalesman (SalesmanDto SalesmanDto);
	SalesmanDto updateSalesman (SalesmanDto SalesmanDto, String salesmanno);
	SalesmanDto getSalesmanBy (String salesmanno);
	List<SalesmanDto> getAllSalesman();
	void deleteSalesman (String salesmanno);
	SalesmanDto getSalesmanByID(String salesmanno);
}
