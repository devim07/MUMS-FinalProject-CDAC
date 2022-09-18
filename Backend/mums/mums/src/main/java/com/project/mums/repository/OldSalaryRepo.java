package com.project.mums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.mums.entities.OldSalary;

public interface OldSalaryRepo extends JpaRepository<OldSalary, String> {


	@Query (value="SELECT * FROM OLD_SALARY WHERE YEAR(INT(YEAR))=?1 AND MONTH (INT(MONTH))=?2",nativeQuery=true)
	public List<OldSalary> getAllByMonthYearFromDb(int year, int month);

}
