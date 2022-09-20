package com.project.mums.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.EmpDetail;
import com.project.mums.exceptions.IdMisMatchException;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.payload.EmpDetailDto;
import com.project.mums.repository.EmpDetailRepo;
import com.project.mums.services.EmpDetailService;

@Service
public class EmpDetailServiceImpl implements EmpDetailService {

	@Autowired
	private EmpDetailRepo EmpDetailRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public EmpDetailDto createEmpDetail(EmpDetailDto EmpDetailDto, String Empno) {
		if (!Empno.toUpperCase().equals(EmpDetailDto.getEmpno()))
			throw new IdMisMatchException(Empno,EmpDetailDto.getEmpno());
		EmpDetail EmpDetail=this.dtoToEmpDetail(EmpDetailDto);
		EmpDetail savedEmpDetail=this.EmpDetailRepo.save(EmpDetail);
		return empDetailToDto(savedEmpDetail);
	}

	
	
	@Override
	public EmpDetailDto updateEmpDetail(EmpDetailDto empDetailDto, String Empno) {
		if (!Empno.toUpperCase().equals(empDetailDto.getEmpno()))
			throw new IdMisMatchException(Empno,empDetailDto.getEmpno());
		EmpDetail empDetail=this.EmpDetailRepo.findById(Empno)
			.orElseThrow(()->
			new ResourceNotFoundException("Employee", "Employee ID", Empno)); 
		empDetail=dtoToEmpDetail(empDetailDto);
		return empDetailToDto(this.EmpDetailRepo.save(empDetail));
	}
	
	

	@Override
	public EmpDetailDto getEmpDetailById(String empNo) {
		EmpDetail empDetail=this.EmpDetailRepo.findById(empNo)
				.orElseThrow(()->
				new ResourceNotFoundException("EmpDetailloyee", "EmpDetailloyee ID", empNo)); 
		return empDetailToDto(empDetail);
	}

	
	
	@Override
	public void deleteEmpDetail(String empno) {
		EmpDetail EmpDetail = this.EmpDetailRepo.findById(empno)
		.orElseThrow(()->
		new ResourceNotFoundException("Employee", "Employee ID", empno));
		this.EmpDetailRepo.delete(EmpDetail);
	}
	
	
	
	public EmpDetail dtoToEmpDetail(EmpDetailDto EmpDetailDto) {
		EmpDetail EmpDetail=this.modelMapper.map(EmpDetailDto, EmpDetail.class);
		return EmpDetail;
	}
	
	
	
	public EmpDetailDto empDetailToDto(EmpDetail empDetail) {
		EmpDetailDto EmpDetailDto=this.modelMapper.map(empDetail, EmpDetailDto.class);
		return EmpDetailDto;
	}
}