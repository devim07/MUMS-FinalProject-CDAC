package com.project.mums.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.OldSalaryDto;
import com.project.mums.services.OldSalaryService;

@RestController
@RequestMapping("/oldsalary")
@CrossOrigin("http://localhost:3000")
public class OldSalaryController {

	@Autowired
	public OldSalaryService oldSalaryService;
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<List<OldSalaryDto>> getOldSalaryById (@PathVariable String id){
		return ResponseEntity.ok(this.oldSalaryService.getOldSalaryById(id));
	}

	
	
	@GetMapping("/{id}/{year}/{month}")
	public ResponseEntity<OldSalaryDto> getParticularOldSalaryById (@PathVariable String id, @PathVariable int year, @PathVariable int month){
		return ResponseEntity.ok(this.oldSalaryService.getParticularOldSalaryById(year, month, id));
	}
	
	
	
	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<OldSalaryDto>> getAllByMonthYear(@PathVariable int year, @PathVariable int month){
		return ResponseEntity.ok(this.oldSalaryService.getAllByMonthYear(year, month));
	}
}
