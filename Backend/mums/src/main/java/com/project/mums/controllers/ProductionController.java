package com.project.mums.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.ProductionDto;
import com.project.mums.services.ProductionService;

@RestController
@RequestMapping("/production")
public class ProductionController {

	
	@Autowired
	public ProductionService ProductionService;
	
	
	
	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<ProductionDto>> getAllByMonthYear(@PathVariable int year ,@PathVariable int month){
		return ResponseEntity.ok(this.ProductionService.getAllByMonthYear(year,month));	
	}
	
	
	@GetMapping("")
	public ResponseEntity<List<ProductionDto>> getAllProduction(){
		return ResponseEntity.ok(this.ProductionService.getAllProduction());
		
	}
}
