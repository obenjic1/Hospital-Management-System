package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ppp.billing.model.JobActivityOption;
import com.ppp.billing.model.dto.JobActivityOptionDTO;
import com.ppp.billing.repository.JobActivityOptionRepository;
import com.ppp.billing.service.JobActivityOptionService;

@Service
public class JobActivityOptionServiceImpl implements JobActivityOptionService{

	@Autowired
	private JobActivityOptionRepository jobActivityOptionRepository;
	
	@Override
	public Optional<JobActivityOption> findByName(String name) {
		return jobActivityOptionRepository.findByName(name);
	}

	@Override
	public JobActivityOption update(JobActivityOption jobActivityOption, long id) {
		JobActivityOption machineOptional = jobActivityOptionRepository.findById(id).get();	    
		machineOptional.setName(jobActivityOption.getName());
		return jobActivityOptionRepository.save(machineOptional);
	}

	@Override
	public Optional<JobActivityOption> findById(long id) {
		return jobActivityOptionRepository.findById(id);
	}

	@Override
	public List<JobActivityOption> getAllJobActivityOptions() {
		return jobActivityOptionRepository.findAll();
	}

//	@Override
//	public JobActivityOption save(JobActivityOptionDTO jobActivityOptionDTO) {
//		JobActivityOption newActivity = new JobActivityOption();
//		newActivity.setName(jobActivityOptionDTO.getName());
//		return jobActivityOptionRepository.save(newActivity);
//	}

	@Override
	public void delete(long id) {
		jobActivityOptionRepository.deleteById(id);
	}

@Override
public JobActivityOption save(JobActivityOptionDTO jobActivityOptionDTO) {
	// TODO Auto-generated method stub
	return null;
}
	

}
