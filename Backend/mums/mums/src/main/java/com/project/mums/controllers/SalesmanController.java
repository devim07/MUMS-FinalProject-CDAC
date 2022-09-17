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

import com.project.mums.payload.SalesmanDto;
import com.project.mums.services.SalesmanService;



	
	@RestController
	@RequestMapping("/salesman")
	public class SalesmanController {
		
		@Autowired
		public SalesmanService salesmanService;
		
		
		
		@GetMapping("")
		public ResponseEntity<List<SalesmanDto>> getAllSalesman(){
			return ResponseEntity.ok(this.salesmanService.getAllSalesman());
		}
		
		
		
		@GetMapping("/{id}")
		public ResponseEntity<SalesmanDto> getSalesmanById(@PathVariable String id){
			return ResponseEntity.ok(this.salesmanService.getSalesmanBy(id));
		}
		
		
		
		@PostMapping( "/")
		public ResponseEntity<SalesmanDto> createSalesman(@RequestBody SalesmanDto salesmanDto){
			SalesmanDto createdSalesman=this.salesmanService.createSalesman(salesmanDto);
			return new ResponseEntity<>(createdSalesman, HttpStatus.CREATED);
		}
		
		
		
		@PutMapping("/{id}")
		public ResponseEntity<SalesmanDto> updateSalesman(@RequestBody SalesmanDto salesmanDto, @PathVariable String id){
			return ResponseEntity.ok(this.salesmanService.updateSalesman(salesmanDto, id));
		}
		
		
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deleteSalesman(@PathVariable String id){
			this.salesmanService.deleteSalesman(id);
			return ResponseEntity.ok(Map.of("message", "Salesman deleted successfully"));
		}

	}



