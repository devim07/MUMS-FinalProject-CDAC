package com.project.mums.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.EmpDetailDto;
import com.project.mums.services.EmpDetailService;

@RestController
@RequestMapping("/employee/details/{id}")
@CrossOrigin("http://localhost:3000")
public class EmpDetailController {
	
	

	@Autowired
	public EmpDetailService empDetailService;
	
	
	@GetMapping("")
	public ResponseEntity<EmpDetailDto> getempDetailById(@PathVariable String id){
		return ResponseEntity.ok(this.empDetailService.getEmpDetailById(id));
	}
	
	
	@PostMapping("")
	public ResponseEntity<EmpDetailDto> createempDetail(@Valid @RequestBody EmpDetailDto empDetailDto, @PathVariable String id){
		EmpDetailDto createdempDetail=this.empDetailService.createEmpDetail(empDetailDto, id);
		return new ResponseEntity<>(createdempDetail, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("")
	public ResponseEntity<EmpDetailDto> updateempDetail(@Valid @RequestBody EmpDetailDto empDetailDto, @PathVariable String id){
		return ResponseEntity.ok(this.empDetailService.updateEmpDetail(empDetailDto, id));
	}
	
	
	
	@DeleteMapping("")
	public ResponseEntity<?> deleteempDetail(@PathVariable String id){
		this.empDetailService.deleteEmpDetail(id);
		return ResponseEntity.ok(Map.of("message", "Employee details of Employee ID= "+id+" deleted successfully"));
	}

}


