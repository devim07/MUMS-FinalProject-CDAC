package com.project.mums.services;

import java.util.List;

import com.project.mums.payload.DeptDto;

public interface DeptService {

	DeptDto createDept (DeptDto deptDto);
	List<DeptDto> getAllDepts();
}
