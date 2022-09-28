package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.OldSalary;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.payload.OldSalaryDto;
import com.project.mums.repository.OldSalaryRepo;
import com.project.mums.services.OldSalaryService;

@Service
public class OldSalaryServiceImpl implements OldSalaryService {

	@Autowired
	private OldSalaryRepo oldSalaryRepo;
	
	@Autowired
	private ModelMapper modelMapper;



	@Override
	public List<OldSalaryDto> getAll() {
		List<OldSalary> oldSalarys=this.oldSalaryRepo.findAll();
		if(oldSalarys.isEmpty())
			return null;
		List<OldSalaryDto> oldSalaryDtos =oldSalarys.stream().map(oldSalary->this.oldSalaryToDto(oldSalary)).collect(Collectors.toList());
		return oldSalaryDtos;
	}

	
	
	@Override
	public List<OldSalaryDto> getAllByMonthYear(int year, int month) {
		List<OldSalary> oldSalarys=this.oldSalaryRepo.getAllByMonthYearFromDb(year, month);
		if(oldSalarys.isEmpty()) {
			System.out.println(oldSalarys);
			return null;
		}
		List<OldSalaryDto> oldSalaryDtos =oldSalarys.stream().map(oldSalary->this.oldSalaryToDto(oldSalary)).collect(Collectors.toList());
		return oldSalaryDtos;
	}
	

	
	@Override
	public List<OldSalaryDto> getOldSalaryById(String empno) {
		List<OldSalary> oldSalarys=this.oldSalaryRepo.getAllByIdFromDb(empno);
		if(oldSalarys.isEmpty())
			return null;
		List<OldSalaryDto> oldSalaryDtos =oldSalarys.stream().map(oldSalary->this.oldSalaryToDto(oldSalary)).collect(Collectors.toList());
		return oldSalaryDtos;
	}
		
	
	
	@Override
	public OldSalaryDto getParticularOldSalaryById(int year, int month, String id) {
		OldSalary oldSalary=this.oldSalaryRepo.getByIdMonthYearFromDb(year, month, id);
		if(oldSalary==null)
			throw new ResourceNotFoundException("Old Salary Details", "Employee ID", id);
		return oldSalaryToDto(oldSalary);
	}


	
	private OldSalaryDto oldSalaryToDto(OldSalary oldSalary) {
		OldSalaryDto oldSalaryDto = this.modelMapper.map(oldSalary,OldSalaryDto.class);
		return oldSalaryDto;
	}


}
