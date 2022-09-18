package com.project.mums.services;

import java.util.List;

import com.project.mums.payload.OldSalaryDto;

public interface OldSalaryService {

	List<OldSalaryDto> getOldSalaryById (String empno);
//	List<OldSalaryDto> getAllOldSalarys();
	List<OldSalaryDto> getAllByMonthYear(int year, int month);
	OldSalaryDto getParticularOldSalaryById(int year, int month, String id);
}
