package com.ppp.billing.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.JobActivityOption;
import com.ppp.billing.model.dto.JobActivityDTO;
import com.ppp.billing.model.dto.JobColorCombinationDTO;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.model.dto.JobPaperDTO;
import com.ppp.billing.model.dto.JobStatusDTO;
import com.ppp.billing.repository.CustomerRepository;
import com.ppp.billing.repository.JobActivityOptionRepository;
import com.ppp.billing.repository.JobActivityRepository;
import com.ppp.billing.repository.JobPaperRepository;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.service.JobService;


@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private JobPaperRepository jobPaperRepository;
	@Autowired
	private JobActivityOptionRepository jobActivityOptionRepository;
//	@Autowired
//	private ModelMapper modelMapper;


	
	@Override
	public Optional<Job> findByTitle(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Job update(JobDTO jobDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Job> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Page<Job> findPaginatedJob(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job saveJob(JobDTO jobDTO, JobPaperDTO jobPaperDTO, JobColorCombinationDTO jobColorCombinationDTO) {
		JobDTO newJob = new JobDTO();
		newJob.setTitle(jobDTO.getTitle());
		newJob.setDescription(jobDTO.getDescription());
		newJob.setCoverVolume(jobDTO.getCoverVolume());
		newJob.setContentVolume(jobDTO.getContentVolume());
		newJob.setTotalCoverSignature(jobDTO.getTotalCoverSignature());
		newJob.setTotalContentSignature(jobDTO.getTotalContentSignature());
		newJob.setExistingPlate(jobDTO.isExistingPlate());
		newJob.setLayOutByUs(jobDTO.isLayOutByUs());
		newJob.setTypesettingByUs(jobDTO.isTypesettingByUs());
		newJob.setDataSuppliedByCustomer(jobDTO.isDataSuppliedByCustomer());
		newJob.setCreationDate(new Date());
		newJob.setExpectedDeliveryDate(jobDTO.getExpectedDeliveryDate());
		  JobPaperDTO newJobPaperDTO = new JobPaperDTO();
		  newJobPaperDTO.setGrammage(jobPaperDTO.getGrammage());
		  newJobPaperDTO.setOpenLength(jobPaperDTO.getOpenLength());
		  newJobPaperDTO.setCloseLength(jobPaperDTO.getCloseLength());
		  newJobPaperDTO.setOpenWidth(jobPaperDTO.getOpenWidth());
		  newJobPaperDTO.setCloseWidth(jobPaperDTO.getCloseWidth());
		  newJobPaperDTO.setVolume(jobPaperDTO.getVolume());
		  newJobPaperDTO.setPaperTypeDTO(jobPaperDTO.getPaperTypeDTO());
		  newJobPaperDTO.setPrintingMachineDTO(jobPaperDTO.getPrintingMachineDTO());		  
		  newJobPaperDTO.setContentTypeDTO(jobPaperDTO.getContentTypeDTO());
			JobColorCombinationDTO newJobColorCombinationDTO = new JobColorCombinationDTO();
		    newJobColorCombinationDTO.setBackColorNumber(jobColorCombinationDTO.getBackColorNumber());
		    newJobColorCombinationDTO.setFrontColorNumber(jobColorCombinationDTO.getFrontColorNumber());
		    newJobColorCombinationDTO.setPrintTypeDTO(jobColorCombinationDTO.getPrintTypeDTO());		    
		  newJobPaperDTO.setJobColorCombinationsDTO(newJobColorCombinationDTO);
		  List<JobPaperDTO> jobPaperdtos = jobDTO.getJobPapersDTO();
		  newJob.setJobPapersDTO(jobPaperdtos);	
		  newJob.setBindingTypeDTO(jobDTO.getBindingTypeDTO());
		  JobStatusDTO jobStatusDTO = new JobStatusDTO();
		  jobStatusDTO.setName("CREATED");
		newJob.setJobStatusDTO(jobStatusDTO);
		newJob.setJobTypeDTO(jobDTO.getJobTypeDTO());
		
		List<JobActivityDTO> jobActivitiesDTO = jobDTO.getJobActivitiesDTO();
		newJob.setJobActivitiesDTO(jobActivitiesDTO);
//	  Job newJobResult = modelMapper.map(newJob, Job.class);
//	  jobRepository.save(newJobResult);
	  
	  for(JobActivityDTO jobActivityDTO : jobActivitiesDTO) {
//		  Optional<JobActivityOption> jobActivityOptionRepository = jobActivityOptionRepository.findByName(jobActivityDTO);
	  }
			return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Job> findall() {
		// TODO Auto-generated method stub
		return null;
	}

}
