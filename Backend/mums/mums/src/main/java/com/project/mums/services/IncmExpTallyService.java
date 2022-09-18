package com.project.mums.services;

import java.util.List;

import com.project.mums.payload.IncmExpTallyDto;

public interface IncmExpTallyService {

	List<IncmExpTallyDto> getAll();
	List<IncmExpTallyDto> getAllByMonthYear(int year, int month);
	
}
