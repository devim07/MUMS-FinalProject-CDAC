package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.EmpDetail;
import com.project.mums.exceptions.EmpDetailNotFoundException;
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
	public EmpDetailDto createEmpDetail(EmpDetailDto EmpDetailDto) {
		EmpDetail EmpDetail=this.dtoToEmpDetail(EmpDetailDto);
		EmpDetail savedEmpDetail=this.EmpDetailRepo.save(EmpDetail);
		return EmpDetailToDto(savedEmpDetail);
	}

	
	
	@Override
	public EmpDetailDto updateEmpDetail(EmpDetailDto EmpDetailDto, String Empno) {
		EmpDetail EmpDetail=this.EmpDetailRepo.findById(Empno)
				.orElseThrow(()->
				new EmpDetailNotFoundException("Employee", "Employee ID", Empno)); 
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
	public EmpDetailDto getEmpDetailById(String EmpDetailno) {
		EmpDetail EmpDetail=this.EmpDetailRepo.findById(EmpDetailno)
				.orElseThrow(()->
				new EmpDetailNotFoundException("EmpDetailloyee", "EmpDetailloyee ID", EmpDetailno)); 
		return EmpDetailToDto(EmpDetail);
	}

	
	
	@Override
	public List<EmpDetailDto> getAllEmpDetails() {
		List<EmpDetail> EmpDetails = this.EmpDetailRepo.findAll();
		List<EmpDetailDto> EmpDetailDtos = EmpDetails.stream().map(EmpDetail->this.EmpDetailToDto(EmpDetail)).collect(Collectors.toList());
		return EmpDetailDtos;
	}

	
	
	@Override
	public void deleteEmpDetail(String empno) {
		EmpDetail EmpDetail = this.EmpDetailRepo.findById(empno)
		.orElseThrow(()->
		new EmpDetailNotFoundException("Employee", "Employee ID", empno));
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
