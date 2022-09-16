package com.project.mums.repository;

	import org.springframework.data.jpa.repository.JpaRepository;

	import com.project.mums.entities.EmpAudit;

	public interface EmpAuditRepo extends JpaRepository<EmpAudit, Integer>{

}
