package com.project.mums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.mums.entities.Login;

public interface LoginRepo extends JpaRepository<Login, String>{
	
	@Query(value="SELECT * FROM LOGIN WHERE BINARY USERNAME=?1",nativeQuery=true)
	public Login getUserByUserName(String username);
	
}
