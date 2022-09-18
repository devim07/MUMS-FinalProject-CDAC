package com.project.mums.services;

import java.util.List;

import com.project.mums.payload.OldSalaryDto;

public interface OldSalaryService {

	OldSalaryDto getOldSalaryById (String empno);
	List<OldSalaryDto> getAllOldSalarys();
	List<OldSalaryDto> getAllByMonthYear(int year, int month);
}
