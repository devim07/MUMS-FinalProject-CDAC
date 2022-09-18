package com.project.mums.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.OldSalaryDto;
import com.project.mums.services.OldSalaryService;

@RestController
@RequestMapping("/oldsalary")
public class OldSalaryController {

	@Autowired
	public OldSalaryService oldSalaryService;
	
	@GetMapping("")
	public ResponseEntity<List<OldSalaryDto>> getAllOldSalary(){
		return ResponseEntity.ok(this.oldSalaryService.getAllOldSalarys());
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<OldSalaryDto> getOldSalaryById (@PathVariable String id){
		return ResponseEntity.ok(this.oldSalaryService.getOldSalaryById(id));
	}

	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<OldSalaryDto>> getAllByMonthYear(int year, int month){
		return ResponseEntity.ok(this.oldSalaryService.getAllByMonthYear(year, month));
	}
}
