package com.ppp.billing.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobActivityOption;
import com.ppp.billing.model.dto.JobActivityOptionDTO;


@Service
public interface JobActivityOptionService {
	Optional<JobActivityOption> findByName(String name);
	JobActivityOption update (JobActivityOption jobActivityOption, long id);
	Optional<JobActivityOption>  findById(long id);
	List<JobActivityOption> getAllJobActivityOptions();
	JobActivityOption save (JobActivityOptionDTO jobActivityOptionDTO);
	void delete(long id);
}
