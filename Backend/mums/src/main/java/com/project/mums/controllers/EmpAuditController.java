package com.project.mums.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.EmpAuditDto;
import com.project.mums.services.EmpAuditService;

@RestController
@RequestMapping("/employeeAudit")
public class EmpAuditController {

	@Autowired
	public EmpAuditService empAuditService;
	
	
	@GetMapping("")
	public ResponseEntity<List<EmpAuditDto>> getAllEmpAudits(){
		return ResponseEntity.ok(this.empAuditService.getAllEmpAudits());
	}
}