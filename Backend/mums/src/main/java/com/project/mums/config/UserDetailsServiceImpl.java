package com.project.mums.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.mums.entities.Login;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.repository.LoginRepo;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private LoginRepo loginRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login loginUser = loginRepo.getUserByUserName(username);
		if(loginUser==null)
			throw new ResourceNotFoundException("User", "username", username);
		CustomUserDetails customUserDetails=new CustomUserDetails(loginUser);
		return customUserDetails;
	}

}
