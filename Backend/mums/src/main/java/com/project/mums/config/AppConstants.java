package com.project.mums.config;

import java.util.ArrayList;
import java.util.List;

public class AppConstants {

	public static final List<String> ROLES;
	static{
		ROLES=new ArrayList<>();
		ROLES.add("ROLE_EMPLOYEE");
		ROLES.add("ROLE_ADMIN");
		ROLES.add("ROLE_CUSTOMER");			
	};
	
	
	public static final long JWT_TOKEN_VALIDITY = 10*60*1000;
}
