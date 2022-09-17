package com.project.mums.services;

import java.util.List;

import javax.validation.Valid;

import com.project.mums.payload.DeptDto;

public interface DeptService {

	DeptDto createDept (DeptDto deptDto);
	List<DeptDto> getAllDepts();
	DeptDto updateDept(@Valid DeptDto deptDto, String id);
}