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
		return EmpDetailToDto(savedEmpDetail);
	}

	
	
	@Override
	public EmpDetailDto updateEmpDetail(EmpDetailDto EmpDetailDto, String Empno) {
		if (!Empno.toUpperCase().equals(EmpDetailDto.getEmpno()))
			throw new IdMisMatchException(Empno,EmpDetailDto.getEmpno());
		EmpDetail EmpDetail=this.EmpDetailRepo.findById(Empno)
			.orElseThrow(()->
			new ResourceNotFoundException("Employee", "Employee ID", Empno)); 
		EmpDetail.setEname(EmpDetailDto.getEname());
		EmpDetail.setMobileNumber(EmpDetailDto.getMobileNumber());
		EmpDetail.setGender(EmpDetailDto.getGender());
		EmpDetail.setAadhar(EmpDetailDto.getAadhar());
		EmpDetail.setDob(EmpDetailDto.getDob());
		EmpDetail.setCity(EmpDetailDto.getCity());
		EmpDetail.setPinCode(EmpDetailDto.getPinCode());
		EmpDetail.setAddress(EmpDetailDto.getAddress());
		EmpDetail updatedEmpDetail=this.EmpDetailRepo.save(EmpDetail);
		return EmpDetailToDto(updatedEmpDetail);
	}
	
	

	@Override
	public EmpDetailDto getEmpDetailById(String empNo) {
		EmpDetail empDetail=this.EmpDetailRepo.findById(empNo)
				.orElseThrow(()->
				new ResourceNotFoundException("EmpDetailloyee", "EmpDetailloyee ID", empNo)); 
		return EmpDetailToDto(empDetail);
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
	
	
	
	public EmpDetailDto EmpDetailToDto(EmpDetail EmpDetail) {
		EmpDetailDto EmpDetailDto=this.modelMapper.map(EmpDetail, EmpDetailDto.class);
		return EmpDetailDto;
	}

}