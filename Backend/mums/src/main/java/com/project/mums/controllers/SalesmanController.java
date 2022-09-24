package com.project.mums.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.SalesmanDto;
import com.project.mums.services.SalesmanService;

	@RestController
	@RequestMapping("/salesman")
	@CrossOrigin("http://localhost:3000")
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
		
		
		
//		@DeleteMapping("/{id}")
//		public ResponseEntity<?> deleteSalesman(@PathVariable String id){
//			this.salesmanService.deleteSalesman(id);
//			return ResponseEntity.ok(Map.of("message", "Salesman deleted successfully"));
//		}

	}



