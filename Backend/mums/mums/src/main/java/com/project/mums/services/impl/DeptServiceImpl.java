package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.Dept;
import com.project.mums.exceptions.IdMisMatchException;
import com.project.mums.payload.DeptDto;
import com.project.mums.repository.DeptRepo;
import com.project.mums.services.DeptService;


@Service
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptRepo deptRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	
	
	@Override
	public DeptDto createDept(DeptDto deptDto) {
		Dept dept = this.dtoToDept(deptDto);
		Dept savedDept = this.deptRepo.save(dept);
		return deptToDto(savedDept) ;
	}
	

	@Override
	public List<DeptDto> getAllDepts() {
		List<Dept> depts=this.deptRepo.findAll();
		List<DeptDto> deptDtos =depts.stream().map(dept->this.deptToDto(dept)).collect(Collectors.toList());
				return deptDtos;
	}

	
	
	@Override
	public DeptDto updateDept(DeptDto deptDto, String id){
		if (!id.toUpperCase().equals(deptDto.getDeptno()))
			throw new IdMisMatchException(id,deptDto.getDeptno());
		Dept dept=this.deptRepo.getById(deptDto.getDeptno());		
		dept.setDeptname(deptDto.getDeptname());
		dept.setManager(deptDto.getManager());
		Dept updatedDept=this.deptRepo.save(dept);
		return deptToDto(updatedDept);
	}
	
	
	private Dept dtoToDept(DeptDto deptDto) {
		Dept dept = this.modelMapper.map(deptDto, Dept.class);
		return dept;
	}
	
	private DeptDto deptToDto(Dept dept) {
		DeptDto deptDto = this.modelMapper.map(dept, DeptDto.class);
		return deptDto;
	}

}