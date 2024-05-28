package com.ppp.billing.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.ContentType;
import com.ppp.billing.model.Customer;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.JobType;
import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.JobActivityOptionDTO;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.repository.BindingTypeRepository;
import com.ppp.billing.repository.ContentTypeRepository;
import com.ppp.billing.repository.CustomerRepository;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.repository.JobTypeRepository;
import com.ppp.billing.repository.PaperTypeRepository;
import com.ppp.billing.repository.PrintTypeRepository;
import com.ppp.billing.repository.PrintingMachineRepository;
import com.ppp.billing.service.JobService;


@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ContentTypeRepository contentTypeRepository;
	@Autowired
	private PrintingMachineRepository printingMachineRepository;
    @Autowired
    private JobTypeRepository jobTypeRepository;
    @Autowired
    private BindingTypeRepository bindingTypeRepository;
    @Autowired
    private PaperTypeRepository paperTypeRepository;
    @Autowired
    private PrintTypeRepository printTypeRepository;
    
	@Override
	public Job saveJob(JobDTO jobDTO) {
		Job newJob = new Job();
		newJob.setTitle(jobDTO.getTitle());
		newJob.setContentVolume(jobDTO.getContentVolume());
		newJob.setCoverVolume(jobDTO.getCoverVolume());
		newJob.setOpenLength(jobDTO.getOpenLength());
		newJob.setCloseLength(jobDTO.getCloseLength());
		newJob.setOpenWidth(jobDTO.getOpenWidth());
		newJob.setCloseWidth(jobDTO.getCloseWidth());
		newJob.setCtpFees(jobDTO.getCtpFees());
		newJob.setExistingPlate(jobDTO.isExistingPlate());
		newJob.setDataSuppliedByCustomer(jobDTO.isDataSuppliedByCustomer());
		newJob.setLayOutByUs(jobDTO.isLayOutByUs());
		newJob.setTypesettingByUs(jobDTO.isTypesettingByUs());
		newJob.setCreationDate(new Date());
		Optional<Customer> customer = customerRepository.findById(jobDTO.getCustomerId());
		newJob.setCustomer(customer.get());
		Optional<JobType>  jobType = jobTypeRepository.findById(jobDTO.getJobTypeId());
		newJob.setJobType(jobType.get());
		
		JobActivityOptionDTO jobdto = jobDTO.getJobActivities();
		JobActivity activity = new JobActivity();
		activity.setXPerforated(jobdto.getxPerforated());
		activity.setXNumbered(jobdto.getxNumbered());
		activity.setLamination(jobdto.getLamination());
		activity.setXCreased(jobdto.getxCreased());
		activity.setXWiredStiched(jobdto.getxWiredStiched());
		activity.setXCross(jobdto.getxCross());
		activity.setGlueOption(jobdto.getGlueOption());
		activity.setHandgather(jobdto.isHandgather());
		activity.setStitching(jobdto.isStitching());
		activity.setTrimmed(jobdto.isTrimmed());
		activity.setSewn(jobdto.isSewn());
		activity.setSelloptaped(jobdto.isSelloptaped());
		Optional<BindingType> bindingtp = bindingTypeRepository.findById(jobdto.getBindingType());
		activity.setBindingType(bindingtp.get());
		activity.setJob(newJob);
		newJob.setJobActivity(activity);
				
		List<JobPaper> jobPapers = new ArrayList<JobPaper>();
		jobDTO.getJobPapers().forEach(row-> {
			JobPaper jobPaper = new JobPaper();
			jobPaper.setGrammage(row.getGramage());
			jobPaper.setVolume(row.getVolume());
			Optional<PaperType> paperType = paperTypeRepository.findById(row.getPaperTypeId());
			jobPaper.setPaperType(paperType.get());
			Optional<ContentType> contentType = contentTypeRepository.findById(row.getContentTypeId());
			jobPaper.setContentType(contentType.get());

			List<JobColorCombination> colorCombinations = new ArrayList<JobColorCombination>();
			row.getJobColorCombinations().forEach(colors->{
				JobColorCombination jobColorCombination = new JobColorCombination();
				jobColorCombination.setBackColorNumber(colors.getBackColorNumber());
				jobColorCombination.setFrontColorNumber(colors.getFrontColorNumber());
				jobColorCombination.setNumberOfSignature(colors.getSignatureNumber());
				int machineId = Integer.valueOf(colors.getPrintingMachineId().split(",")[0]);
				Optional<PrintingMachine> printingMachine =  printingMachineRepository.findById(machineId);
				jobColorCombination.setPrintingMachine(printingMachine.get());
				Optional<PrintType> printType =  printTypeRepository.findById(colors.getPrintTypeId());
				jobColorCombination.setPrintType(printType.get());
				jobColorCombination.setJobPaper(jobPaper);
				colorCombinations.add(jobColorCombination);
			
			});
			jobPaper.setJobColorCombinations(colorCombinations);
			jobPaper.setJob(newJob);
			jobPapers.add(jobPaper);
			
		});
		newJob.setJobPapers(jobPapers);		
		jobRepository.save(newJob);
        generateSerialNumber(newJob);
		
        return newJob;
	}

	public void generateSerialNumber(Job job) {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String dateString = today.format(formatter);
		int currentCount = (int) (job.getId()%9999); 
		String countString = String.format("%04d", currentCount);
		String serialNuber = dateString + "JBN" + countString;
		job.setReferenceNumber(serialNuber);
		jobRepository.save(job);
	}
	
	@Override
	public List<Job> listAllJob() {
		return jobRepository.findAll();
	}

	public Optional<Job> findById(long id) {
		
		return jobRepository.findById(id);
	}

	
}
