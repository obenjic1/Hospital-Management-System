package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.dto.JobPaperDTO;

import java.util.List;



public interface JobPaperRepository extends JpaRepository<JobPaper,Long>{
	
	Optional<JobPaper>  findById(long id );
	JobPaperDTO findByGrammage(String grammage);
		
}
