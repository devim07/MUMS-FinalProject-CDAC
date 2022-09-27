package com.project.mums.services;

import java.util.List;

import javax.validation.Valid;

import com.project.mums.payload.EmpDto;

public interface EmpService {
	EmpDto createEmp (EmpDto empDto);
	EmpDto updateEmp (EmpDto empDto, String empno) ;
	EmpDto getEmpById (String empno) ;
	List<EmpDto> getAllEmps();
	void deleteEmp (String empno);
	EmpDto setPhoto(String empno, String fileName);
	List<EmpDto>  getEmpByDeptno(String deptNo);
	EmpDto updateEmpHoliday(@Valid EmpDto empDto, String empno, int days);
}