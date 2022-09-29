package com.project.mums.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.mums.payload.CustDto;
import com.project.mums.payload.EmpDto;
import com.project.mums.services.CustService;
import com.project.mums.services.EmpService;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*", allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"}, exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})

public class FileUploadController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private CustService custService;

	
	@PostMapping("employee/{empno}")
	public ResponseEntity<EmpDto> imageUpload(
			@PathVariable String empno,
			@RequestParam MultipartFile image
			){
		String fileName=image.getOriginalFilename();
		fileName=fileName.substring(fileName.lastIndexOf("."));
		fileName=empno+fileName;
		String filePath=servletContext.getRealPath("/images");
		//check if folder exist
		Path path=Paths.get(filePath);
		if(!Files.exists(path)) {
			try {
				Files.createDirectory(path);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		path=path.resolve(path+File.separator+fileName);
		EmpDto updatedEmp=null;
		System.out.println("path==========="+path.toString());
		try {
			Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			updatedEmp = this.empService.setPhoto(empno, fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
	}
	
	

	@PostMapping("customer/{cnum}")
	public ResponseEntity<CustDto> imageUpload(
			@PathVariable int cnum,
			@RequestParam MultipartFile image
			){
		String fileName=image.getOriginalFilename();
		fileName=fileName.substring(fileName.lastIndexOf("."));
		fileName="CUST"+cnum+fileName;
		String filePath=servletContext.getRealPath("/images");
		//check if folder exist
		Path path=Paths.get(filePath);
		if(!Files.exists(path)) {
			try {
				Files.createDirectory(path);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		path=path.resolve(path+File.separator+fileName);
		CustDto updatedCust=null;
		System.out.println("path==========="+path.toString());
		try {
			Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			updatedCust = this.custService.setPhoto(cnum, fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(updatedCust, HttpStatus.OK);
		
	}
	
}
