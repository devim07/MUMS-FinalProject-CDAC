package com.project.mums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mums.entities.Login;

public interface LoginRepo extends JpaRepository<Login, String>{
	
}
