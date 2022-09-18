package com.project.mums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.mums.entities.OldSalary;

public interface OldSalaryRepo extends JpaRepository<OldSalary, String> {

	@Query (value="SELECT * FROM OLD_SALARY WHERE ENUM=?1",nativeQuery=true)
	public List<OldSalary> getAllByIdFromDb(String id);

	@Query (value="SELECT * FROM OLD_SALARY WHERE YEAR=?1 && MONTH=?2",nativeQuery=true)
	public List<OldSalary> getAllByMonthYearFromDb(int year, int month);
	
	@Query (value="SELECT * FROM OLD_SALARY WHERE YEAR=?1 && MONTH=?2 && ENUM=?3",nativeQuery=true)
	public OldSalary getByIdMonthYearFromDb(int year, int month, String id);

}
