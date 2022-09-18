package com.project.mums.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.IncmExpTallyDto;
import com.project.mums.services.IncmExpTallyService;

@RestController
@RequestMapping("/incmexptally")
public class IncmExpTallyController {

	@Autowired
	public IncmExpTallyService incmExpTallyService;

	@GetMapping("")
	public ResponseEntity<List<IncmExpTallyDto>> getAll(){
		return ResponseEntity.ok(this.incmExpTallyService.getAll());
	}
	
	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<IncmExpTallyDto>> getAllByMonthYear(int year, int month) {
		return ResponseEntity.ok(this.incmExpTallyService.getAllByMonthYear(year, month));
	}
}
