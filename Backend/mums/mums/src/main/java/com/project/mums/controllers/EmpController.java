package com.project.mums.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.EmpDto;
import com.project.mums.services.EmpService;

@RestController
@RequestMapping("/employee")
public class EmpController {
	
	@Autowired
	public EmpService empService;
	
	
	
	@GetMapping("")
	public ResponseEntity<List<EmpDto>> getAllEmp(){
		return ResponseEntity.ok(this.empService.getAllEmps());
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpDto> getEmpById(@PathVariable String id){
		return ResponseEntity.ok(this.empService.getEmpById(id));
	}
	
	
	
	@PostMapping( "/")
	public ResponseEntity<EmpDto> createEmp(@RequestBody EmpDto empDto){
		EmpDto createdEmp=this.empService.createEmp(empDto);
		return new ResponseEntity<>(createdEmp, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<EmpDto> updateEmp(@RequestBody EmpDto empDto, @PathVariable String id){
		return ResponseEntity.ok(this.empService.updateEmp(empDto, id));
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable String id){
		this.empService.deleteEmp(id);
		return ResponseEntity.ok(Map.of("message", "Employee deleted successfully"));
	}

}
