package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.Emp;
import com.project.mums.exceptions.IdMisMatchException;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.payload.EmpDto;
import com.project.mums.repository.EmpRepo;
import com.project.mums.services.EmpService;


@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepo empRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public EmpDto createEmp(EmpDto empDto) {
		Emp emp=this.dtoToEmp(empDto);
		Emp savedEmp=this.empRepo.save(emp);
		return empToDto(savedEmp);
	}

	
	
	@Override
	public EmpDto updateEmp(EmpDto empDto, String empno) {
		if (!(empno).toUpperCase().equals(empDto.getEmpno()))
			throw new IdMisMatchException(empno,empDto.getEmpno());
		Emp emp=this.empRepo.findById(empno)
				.orElseThrow(()->
				new ResourceNotFoundException("Employee", "Employee ID", empno)); 
		emp.setBasicSal(empDto.getBasicSal());
		emp.setCity(empDto.getCity());
		emp.setDeptno(empDto.getDeptno());
		emp.setEmail(empDto.getEmail());
		emp.setHiredate(empDto.getHiredate());
		emp.setJob(empDto.getJob());
		Emp updatedEmp=this.empRepo.save(emp);
		return empToDto(updatedEmp);
	}
	
	

	@Override
	public EmpDto getEmpById(String empno) {
		Emp emp=this.empRepo.findById(empno)
				.orElseThrow(()->
				new ResourceNotFoundException("Employee", "Employee ID", empno)); 
		return empToDto(emp);
	}

	
	
	@Override
	public List<EmpDto> getAllEmps() {
		List<Emp> emps = this.empRepo.findAll();
		List<EmpDto> empDtos = emps.stream().map(emp->this.empToDto(emp)).collect(Collectors.toList());
		return empDtos;
	}

	
	
	@Override
	public void deleteEmp(String empno) {
		Emp emp = this.empRepo.findById(empno)
		.orElseThrow(()->
		new ResourceNotFoundException("Employee", "Employee ID", empno));
		this.empRepo.delete(emp);
	}
	
	
	
	public Emp dtoToEmp(EmpDto empDto) {
		Emp emp=this.modelMapper.map(empDto, Emp.class);
		return emp;
	}
	
	
	
	public EmpDto empToDto(Emp emp) {
		EmpDto empDto=this.modelMapper.map(emp, EmpDto.class);
		return empDto;
	}

}
