package com.project.mums.services;

import java.util.List;

import com.project.mums.payload.EmpDto;

public interface EmpService {
	EmpDto createEmp (EmpDto empDto, String id) ;
	EmpDto updateEmp (EmpDto empDto, String empno) ;
	EmpDto getEmpById (String empno) ;
	List<EmpDto> getAllEmps();
	void deleteEmp (String empno);
}
