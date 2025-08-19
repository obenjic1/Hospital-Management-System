package com.ppp.billing.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.dto.JobPaperDTO;



public interface JobPaperRepository extends JpaRepository<JobPaper,Long>{
	
	Optional<JobPaper>  findById(long id );
	JobPaperDTO findByGrammage(String grammage);
	
	@Transactional
	@Modifying
	@Query(value = "delete from job_paper where job_paper.id=:id", nativeQuery = true)
	 void deleteByjobPaper(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from job_color_combination where job_color_combination.id=:id", nativeQuery = true)
	 void deleteByjobColorCombination(@Param("id") long id);

		
}
