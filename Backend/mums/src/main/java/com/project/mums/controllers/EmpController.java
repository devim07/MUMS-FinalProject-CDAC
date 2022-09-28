package com.project.mums.controllers;

import java.util.List;
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

import com.project.mums.payload.EmpDto;
import com.project.mums.services.EmpService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type")
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
	public ResponseEntity<EmpDto> createEmp(@Valid @RequestBody EmpDto empDto){
		EmpDto createdEmp=this.empService.createEmp(empDto);
		return new ResponseEntity<>(createdEmp, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<EmpDto> updateEmp(@Valid @RequestBody EmpDto empDto, @PathVariable String id){
		return ResponseEntity.ok(this.empService.updateEmp(empDto, id));
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable String id){
		this.empService.deleteEmp(id);
		return ResponseEntity.ok(Map.of("message", "Employee deleted successfully"));
	}
	
	
	@GetMapping("/dept/{deptNo}")
	public ResponseEntity<List<EmpDto>> getEmpByDeptno(@PathVariable String deptNo){
		return ResponseEntity.ok(this.empService.getEmpByDeptno(deptNo));
	}
	
	
	@PutMapping("/holiday/{id}/{days}")
	public ResponseEntity<EmpDto> updateEmpHoliday(@Valid @RequestBody EmpDto empDto, @PathVariable String id, @PathVariable int days){
		return ResponseEntity.ok(this.empService.updateEmpHoliday(empDto, id, days));
	}

}