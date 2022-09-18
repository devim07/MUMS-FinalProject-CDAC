package com.project.mums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.mums.entities.IncmExpTally;




public interface IncmExpTallyRepo extends JpaRepository<IncmExpTally, String>{

	@Query (value="SELECT * FROM INCM_EXP_TALLY WHERE YEAR(DATE(ENTRY_DATE))=?1 AND MONTH (DATE(ENTRY_DATE))=?2",nativeQuery=true)
    public List<IncmExpTally> getAllByMonthYearFromDb(int year, int month);
}
