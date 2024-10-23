package com.ppp.billing.serviceImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.ContentType;
import com.ppp.billing.model.Customer;
import com.ppp.billing.model.Department;
import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.model.JobMovement;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.JobStatus;
import com.ppp.billing.model.JobTracking;
import com.ppp.billing.model.JobType;
import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.JobActivityOptionDTO;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.repository.BindingTypeRepository;
import com.ppp.billing.repository.ContentTypeRepository;
import com.ppp.billing.repository.CustomerRepository;
import com.ppp.billing.repository.DepartmentRepository;
import com.ppp.billing.repository.JobColorCombinationRepository;
import com.ppp.billing.repository.JobEstimateRepository;
import com.ppp.billing.repository.JobPaperRepository;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.repository.JobStatusRepository;
import com.ppp.billing.repository.JobTrackingRepository;
import com.ppp.billing.repository.JobTypeRepository;
import com.ppp.billing.repository.PaperTypeRepository;
import com.ppp.billing.repository.PrintTypeRepository;
import com.ppp.billing.repository.PrintingMachineRepository;
import com.ppp.billing.service.JobService;
import com.ppp.printable.PrintableElement;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;
import com.ppp.user.service.impl.UserServiceImpl;


@Service
public class JobServiceImpl implements JobService {
	
	@Value("${folder.estimate}")
	private String estimateDir;
	
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
    @Autowired
	public JobStatusRepository jobStatusRepository;
    
    @Autowired
   	public JobStatusServiceImpl jobStatusServiceImpl;
    
    @Autowired
	private JobEstimateRepository jobEstimateRepository; 
    
    @Autowired
	private DepartmentRepository departmentRepository;
    
    @Autowired
	private UserServiceImpl userServiceImpl;
    
    @Autowired
    JobTrackingRepository jobTrackingRepository;
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private JobPaperRepository jobPaperRepository;
   
	@Override
	public Job saveJob(JobDTO jobDTO) {
		Job newJob = new Job();
		newJob.setTitle(jobDTO.getTitle().toUpperCase());
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
		newJob.setPaperFormat(jobDTO.getPaperFormat());
		newJob.setCardCopies(jobDTO.getCardCopies());
		newJob.setCreationDate(new Date());
		Optional<Customer> customer = customerRepository.findById(jobDTO.getCustomerId());
		newJob.setCustomer(customer.get());
		Optional<JobType> jobType = jobTypeRepository.findById(jobDTO.getJobTypeId());
		newJob.setJobType(jobType.get());
		JobStatus status = jobStatusRepository.findById(2).get();
		newJob.setStatus(status);
		
		JobActivityOptionDTO jobdto = jobDTO.getJobActivities();
		JobActivity activity = new JobActivity();
		activity.setXPerforated(jobdto.getxPerforated());
		activity.setXNumbered(jobdto.getxNumbered());
		activity.setLamination(jobdto.getLamination());
		activity.setXCreased(jobdto.getxCreased());
//		activity.setXWiredStiched(jobdto.getrackingServicetxWiredStiched());
		activity.setXCross(jobdto.getxCross());
		//activity.setGlueOption(jobdto.getGlueOption());
		activity.setHandgather(jobdto.isHandgather());
		activity.setIsStitching(jobdto.getStitching());
		activity.setTrimmed(jobdto.isTrimmed());
		activity.setSewn(jobdto.isSewn());
		activity.setHandFoldingCov(jobdto.getHandFoldCov());
		activity.setSelloptaped(jobdto.isSelloptaped());
		if(jobdto.getBindingType()!=0) {
			Optional<BindingType> bindingtp = bindingTypeRepository.findById(jobdto.getBindingType());
			activity.setBindingType(bindingtp.get());
		}
		activity.setJob(newJob);
		newJob.setJobActivity(activity);
				
		List<JobPaper> jobPapers = new ArrayList<JobPaper>();
		jobDTO.getJobPapers().forEach(row-> {
			JobPaper jobPaper = new JobPaper();
			jobPaper.setGrammage(row.getGrammage());
			jobPaper.setVolume(row.getVolume());
			jobPaper.setUnitPrice(row.getPaperUnitPrice());
			Optional<PaperType> paperType = paperTypeRepository.findById(row.getPaperTypeId());
			jobPaper.setPaperType(paperType.get());
			Optional<ContentType> contentType = contentTypeRepository.findById(row.getContentTypeId());
			jobPaper.setContentType(contentType.get());
			jobPaper.setPaperSizeLength(row.getPaperSizeLength());
			jobPaper.setPaperSizeWidth(row.getPaperSizeWidth());

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
		List<JobMovement> movements = new ArrayList<JobMovement>();
		JobMovement moveJob = new JobMovement();
		moveJob.setCreationDate(new Date());
		moveJob.setDescription("new Job");
		Department	department = departmentRepository.findById(1);
		moveJob.setDepartment(department);
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		moveJob.setUser(user);
		movements.add(moveJob);
		moveJob.setJob(newJob);
		newJob.setJobMovements(movements);
		
		List<JobTracking> jobTrackings = new ArrayList<JobTracking>();
		JobTracking tracking = new JobTracking();
		tracking.setCreationDate(new Date());
		tracking.setUser(user);
		tracking.setOperation("Registered Job");
		jobTrackings.add(tracking);
		tracking.setJob(newJob);
		newJob.setJobTrackings(jobTrackings);
		newJob.setJobPapers(jobPapers);	
		jobRepository.saveAndFlush(newJob);
        generateSerialNumber(newJob);
        return newJob; 
	}
	
	public void generateSerialNumber(Job job) {
		int currentCount = (int) (job.getId()%9999); 
		String countString = String.format("%06d", currentCount);
		String serialNuber = "J" + countString;
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
	
	public void deleteById(long id) {
		jobRepository.deleteById(id);
	}

	public void save(Job job) {
		jobRepository.save(job);
		
	}
	
	public String currencyFormarter(double amount) {
		Locale locale = new Locale("en", "EN");

		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		return numberFormat.format(amount);
	}
	
	public String currencyFormarterCfa(double amount) {
		Locale locale = new Locale("en", "EN");

		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		return numberFormat.format(amount);
	}

	
	@Override
	public Optional <Job> findJobByReferenceNumber(String referenceNumber) {
		try {
			Optional <Job> reference = jobRepository.findByReferenceNumber(referenceNumber.toUpperCase());
//			if (reference.isPresent()) {
			return reference;
//			}return null;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String getFinishingActivities(Job job) {
		JobActivity jobActivity = job.getJobActivity();
		String finishingActivities = "";
		if(jobActivity.getXCross()>0) finishingActivities += "Signatures " + jobActivity.getXCross()+ " x cross-folded, " ;
		if(jobActivity.getXCreased()>0) finishingActivities += "Cover " + jobActivity.getXCreased()+ " x creased, " ;
		if(jobActivity.getLamination()>0) finishingActivities += "Cover " + jobActivity.getLamination()+ " side laminated, " ;
		if(jobActivity.getXWiredStiched()>0) finishingActivities += "Booklets " + jobActivity.getXWiredStiched()+ " x wire-stitched, " ;
		if(jobActivity.isSewn()) finishingActivities += "Booklets sewn, " ;
		if(jobActivity.isHandgather()) finishingActivities += "hand-gathered, " ;
		if(jobActivity.isSelloptaped()) finishingActivities += "sellotaped, " ;
		if(jobActivity.isTrimmed()) finishingActivities += "trimmed, " ;
		if(jobActivity.getXPerforated()>0) finishingActivities += "Job perforated " + jobActivity.getXPerforated()+ " times, " ;
		if(jobActivity.getBindingType()!=null) finishingActivities += "final binding: " + jobActivity.getBindingType().getName()+ " " ;
		
		return finishingActivities;
	}
	
	public List<String> gettypsettingActivities(Job job){
		List<String> typsettingActivities = new ArrayList<String>();
		if(job.isTypesettingByUs()) typsettingActivities.add("Typesetting by us");
		if(job.isDataSuppliedByCustomer()) typsettingActivities.add("Data Supplied by customer");
		if(job.isLayOutByUs()) typsettingActivities.add("Layout by us");
		if(job.isExistingPlate()) typsettingActivities.add("Has existing Plates");
		
		return typsettingActivities;
	}

	@Override
	public List<Job> findByCreationDateBetween(Date startDate, Date endDate) {
		try {
			return jobRepository.findByCreationDateBetween(startDate, endDate);
		} catch (Exception e) {
			throw e;
		}
	}
		
	/* working with Job Draft
	 * **/
	
	@Override
	public Job saveDraft(JobDTO jobDTO) {
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
		newJob.setCardCopies(jobDTO.getCardCopies());
		newJob.setCreationDate(new Date());
		Optional<Customer> customer = customerRepository.findById(jobDTO.getCustomerId());
		newJob.setCustomer(customer.get());
		Optional<JobType> jobType = jobTypeRepository.findById(jobDTO.getJobTypeId());
		newJob.setJobType(jobType.get());
		
		List<JobTracking> jobTrackings = new ArrayList<JobTracking>();
		JobTracking tracking = new JobTracking();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		tracking.setUser(user);
		tracking.setCreationDate(new Date());
		tracking.setOperation("Registered Daft");
		jobTrackings.add(tracking);
		tracking.setJob(newJob);
		newJob.setJobTrackings(jobTrackings);
		JobStatus status = jobStatusRepository.findById(1).get();
		newJob.setStatus(status);
		jobRepository.saveAndFlush(newJob); 

		generateSerialNumber(newJob);
		return newJob;
	}

	
	public Job updateDraft(JobDTO jobDTO, long id) {
		Job newJob =jobRepository.findById(id).get();
		newJob.setTitle(jobDTO.getTitle());
		newJob.setContentVolume(jobDTO.getContentVolume());
		newJob.setCoverVolume(jobDTO.getCoverVolume());
		newJob.setOpenLength(jobDTO.getOpenLength());
		newJob.setCloseLength(jobDTO.getCloseLength());
		newJob.setOpenWidth(jobDTO.getOpenWidth());
		newJob.setCloseWidth(jobDTO.getCloseWidth());
		newJob.setCardCopies(jobDTO.getCardCopies());
		newJob.setCtpFees(jobDTO.getCtpFees());
		newJob.setExistingPlate(jobDTO.isExistingPlate());
		newJob.setDataSuppliedByCustomer(jobDTO.isDataSuppliedByCustomer());
		newJob.setLayOutByUs(jobDTO.isLayOutByUs());
		newJob.setTypesettingByUs(jobDTO.isTypesettingByUs());
		// Optional<JobType> jobType = jobTypeRepository.findById(jobDTO.getJobTypeId());
		//newJob.setJobType(jobType.get());
		
		JobTracking tracking = new JobTracking();
		tracking.setCreationDate(new Date());
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		tracking.setUser(user);
		tracking.setOperation("Edit Daft");
		tracking.setJob(newJob);
		jobTrackingRepository.save(tracking);

		jobRepository.saveAndFlush(newJob);
		return newJob;
	}

	
	
	
	
	
	public Job updateJob(JobDTO jobDTO) {
	Job newJob = jobRepository.findById(jobDTO.getId()).get() ;
		newJob.setTitle(jobDTO.getTitle());
		newJob.setContentVolume(jobDTO.getContentVolume());
		newJob.setCoverVolume(jobDTO.getCoverVolume());
		newJob.setOpenLength(jobDTO.getOpenLength());
		newJob.setCloseLength(jobDTO.getCloseLength());
		newJob.setOpenWidth(jobDTO.getOpenWidth());
		newJob.setCloseWidth(jobDTO.getCloseWidth());
		newJob.setCardCopies(jobDTO.getCardCopies());
		newJob.setCtpFees(jobDTO.getCtpFees());
		newJob.setExistingPlate(jobDTO.isExistingPlate());
		newJob.setDataSuppliedByCustomer(jobDTO.isDataSuppliedByCustomer());
		newJob.setLayOutByUs(jobDTO.isLayOutByUs());
		newJob.setTypesettingByUs(jobDTO.isTypesettingByUs());
		newJob.setPaperFormat(jobDTO.getPaperFormat());
		newJob.setCreationDate(new Date());
		Optional<Customer> customer = customerRepository.findById(jobDTO.getCustomerId());
		newJob.setCustomer(customer.get());
		Optional<JobType> jobType = jobTypeRepository.findById(jobDTO.getJobTypeId());
		newJob.setJobType(jobType.get());
		JobStatus status = jobStatusRepository.findById(2).get();
		newJob.setStatus(status);
		
		List<JobPaper> jobPapers_ = newJob.getJobPapers();
		
		for( JobPaper jobpaper : jobPapers_ ) {
			for(JobColorCombination jbColorCmb : jobpaper.getJobColorCombinations()) {
				jobPaperRepository.deleteByjobColorCombination(jbColorCmb.getId());
			}
			jobPaperRepository.deleteByjobPaper(jobpaper.getId());
		}
	
		
		JobActivityOptionDTO jobdto = jobDTO.getJobActivities();
		JobActivity activity = newJob.getJobActivity();
		activity.setXPerforated(jobdto.getxPerforated());
		activity.setXNumbered(jobdto.getxNumbered());
		activity.setLamination(jobdto.getLamination());
		activity.setXCreased(jobdto.getxCreased());
//		activity.setXWiredStiched(jobdto.getrackingServicetxWiredStiched());
		activity.setXCross(jobdto.getxCross());
		//activity.setGlueOption(jobdto.getGlueOption());
		activity.setHandgather(jobdto.isHandgather());
		activity.setIsStitching(jobdto.getStitching());
		activity.setTrimmed(jobdto.isTrimmed());
		activity.setSewn(jobdto.isSewn());
		activity.setHandFoldingCov(jobdto.getHandFoldCov());
		activity.setSelloptaped(jobdto.isSelloptaped());
		if(jobdto.getBindingType()!=0) {
			Optional<BindingType> bindingtp = bindingTypeRepository.findById(jobdto.getBindingType());
			activity.setBindingType(bindingtp.get());
		}
		activity.setJob(newJob);
		newJob.setJobActivity(activity);
				
		List<JobPaper> jobPapers =new ArrayList<JobPaper>();
		jobDTO.getJobPapers().forEach(row-> {
			JobPaper jobPaper = new JobPaper();
			jobPaper.setGrammage(row.getGrammage());
			jobPaper.setVolume(row.getVolume());
			jobPaper.setPaperSizeLength(row.getPaperSizeLength());
			jobPaper.setPaperSizeWidth(row.getPaperSizeWidth());
			jobPaper.setUnitPrice(row.getPaperUnitPrice());
			Optional<PaperType> paperType = paperTypeRepository.findById(row.getPaperTypeId());
			if(paperType.isPresent())
			jobPaper.setPaperType(paperType.get());
			Optional<ContentType> contentType = contentTypeRepository.findById(row.getContentTypeId());
			if(contentType.isPresent())
			jobPaper.setContentType(contentType.get());

			List<JobColorCombination> colorCombinations = new ArrayList<JobColorCombination>();
			row.getJobColorCombinations().forEach(colors->{
				JobColorCombination jobColorCombination = new JobColorCombination();
				jobColorCombination.setBackColorNumber(colors.getBackColorNumber());
				jobColorCombination.setFrontColorNumber(colors.getFrontColorNumber());
				jobColorCombination.setNumberOfSignature(colors.getSignatureNumber());
				int machineId = Integer.valueOf(colors.getPrintingMachineId().split(",")[0]);
				Optional<PrintingMachine> printingMachine =  printingMachineRepository.findById(machineId);
				if(printingMachine.isPresent())
				jobColorCombination.setPrintingMachine(printingMachine.get());
				Optional<PrintType> printType =  printTypeRepository.findById(colors.getPrintTypeId());
				if(printType.isPresent())
				jobColorCombination.setPrintType(printType.get());
				jobColorCombination.setJobPaper(jobPaper);
				colorCombinations.add(jobColorCombination);
		
			});
			jobPaper.setJobColorCombinations(colorCombinations);
			jobPaper.setJob(newJob);
			jobPapers.add(jobPaper);
			
		});
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		JobTracking tracking = new JobTracking();
		tracking.setCreationDate(new Date());
		tracking.setUser(user);
		tracking.setOperation("Edit Job");
		tracking.setJob(newJob);
		newJob.setJobPapers(jobPapers);
		jobTrackingRepository.save(tracking);
		jobRepository.saveAndFlush(newJob);
        return newJob; 
	}
	
	/*
	 * Function That is Use to update Job And Draft
	 */
	public Job completeDraft(JobDTO jobDTO, long id) {
		Job newJob =jobRepository.findById(id).get();
		newJob.setTitle(jobDTO.getTitle().toUpperCase());
		newJob.setContentVolume(jobDTO.getContentVolume());
		newJob.setCoverVolume(jobDTO.getCoverVolume());
		newJob.setOpenLength(jobDTO.getOpenLength());
		newJob.setCloseLength(jobDTO.getCloseLength());
		newJob.setOpenWidth(jobDTO.getOpenWidth());
		newJob.setCloseWidth(jobDTO.getCloseWidth());
		newJob.setCardCopies(jobDTO.getCardCopies());
		newJob.setCtpFees(jobDTO.getCtpFees());
		newJob.setExistingPlate(jobDTO.isExistingPlate());
		newJob.setDataSuppliedByCustomer(jobDTO.isDataSuppliedByCustomer());
		newJob.setLayOutByUs(jobDTO.isLayOutByUs());
		newJob.setTypesettingByUs(jobDTO.isTypesettingByUs());
		newJob.setCreationDate(new Date());
		Optional<Customer> customer = customerRepository.findById(jobDTO.getCustomerId());
		if(customer.isPresent())
		newJob.setCustomer(customer.get());
		JobType jobType = jobTypeRepository.findById(jobDTO.getJobTypeId()).get();
		newJob.setJobType(jobType);
		JobStatus status = jobStatusRepository.findById(2).get();
		newJob.setStatus(status);

		
		JobActivityOptionDTO jobdto = jobDTO.getJobActivities();
		JobActivity activity = new JobActivity();
		activity.setXPerforated(jobdto.getxPerforated());
		activity.setXNumbered(jobdto.getxNumbered());
		activity.setLamination(jobdto.getLamination());
		activity.setXCreased(jobdto.getxCreased());
//		activity.setXWiredStiched(jobdto.getrackingServicetxWiredStiched());
		activity.setXCross(jobdto.getxCross());
		//activity.setGlueOption(jobdto.getGlueOption());
		activity.setHandgather(jobdto.isHandgather());
		activity.setIsStitching(jobdto.getStitching());
		activity.setTrimmed(jobdto.isTrimmed());
		activity.setSewn(jobdto.isSewn());
		activity.setHandFoldingCov(jobdto.getHandFoldCov());
		activity.setSelloptaped(jobdto.isSelloptaped());
		if(jobdto.getBindingType()!=0) {
			Optional<BindingType> bindingtp = bindingTypeRepository.findById(jobdto.getBindingType());
			activity.setBindingType(bindingtp.get());
		}
		activity.setJob(newJob);
		newJob.setJobActivity(activity);
				
		List<JobPaper> jobPapers = new ArrayList<JobPaper>();
		jobDTO.getJobPapers().forEach(row-> {
			JobPaper jobPaper = new JobPaper();
			jobPaper.setGrammage(row.getGrammage());
			jobPaper.setVolume(row.getVolume());
			jobPaper.setUnitPrice(row.getPaperUnitPrice());
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
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		
		JobTracking tracking = new JobTracking();
		tracking.setCreationDate(new Date());
		tracking.setUser(user);
		tracking.setOperation("complete draft  Job");
		tracking.setJob(newJob);
		newJob.setJobPapers(jobPapers);	
		jobTrackingRepository.save(tracking);
		jobRepository.saveAndFlush(newJob);
        generateSerialNumber(newJob);
        return newJob; 
	}

			// aborting a job
	
	@Override
	@Transactional()
	public void abortJob(long id) {
		Job job = jobRepository.findById(id).get();
		JobStatus status = jobStatusRepository.findById(5).get();
		job.setStatus(status);
		JobTracking tracking = new JobTracking();
		tracking.setCreationDate(new Date());
		tracking.setOperation("Aborted Job");
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		tracking.setUser(user);
		tracking.setJob(job);
		jobTrackingRepository.save(tracking);
		jobRepository.saveAndFlush(job);
	}
				// Mark Job has been proofreaded 
	@Override
	public void proofreadByTheCustomer(long id) {
		try {
			Job job =jobRepository.findById(id).get();
			if(job.getStatus().getName().equals("Registered")||job.getStatus().getName().equals("Confrimed")||job.getStatus().getName().equals("Approved"))
				job.setProofread(true);
			JobTracking tracking = new JobTracking();
			tracking.setCreationDate(new Date());
			tracking.setOperation("proof read Job");
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(name);
			tracking.setUser(user);
			tracking.setJob(job);
			jobTrackingRepository.save(tracking);
			jobRepository.save(job);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	// Mark a Job as Confirm 
	@Override
	public void confirmJob(long id) {
		Job job =jobRepository.findById(id).get();
		if(job.getStatus().getName().equals("Registered")){
			JobStatus status = jobStatusRepository.findById(3).get();
			JobTracking tracking = new JobTracking();
			tracking.setCreationDate(new Date());
			tracking.setOperation("Confim Job");
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(name);
			tracking.setUser(user);
			tracking.setJob(job);
			tracking.setOperation("Confirmed Job");
			jobTrackingRepository.save(tracking);
			job.setStatus(status);
			jobRepository.saveAndFlush(job);
		}
		
	}
	
	// Mark a Job  as Approve 
	@Override
	public void approve(long id) {
		Job job =jobRepository.findById(id).get();
		if(job.getStatus().getName().equals("Confrimed")){
			JobStatus status = jobStatusRepository.findById(4).get();
			JobTracking tracking = new JobTracking();
			tracking.setCreationDate(new Date());
			tracking.setOperation("Approved Job");
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(name);
			tracking.setUser(user);
			tracking.setJob(job);
			jobTrackingRepository.save(tracking);
			job.setStatus(status);
			jobRepository.saveAndFlush(job);
		}
	
	}

	
	/*
	 * Print the estimate pdf
	 */
	public String createEstimateDataPdf(String estimateName) throws IOException{
	 try {
		PdfWriter pdfWriter = new PdfWriter(estimateDir+estimateName+".pdf");
		JobEstimate jobEstimate=jobEstimateRepository.findByReference(estimateName).get();
		Job job=jobEstimate.getJob();
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument, PageSize.A4);
//		 document.setMargins(25, 25, 297-156, 50);

		PrintableElement printer = new PrintableElement();	
		JobActivity jobActivity = job.getJobActivity();
		List<JobPaper> jcc = job.getJobPapers();
		JobPaper coverPaper = null;
		for(JobPaper pp : jcc) {
			if(pp.getContentType().getId()==1)
			{
				coverPaper=pp;
				printer.print(document,"Cover: "+ job.getOpenLength()+" X "+job.getOpenWidth()+" mm", 73, 297-93);
				printer.print(document, "Cover: " +job.getCoverVolume()+" Pages", 73, 297-110);
				printer.print(document,"Cover: "+  coverPaper.getJobColorCombinations().get(0).getFrontColorNumber() + "/" +coverPaper.getJobColorCombinations().get(0).getBackColorNumber()+" " + coverPaper.getJobColorCombinations().get(0).getPrintType().getName(), 73, 297-136);
				printer.printHeader(document,"Paper", 38, 297-182);
			    printer.print(document, "Cover : "+ job.getJobPapers().get(0).getPaperType().getName(), 73, 297-182);
				printer.print(document, job.getJobPapers().get(0).getGrammage()+" GSM", 168, 297-182);
		}}
		String jobActivities = "";
		String typeSettings = "";
		String reproduction = "";
			
			printer.printHeader(document, "Estimate ".toUpperCase() , 73, 297-42);
		 	printer.print(document, "("+jobEstimate.getReference().toUpperCase()+")", 97, 297-42);

		 	printer.printHeader(document,job.getCustomer().getName().toUpperCase(), 123, 297-52);
			printer.printHeader(document,job.getCustomer().getAddress().toUpperCase(), 123, 297-58);

			printer.printHeader(document, "Description", 38, 297-83);
			printer.print(document, job.getJobType().getName(), 73, 297-83);

			if(job.isTypesettingByUs()||job.isLayOutByUs()) typeSettings =	typeSettings + "By us,";
			if(job.isExistingPlate()) {
				reproduction = reproduction +  "Existing Plate";
				
			}else reproduction = reproduction +  "Data supplied By Customer";

			printer.printHeader(document, "Typesetting ", 38, 297-123);
			printer.print(document, typeSettings, 73, 297-123);
			
			printer.printHeader(document, " Reproduction", 38, 297-128 );
			printer.print(document, reproduction, 73, 297-128);

			printer.printHeader(document, "Format", 38, 297-93);
			printer.printHeader(document, "Volume", 38, 297-110);
			
			printer.printHeader(document, "Printing", 38, 297-137);

				String message_ =" ";
				boolean isContent =false;
				for(JobPaper pp:jcc) {
				if(pp.getContentType().getId()!=1)
					{
					isContent=true;
					for(int j=0; j<pp.getJobColorCombinations().size(); j++) {
					message_+=  pp.getJobColorCombinations().get(j).getFrontColorNumber()+"/"+ pp.getJobColorCombinations().get(j).getBackColorNumber()+" "+pp.getJobColorCombinations().get(j).getPrintType().getName()+"";
				}}
				
			}
				if(isContent) {
					printer.print(document, message_, 90, 297-142);
					printer.print(document, "Content", 73, 297-142);
					printer.print(document,"Content: "+  job.getCloseLength()+" X "+ job.getCloseWidth()+ " mm", 73, 297-99);
					if(job.getJobType().getCategory()!=3)
						 printer.print(document, "Content: " +job.getContentVolume()+" Pages", 73, 297-116);
					else
						printer.print(document, "Content: " +job.getContentVolume()+" x " +job.getCardCopies() +" Copies", 73, 297-116);
				}
			
			printer.printHeader(document, "Finishing", 38, 297-161);
			
			if(jobActivity.isHandgather()) jobActivities =	jobActivities + "hand-gatherd, ";
			if(jobActivity.isSelloptaped()) jobActivities =	jobActivities + " Selloptaped, ";
			if(jobActivity.isSewn()) jobActivities =	jobActivities + " Sewn,";
			if(jobActivity.isTrimmed()) jobActivities =	jobActivities + " trimmed, ";
			if(jobActivity.getIsStitching()!=null) jobActivities =	jobActivity.getIsStitching() + ", ";
			//if(!jobActivity.getGlueOption().isEmpty()) jobActivities =	jobActivities + jobActivity.getGlueOption()+ ", ";
			if(jobActivity.getXWiredStiched()>0) jobActivities =	jobActivities + jobActivity.getXWiredStiched()+ " x Stiched, ";
			if(jobActivity.getXCreased()>0) jobActivities =	jobActivities + " Cover "+ jobActivity.getXCreased()+ " x creased, ";
			if(jobActivity.getXCross()>0) jobActivities =	jobActivities + jobActivity.getXCross()+ " x folded,";
			if(jobActivity.getXNumbered()>0) jobActivities =	jobActivities + jobActivity.getXNumbered()+ " x Numbered, ";
			if(jobActivity.getBindingType()!=null) jobActivities =	jobActivities + jobActivity.getBindingType().getName()+" ";
			if(jobActivity.getLamination()>0) jobActivities =	jobActivities +" Cover " + jobActivity.getLamination() + " side(s) laminated, ";

		 printer.printParagraphe(document,jobActivities, 73, 297-170);

		   if(isContent)			
			printer.printParagraphe(document,"Content : ", 73, 297-187);
			float vecto = -5;
			for(JobPaper pp:jcc) {
				if(pp.getContentType().getId()!=1) {
					vecto+=5;
					printer.print(document, pp.getPaperType().getName(),  90, 297-187-vecto);
					printer.print(document, pp.getGrammage()+" GSM",  168, 297-187-vecto);
				}

			}
			printer.printHeader(document, "Quantity",  38, 297-200);
			printer.printHeader(document, "Unit(XAF)",  132, 297-200);
			printer.printHeader(document, "Total(XAF)",  172, 297-200);
			
			String messagesAdvancePayment="";
			List<EstimatePricing> estimates =jobEstimate.getEstimatePricings();
			float vect = -5;
			DateFormat date =  DateFormat.getDateInstance(DateFormat.DEFAULT,Locale.ENGLISH);
			printer.printHeader(document,date.format(new Date())+"", 38, 297-73);
			for(int i=0; i<estimates.size(); i++) {
				vect+=5;
				printer.printMoney(document,estimates.get(i).getQuantity(), 82, 297-207-vect);
				printer.printMoney(document,estimates.get(i).getUnitPrice(), 132, 297-207-vect);
				printer.printMoney(document,estimates.get(i).getTotalPrice() , 171, 297-207-vect);
				
			}
			if(jobEstimate.getAdvancePercentage()> 0)
				messagesAdvancePayment =" Terms of Payment : "+ jobEstimate.getAdvancePercentage()  + "%" + " in advance, "+(100-jobEstimate.getAdvancePercentage())+ ""+"% on delivery.";
		printer.printHeader(document,messagesAdvancePayment, 38,297-227-vect);
			
			document.close();
			String	file = jobEstimate.getReference()+".pdf";
		 return "file="+file+"&dir=folder.estimate";
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			return "file=error.pdf&dir=folder.estimate";
		}
		
	}
	
	
	public String createEstimateDataPdfWithCommision(String estimateName, List<EstimatePricing> estimates) throws IOException{
		 try {
			PdfWriter pdfWriter = new PdfWriter(estimateDir+estimateName+".pdf");
			JobEstimate jobEstimate=jobEstimateRepository.findByReference(estimateName).get();
			
			Job job=jobEstimate.getJob();
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument, PageSize.A4);
//			 document.setMargins(25, 25, 297-156, 50);

			PrintableElement printer = new PrintableElement();	
			JobActivity jobActivity = job.getJobActivity();
			List<JobPaper> jcc = job.getJobPapers();
			JobPaper coverPaper = null;
			for(JobPaper pp : jcc) {
				if(pp.getContentType().getId()==1)
				{
					coverPaper=pp;
					printer.print(document,"Cover: "+ job.getOpenLength()+" X "+job.getOpenWidth()+" mm", 73, 297-93);
					printer.print(document, "Cover: " +job.getCoverVolume()+" Pages", 73, 297-110);
					printer.print(document,"Cover: "+  coverPaper.getJobColorCombinations().get(0).getFrontColorNumber() + "/" +coverPaper.getJobColorCombinations().get(0).getBackColorNumber()+" " + coverPaper.getJobColorCombinations().get(0).getPrintType().getName(), 73, 297-136);
					printer.printHeader(document,"Paper", 38, 297-182);
				    printer.print(document, "Cover : "+ job.getJobPapers().get(0).getPaperType().getName(), 73, 297-182);
					printer.print(document, job.getJobPapers().get(0).getGrammage()+" GSM", 168, 297-182);
			}}
			String jobActivities = "";
			String typeSettings = "";
			String reproduction = "";
				
				printer.printHeader(document, "Estimate ".toUpperCase() , 73, 297-42);
			 	printer.print(document, "("+jobEstimate.getReference().toUpperCase()+")", 97, 297-42);

			 	printer.printHeader(document,job.getCustomer().getName().toUpperCase(), 123, 297-52);
				printer.printHeader(document,job.getCustomer().getAddress().toUpperCase(), 123, 297-58);

				printer.printHeader(document, "Description", 38, 297-83);
				printer.print(document, job.getJobType().getName(), 73, 297-83);

				if(job.isTypesettingByUs()||job.isLayOutByUs()) typeSettings =	typeSettings + "By us,";
				if(job.isExistingPlate()) {
					reproduction = reproduction +  "Existing Plate";
					
				}else reproduction = reproduction +  "Data supplied By Customer";

				printer.printHeader(document, "Typesetting ", 38, 297-123);
				printer.print(document, typeSettings, 73, 297-123);
				
				printer.printHeader(document, " Reproduction", 38, 297-128 );
				printer.print(document, reproduction, 73, 297-128);

				printer.printHeader(document, "Format", 38, 297-93);
				printer.printHeader(document, "Volume", 38, 297-110);
				
				printer.printHeader(document, "Printing", 38, 297-137);

					String message_ =" ";
					boolean isContent =false;
					for(JobPaper pp:jcc) {
					if(pp.getContentType().getId()!=1)
						{
						isContent=true;
						for(int j=0; j<pp.getJobColorCombinations().size(); j++) {
						message_+=  pp.getJobColorCombinations().get(j).getFrontColorNumber()+"/"+ pp.getJobColorCombinations().get(j).getBackColorNumber()+" "+pp.getJobColorCombinations().get(j).getPrintType().getName()+"";
					}}
					
				}
					if(isContent) {
						printer.print(document, message_, 90, 297-142);
						printer.print(document, "Content", 73, 297-142);
						printer.print(document,"Content: "+  job.getCloseLength()+" X "+ job.getCloseWidth()+ " mm", 73, 297-99);
						if(job.getJobType().getCategory()!=3)
							 printer.print(document, "Content: " +job.getContentVolume()+" Pages", 73, 297-116);
						else
							printer.print(document, "Content: " +job.getContentVolume()+" x " +job.getCardCopies() +" Copies", 73, 297-116);
					}
				
				printer.printHeader(document, "Finishing", 38, 297-161);
				
				if(jobActivity.isHandgather()) jobActivities =	jobActivities + "hand-gatherd, ";
				if(jobActivity.isSelloptaped()) jobActivities =	jobActivities + " Selloptaped, ";
				if(jobActivity.isSewn()) jobActivities =	jobActivities + " Sewn,";
				if(jobActivity.isTrimmed()) jobActivities =	jobActivities + " trimmed, ";
				if(jobActivity.getIsStitching()!=null) jobActivities =	jobActivity.getIsStitching() + ", ";
				//if(!jobActivity.getGlueOption().isEmpty()) jobActivities =	jobActivities + jobActivity.getGlueOption()+ ", ";
				if(jobActivity.getXWiredStiched()>0) jobActivities =	jobActivities + jobActivity.getXWiredStiched()+ " x Stiched, ";
				if(jobActivity.getXCreased()>0) jobActivities =	jobActivities + " Cover "+ jobActivity.getXCreased()+ " x creased, ";
				if(jobActivity.getXCross()>0) jobActivities =	jobActivities + jobActivity.getXCross()+ " x folded,";
				if(jobActivity.getXNumbered()>0) jobActivities =	jobActivities + jobActivity.getXNumbered()+ " x Numbered, ";
				if(jobActivity.getBindingType()!=null) jobActivities =	jobActivities + jobActivity.getBindingType().getName()+" ";
				if(jobActivity.getLamination()>0) jobActivities =	jobActivities +" Cover " + jobActivity.getLamination() + " side(s) laminated, ";

			 printer.printParagraphe(document,jobActivities, 73, 297-170);
			 
			   if(isContent)			
				printer.printParagraphe(document,"Content : ", 73, 297-187);
				float vecto = -5;
				for(JobPaper pp:jcc) {
					if(pp.getContentType().getId()!=1) {
						vecto+=5;
						printer.print(document, pp.getPaperType().getName(),  90, 297-187-vecto);
						printer.print(document, pp.getGrammage()+" GSM",  168, 297-187-vecto);
					}

				}
				printer.printHeader(document, "Quantity",  38, 297-200);
				printer.printHeader(document, "Unit(XAF)",  132, 297-200);
				printer.printHeader(document, "Total(XAF)",  172, 297-200);
				
				String messagesAdvancePayment="";
				
				// work done on printed estimate with commission		
//				List<EstimatePricing> estimates =jobEstimateServiceImpl.generateCommissionEstimateResult(jobEstimate.getId());
//			
//				for (EstimatePricing estimatePricing: jobEstimate.getEstimatePricings()) {
//					
//					if(estimatePricing.isInvoiced()) {
//							for(EstimatePricing invoicedEstimatePricing : estimates ) {
//						if(estimatePricing.getQuantity() == invoicedEstimatePricing.getQuantity()) {
//							
//							invoicedEstimatePricing.setTotalPrice(invoicedEstimatePricing.getTotalPrice() + jobEstimate.getDiscountValue() );
//							invoicedEstimatePricing.setUnitPrice(invoicedEstimatePricing.getTotalPrice()/invoicedEstimatePricing.getQuantity());
//						}
//							}
//					}
//				}
				
				
				float vect = -5;
				DateFormat date =  DateFormat.getDateInstance(DateFormat.DEFAULT,Locale.ENGLISH);
				printer.printHeader(document,date.format(new Date())+"", 38, 297-73);
				for(int i=0; i<estimates.size(); i++) {
					vect+=5;
					printer.printMoney(document,estimates.get(i).getQuantity(), 82, 297-207-vect);
					printer.printMoney(document,estimates.get(i).getUnitPrice(), 132, 297-207-vect);
					printer.printMoney(document,estimates.get(i).getTotalPrice() , 171, 297-207-vect);
					
				}
				if(jobEstimate.getAdvancePercentage()> 0)
					messagesAdvancePayment =" Terms of Payment : "+ jobEstimate.getAdvancePercentage()  + "%" + " in advance, "+(100-jobEstimate.getAdvancePercentage())+ ""+"% on delivery.";
			printer.printHeader(document,messagesAdvancePayment, 38,297-227-vect);
				
				document.close();
				String	file = jobEstimate.getReference()+".pdf";
			 return "file="+file+"&dir=folder.estimate";
			} 
			
			catch (Exception e) {
				e.printStackTrace();
				return "file=error.pdf&dir=folder.estimate";
			}
			
		}
	
}
