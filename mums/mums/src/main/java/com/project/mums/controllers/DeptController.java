package com.project.mums.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.DeptDto;
import com.project.mums.services.DeptService;

@RestController
@RequestMapping("/department")
public class DeptController {


	@Autowired
	public DeptService deptService;

	@GetMapping("")
	public ResponseEntity<List<DeptDto>> getAllDept(){
		return ResponseEntity.ok(this.deptService.getAllDepts());
	}


	
	@PostMapping("/")
	public ResponseEntity<DeptDto> createDept(@Valid @RequestBody DeptDto deptDto){
		DeptDto createdDept=this.deptService.createDept(deptDto);
		return new ResponseEntity<>(createdDept,HttpStatus.CREATED);
	}
}

