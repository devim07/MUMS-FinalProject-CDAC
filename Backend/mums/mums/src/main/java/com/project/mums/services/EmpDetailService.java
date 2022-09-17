package com.project.mums.services;

import java.util.List;

import com.project.mums.payload.EmpDetailDto;

public interface EmpDetailService {
	EmpDetailDto createEmpDetail (EmpDetailDto empDetailDto, String Empno) ;
	EmpDetailDto updateEmpDetail (EmpDetailDto empDetailDto, String empno) ;
	EmpDetailDto getEmpDetailById (String empno) ;
	List<EmpDetailDto> getAllEmpDetails();
	void deleteEmpDetail (String Empno);
}
