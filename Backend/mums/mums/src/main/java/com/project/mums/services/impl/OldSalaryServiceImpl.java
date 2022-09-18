package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.mums.entities.OldSalary;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.payload.OldSalaryDto;
import com.project.mums.repository.OldSalaryRepo;
import com.project.mums.services.OldSalaryService;

public class OldSalaryServiceImpl implements OldSalaryService {

	@Autowired
	private OldSalaryRepo oldSalaryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public List<OldSalaryDto> getAllByMonthYear(int year, int month) {
		List<OldSalary> oldSalarys=this.oldSalaryRepo.getAllByMonthYearFromDb(year, month);
		List<OldSalaryDto> oldSalaryDtos =oldSalarys.stream().map(oldSalary->this.oldSalaryToDto(oldSalary)).collect(Collectors.toList());
	return oldSalaryDtos;

	}
	
	
	@Override
	public OldSalaryDto getOldSalaryById(String empno) {
		OldSalary oldSalary=this.oldSalaryRepo.findById(empno)
				.orElseThrow(()->
				new ResourceNotFoundException("OldSalary","OldSalary ID",((String)empno).toString()));
		return oldSalaryToDto(oldSalary);
	}


	@Override
	public List<OldSalaryDto> getAllOldSalarys() {
		List<OldSalary> oldSalarys=this.oldSalaryRepo.findAll();
		List<OldSalaryDto> oldSalaryDtos =oldSalarys.stream().map(oldSalary->this.oldSalaryToDto(oldSalary)).collect(Collectors.toList());
				return oldSalaryDtos;
		}


	private OldSalaryDto oldSalaryToDto(OldSalary oldSalary) {
		OldSalaryDto oldSalaryDto = this.modelMapper.map(oldSalary,OldSalaryDto.class);
		return oldSalaryDto;
	}
}
