package com.project.mums.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.Emp;
import com.project.mums.exceptions.IdMisMatchException;
import com.project.mums.exceptions.PrimaryKeyViolationException;
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
	public EmpDto createEmp(EmpDto empDto){
		Emp emp=this.dtoToEmp(empDto);
		if(this.empRepo.existsById(emp.getEmpno()))
			throw new PrimaryKeyViolationException("Employee", emp.getEmpno());
		Emp savedEmp=this.empRepo.save(emp);
		return empToDto(savedEmp);
	}

	
	
	@Override
	public EmpDto updateEmp(EmpDto empDto, String empno) {
		if (!(empno).toUpperCase().equals(empDto.getEmpno()))
			throw new IdMisMatchException(empno,empDto.getEmpno());
		Emp emp=this.empRepo.findById(empno)
				.orElseThrow(()-> new ResourceNotFoundException("Employee", "Employee ID", empno));
		emp=dtoToEmp(empDto);
		return empToDto(this.empRepo.save(emp));
	}



	@Override
	public EmpDto updateEmpHoliday(@Valid EmpDto empDto, String empno, int days) {
		if (!(empno).toUpperCase().equals(empDto.getEmpno()))
			throw new IdMisMatchException(empno,empDto.getEmpno());
		Emp emp=this.empRepo.findById(empno)
				.orElseThrow(()-> new ResourceNotFoundException("Employee", "Employee ID", empno));
		emp.setHolidays(emp.getHolidays()+days);
		return empToDto(this.empRepo.save(emp));
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



	@Override
	public EmpDto setPhoto(String empno, String fileName) {
		Emp emp=this.empRepo.findById(empno).orElseThrow(()->
		new ResourceNotFoundException("Employee", "Employee ID", empno)); 
		emp.setPhoto(fileName);
		Emp savedEmp=this.empRepo.save(emp);
		return empToDto(savedEmp);
	}



	@Override
	public void calculateTotSalary() {
		this.empRepo.calculateSalaryInDb(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
	}
	
	
	@Override
	public List<EmpDto> getEmpByDeptno(String deptNo) {
		List<Emp> allByDeptno = this.empRepo.findAllByDeptno(deptNo);
		List<EmpDto> empDtos = allByDeptno.stream().map(emp->this.empToDto(emp)).collect(Collectors.toList());
		return empDtos;
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