package com.project.mums.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.CustDto;
import com.project.mums.services.CustService;

@RestController
@RequestMapping("/customer")
public class CustController {

	@Autowired
	public CustService custService;

	@GetMapping("")
	public ResponseEntity<List<CustDto>> getAllCust(){
		return ResponseEntity.ok(this.custService.getAllCusts());
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CustDto> getCustById (@PathVariable int id){
	return ResponseEntity.ok(this.custService.getCustById(id));
	}


	
	@PostMapping("/")
	public ResponseEntity<CustDto> createCust(@Valid @RequestBody CustDto custDto){
		CustDto createdCust=this.custService.createCust(custDto);
		return new ResponseEntity<>(createdCust,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustDto> updateCust(@Valid @RequestBody CustDto custDto,@PathVariable int id){
			return ResponseEntity.ok(this.custService.updateCust(custDto,id));
	}
	

}
