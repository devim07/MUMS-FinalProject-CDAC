package com.project.mums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.project.mums.entities.Emp;

public interface EmpRepo extends JpaRepository<Emp, String>{
	public List<Emp> findAllByDeptno(String deptNo);
	
	@Procedure (value="MONTHLY_SAL")
	public void calculateSalaryInDb(int year, int mon);
}
