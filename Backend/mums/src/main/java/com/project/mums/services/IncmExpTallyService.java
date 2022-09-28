package com.project.mums.services;

import java.util.List;
import java.util.Map;

import com.project.mums.payload.IncmExpTallyDto;

public interface IncmExpTallyService {

	List<IncmExpTallyDto> getAll();
	List<IncmExpTallyDto> getAllByMonthYear(int year, int month);
	List<IncmExpTallyDto> getPast5MonthsIncome();
	List<IncmExpTallyDto> calculateMonthlyExp(Map<Integer, Integer> data);
	
}
