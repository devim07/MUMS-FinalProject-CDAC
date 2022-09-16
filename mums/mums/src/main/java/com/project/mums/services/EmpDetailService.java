package com.project.mums.services;

import com.project.mums.payload.EmpDetailDto;

public interface EmpDetailService {
	EmpDetailDto createEmpDetail (EmpDetailDto empDetailDto, String id) ;
	EmpDetailDto updateEmpDetail (EmpDetailDto empDetailDto, String empno) ;
	EmpDetailDto getEmpDetailById (String empno) ;
//	List<EmpDetailDto> getAllEmpDetails();
	void deleteEmpDetail (String Empno);
}
