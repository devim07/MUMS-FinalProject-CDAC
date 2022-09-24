package com.project.mums.controllers;

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

import com.project.mums.payload.LoginDto;
import com.project.mums.services.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:3000")
public class LoginController {
	
	@Autowired
	public LoginService loginService;
	
	
	
//	@GetMapping("")
//	public ResponseEntity<List<LoginDto>> getAlllogin(){
//		return ResponseEntity.ok(this.loginService.getAllLogins());
//	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<LoginDto> getloginById(@PathVariable String id){
		return ResponseEntity.ok(this.loginService.getLoginById(id));
	}
	
	
	
	@PostMapping( "/")
	public ResponseEntity<LoginDto> createlogin(@Valid @RequestBody LoginDto loginDto){
		LoginDto createdlogin=this.loginService.createLogin(loginDto, null);
		return new ResponseEntity<>(createdlogin, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<LoginDto> updatelogin(@Valid @RequestBody LoginDto loginDto, @PathVariable String id){
		return ResponseEntity.ok(this.loginService.updateLogin(loginDto, id));
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletelogin(@PathVariable String id){
		this.loginService.deleteLogin(id);
		return ResponseEntity.ok(Map.of("message", "Login details deleted successfully"));
	}

}
