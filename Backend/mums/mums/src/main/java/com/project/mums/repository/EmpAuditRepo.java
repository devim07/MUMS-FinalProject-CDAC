package com.project.mums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.mums.entities.EmpAudit;

public interface EmpAuditRepo extends JpaRepository<EmpAudit, Integer>{
	@Query(value = "SELECT * FROM EMP_AUDIT ORDER BY SR_NO DESC", nativeQuery = true)
	   public List<EmpAudit> getFullAuditDetails();

}