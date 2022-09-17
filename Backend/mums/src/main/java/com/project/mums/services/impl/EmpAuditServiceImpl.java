package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.EmpAudit;
import com.project.mums.payload.EmpAuditDto;
import com.project.mums.repository.EmpAuditRepo;
import com.project.mums.services.EmpAuditService;

@Service
public class EmpAuditServiceImpl implements EmpAuditService {

	@Autowired
	private EmpAuditRepo empAuditRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<EmpAuditDto> getAllEmpAudits(){
		List<EmpAudit> empAudits=this.empAuditRepo.getFullAuditDetails();
		List<EmpAuditDto> empAuditDtos =empAudits.stream().map(empAudit->this.empAuditToDto(empAudit)).collect(Collectors.toList());
				return empAuditDtos;
	}
	
	/*
	private EmpAudit dtoToEmpAudit(EmpAuditDto empAuditDto) {
		EmpAudit empAudit = this.modelMapper.map(empAuditDto, EmpAudit.class);
		return empAudit;
	} */
	
	private EmpAuditDto empAuditToDto(EmpAudit empAudit) {
		EmpAuditDto empAuditDto = this.modelMapper.map(empAudit, EmpAuditDto.class);
		return empAuditDto;
	}
	
}