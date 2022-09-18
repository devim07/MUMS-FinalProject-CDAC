package com.project.mums.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.Login;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.payload.LoginDto;
import com.project.mums.repository.LoginRepo;
import com.project.mums.services.LoginService;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepo LoginRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public LoginDto createLogin(LoginDto LoginDto, String ID) {
		Login Login=this.dtoToLogin(LoginDto);
		Login savedLogin=this.LoginRepo.save(Login);
		return LoginToDto(savedLogin);
	}

	
	
	@Override
	public LoginDto updateLogin(LoginDto LoginDto, String userName) {
		Login Login=this.LoginRepo.findById(userName)
				.orElseThrow(()->
				new ResourceNotFoundException("Login", "Login ID", userName)); 
		Login.setUserName(LoginDto.getUserName());
		Login.setPassword(LoginDto.getPassword());
		Login updatedLogin=this.LoginRepo.save(Login);
		return LoginToDto(updatedLogin);
	}
	
	

	@Override
	public LoginDto getLoginById(String userName) {
		Login Login=this.LoginRepo.findById(userName)
				.orElseThrow(()->
				new ResourceNotFoundException("Login details", "username ", userName)); 
		return LoginToDto(Login);
	}

	
	
	@Override
	public void deleteLogin(String userName) {
		Login Login = this.LoginRepo.findById(userName)
		.orElseThrow(()->
		new ResourceNotFoundException("Login details", "username", userName));
		this.LoginRepo.delete(Login);
	}

	
	
//	@Override
//	public List<LoginDto> getAllLogins() {
//		List<Login> Logins = this.LoginRepo.findAll();
//		List<LoginDto> LoginDtos = Logins.stream().map(Login->this.LoginToDto(Login)).collect(Collectors.toList());
//		return LoginDtos;
//	}

	
	
	
	public Login dtoToLogin(LoginDto LoginDto) {
		Login Login=this.modelMapper.map(LoginDto, Login.class);
		return Login;
	}
	
	
	
	public LoginDto LoginToDto(Login Login) {
		LoginDto LoginDto=this.modelMapper.map(Login, LoginDto.class);
		return LoginDto;
	}

}
