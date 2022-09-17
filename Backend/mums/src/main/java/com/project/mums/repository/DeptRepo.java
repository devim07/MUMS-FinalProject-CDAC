package com.project.mums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mums.entities.Dept;

public interface DeptRepo extends JpaRepository<Dept, String>{
	public Dept getById(String id);

}