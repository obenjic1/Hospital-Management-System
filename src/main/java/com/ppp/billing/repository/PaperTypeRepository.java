package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.JobType;
import com.ppp.billing.model.PaperType;


@Repository
public interface PaperTypeRepository extends JpaRepository<PaperType, Long>{
	
		Optional<PaperType> findByName(String name);
		Optional<PaperType>  findById(long id );

}
