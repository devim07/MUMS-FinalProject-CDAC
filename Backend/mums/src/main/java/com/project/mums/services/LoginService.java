package com.project.mums.services;

import com.project.mums.payload.LoginDto;

public interface LoginService {
	LoginDto createLogin (LoginDto LoginDto, String ID) ;
	LoginDto updateLogin (LoginDto LoginDto, String Loginno) ;
	LoginDto getLoginById (String Loginno) ;
	//List<LoginDto> getAllLogins();
	void deleteLogin (String Loginno);
}
