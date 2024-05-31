package com.ppp.billing.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.Customer;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.JobType;
import com.ppp.billing.model.PaperFormat;
import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.EstimateDTO;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.serviceImpl.BindingTypeserviceImpl;
import com.ppp.billing.serviceImpl.CustomerServiceImpl;
import com.ppp.billing.serviceImpl.JobColorCombinationServiceImpl;
import com.ppp.billing.serviceImpl.JobPaperServiceImpl;
import com.ppp.billing.serviceImpl.JobServiceImpl;
import com.ppp.billing.serviceImpl.JobTypeServiceImpl;
import com.ppp.billing.serviceImpl.PaperFormatServiceImpl;
import com.ppp.billing.serviceImpl.PaperGrammageServiceImpl;
import com.ppp.billing.serviceImpl.PaperTypeServiceImpl;
import com.ppp.billing.serviceImpl.PrintTypeServiceImpl;
import com.ppp.billing.serviceImpl.PrintingMachineServiceImpl;

@Controller
@RequestMapping("/job")
public class JobController {
	
	@Value("${pdf.path}")
    private String pdfStoragePath;
	
	@Autowired
	private JobServiceImpl jobServiceImpl;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	@Autowired
	private JobTypeServiceImpl jobTypeServiceImpl;
	@Autowired
	private PaperFormatServiceImpl paperFormatServiceImpl;
	@Autowired
	private JobPaperServiceImpl jobPaperServiceImpl;
	@Autowired
	private PaperTypeServiceImpl paperTypeServiceImpl;
	@Autowired
	private PrintingMachineServiceImpl printingMachineServiceImpl;
	@Autowired
	private PrintTypeServiceImpl printTypeServiceImpl;
	@Autowired
	private JobColorCombinationServiceImpl jobColorCombinationServiceImpl;
	@Autowired 
	private PaperGrammageServiceImpl paperGrammageServiceImpl;
	@Autowired
	private BindingTypeserviceImpl bindingTypeserviceImpl;
	
//<--------------------- Collect datas form @Vincent------------------------------>
	@GetMapping("/displayform")
	public String displayFormInterface(Model model) {
		List<Customer> customerResult = customerServiceImpl.findAll();
		List<JobType> jobTypeResult = jobTypeServiceImpl.findAll();
		List<PaperFormat> paperFormatResult = paperFormatServiceImpl.findAll();
		List<JobPaper> jobPaperResult = jobPaperServiceImpl.findAll();
		List<PaperType>  paperTypeResult = paperTypeServiceImpl.listAll();
		List<PrintingMachine> printingMachineResult = printingMachineServiceImpl.listMachines();
		List<PrintType> printTypeResult = printTypeServiceImpl.findAll();
		List<JobColorCombination> jobColorCombinationResult = jobColorCombinationServiceImpl.findAll();
		List<PaperGrammage> paperGrammageResult = paperGrammageServiceImpl.findAll();
		List<BindingType> bindingTypeResult = bindingTypeserviceImpl.listAll();

		
		model.addAttribute("customers", customerResult);
		model.addAttribute("jobTypes", jobTypeResult);
		model.addAttribute("paperFormats", paperFormatResult);
		model.addAttribute("bindingTypes", bindingTypeResult);
		model.addAttribute("jobPaperResults", jobPaperResult);
		model.addAttribute("paperTypes", paperTypeResult);
		model.addAttribute("printingMachines", printingMachineResult);
		model.addAttribute("printTypes", printTypeResult);
		model.addAttribute("jobColorCombinations", jobColorCombinationResult);
		model.addAttribute("paperGrammages", paperGrammageResult);
		
		return "billing/display-job-form-interface";
	}

//<--------------------- Save data collected to the data base @Vincent ------------------------------>
	@PostMapping(value="/save", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> saveJob(@RequestBody JobDTO jobDTO,Model model){
		try {
			jobServiceImpl.saveJob(jobDTO);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("KO" + e.getMessage());
		}
	}

	@GetMapping("/list-job")
	public String listJob(Model model) {
		List<Job> result = jobServiceImpl.listAllJob();
		model.addAttribute("jobs", result);
		return "billing/list-job";
	}

////<--------------------- Generate a job pdf @Vincent ------------------------------>
	@GetMapping("/generate-pdf/{id}")
	public ResponseEntity<FileSystemResource> generatePdf(@PathVariable long id) throws IOException {
		 String pdfFile = createPdf(id);
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Disposition", "inline");
		 
		 return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new FileSystemResource(pdfFile));
	}	
	private String createPdf(Long id) throws FileNotFoundException {
		String pdf = "Hello.pdf";
		PdfWriter pdfWriter = new PdfWriter(pdf);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		try {

			document.close();
			
		} catch (Exception e) {
			throw new NotFoundException("Error", e);
		}
		return "okay";
	}


	@GetMapping("/viewJob/{id}")
	public String viewJobDetails(@PathVariable long id, Model model) {
		Job findJob = jobServiceImpl.findById(id).get();
		List<JobPaper> jobPapers = findJob.getJobPapers();
		JobPaper cover = jobPapers.remove(0);
		model.addAttribute("job",findJob);
		model.addAttribute("jobPapers",jobPapers);
		model.addAttribute("coverjobPapers",cover);

    return "/billing/view-job-profile";
	}
	
	@PostMapping(value="/delete/{id}")
	public void delete(@PathVariable long id) {
	  Optional<Job> job = jobServiceImpl.findById(id);
	    if (job.isPresent()) 
	    	jobServiceImpl.deleteById(id);
	}
	
	// to get the update page of job
	
		@GetMapping("/update-form/{id}")
		public String getUpdateForm(@PathVariable Long id, Model model) {
			List<Customer> customerResult = customerServiceImpl.findAll();
			List<JobType> jobTypeResult = jobTypeServiceImpl.findAll();
			List<PaperFormat> paperFormatResult = paperFormatServiceImpl.findAll();
			List<JobPaper> jobPaperResult = jobPaperServiceImpl.findAll();
			List<PaperType>  paperTypeResult = paperTypeServiceImpl.listAll();
			List<PrintingMachine> printingMachineResult = printingMachineServiceImpl.listMachines();
			List<PrintType> printTypeResult = printTypeServiceImpl.findAll();
			List<JobColorCombination> jobColorCombinationResult = jobColorCombinationServiceImpl.findAll();
			List<PaperGrammage> paperGrammageResult = paperGrammageServiceImpl.findAll();
			List<BindingType> bindingTypeResult = bindingTypeserviceImpl.listAll();
			
			Job existingJob = jobServiceImpl.findById(id).get();
			JobPaper existingJobPaper = existingJob.getJobPapers().remove(0);
			model.addAttribute("job", existingJob);
			model.addAttribute("customers", customerResult);
			model.addAttribute("jobTypes", jobTypeResult);
			model.addAttribute("paperFormats", paperFormatResult);
			model.addAttribute("bindingTypes", bindingTypeResult);
			model.addAttribute("coverJobPaper", existingJobPaper);
			model.addAttribute("jobPaperResults", jobPaperResult);
			model.addAttribute("paperTypes", paperTypeResult);
			model.addAttribute("printingMachines", printingMachineResult);
			model.addAttribute("printTypes", printTypeResult);
			model.addAttribute("jobColorCombinations", jobColorCombinationResult);
			model.addAttribute("paperGrammages", paperGrammageResult);
			
		    return "/billing/job-update-form";
		}
	
		@GetMapping("/estimate/{id}")
		public String getEstimateForm (@PathVariable long id, Model model) {
			Job findJob = jobServiceImpl.findById(id).get();
			model.addAttribute("job", findJob);		

			return "/billing/estimate/job-estimate";
		}
		
		@GetMapping("/generate/{id}")
		public String generateEstimate(@PathVariable long id,@RequestParam("quantities") String quantities, 
				@RequestParam("extraFee") int extraFee, @RequestParam("extraFeeDescription") String extraFeeDescription, Model model) {
			
			Job job = jobServiceImpl.findById(id).get();
			//Get and structure typesetting values
			List<String> typsettingActivities = new ArrayList<String>();
			if(job.isTypesettingByUs()) typsettingActivities.add("Typesetting by us");
			if(job.isDataSuppliedByCustomer()) typsettingActivities.add("Data Supplied by customer");
			if(job.isLayOutByUs()) typsettingActivities.add("Layout by us");
			if(job.isExistingPlate()) typsettingActivities.add("Has existing Plates");
			
			//Get and Structure printing 
			List<JobPaper> jobPapers = job.getJobPapers();
			JobPaper coverJobPaper = jobPapers.remove(0);
			
			//Get Finishing structure
			String finishingActivities = "";
			JobActivity jobActivity = job.getJobActivity();
			
			if(jobActivity.getXCross()>0) finishingActivities += "Signatures " + jobActivity.getXCross()+ " x cross-folded, " ;
			if(jobActivity.getXCreased()>0) finishingActivities += "Cover " + jobActivity.getXCreased()+ " x creased, " ;
			if(jobActivity.getLamination()>0) finishingActivities += "Cover " + jobActivity.getLamination()+ " side laminated, " ;
			if(jobActivity.getXWiredStiched()>0) finishingActivities += "Booklets " + jobActivity.getXWiredStiched()+ " x wire-stitched, " ;
			if(jobActivity.isSewn()) finishingActivities += "Booklets sewn, " ;
			if(jobActivity.isHandgather()) finishingActivities += "hand-gathered, " ;
			if(jobActivity.isSelloptaped()) finishingActivities += "sellotaped, " ;
			if(jobActivity.isTrimmed()) finishingActivities += "trimmed, " ;
			if(jobActivity.getXPerforated()>0) finishingActivities += "Job perforated " + jobActivity.getXPerforated()+ " times, " ;
			if(!(jobActivity.getGlueOption().isEmpty() && jobActivity.getGlueOption().isBlank())) finishingActivities += "Glue option is" + jobActivity.getGlueOption()+ ", " ;
			if(jobActivity.getBindingType()!=null) finishingActivities += "final binding: " + jobActivity.getBindingType().getName()+ " " ;
			
			

			String[] qty = quantities.split("@");
			//TO REMOVE SETTERS
			job.setFixCost(10000);
			job.setVariableCost(200000);
			List<EstimateDTO> estimates = new ArrayList<EstimateDTO>();
			for(int i=0; i<qty.length;i++) {
				EstimateDTO estimateDTO = new EstimateDTO();
				estimateDTO.setQuantity(Integer.parseInt(qty[i]));
				float totalPrice=  ( ((job.getVariableCost()/1000) * Integer.parseInt(qty[i])) +(job.getFixCost()+ extraFee));
				estimateDTO.setTotalPrice(totalPrice);
				estimateDTO.setUnitPrice(totalPrice/Integer.parseInt(qty[i]));
				estimates.add(estimateDTO);
			}

			Job findJob = jobServiceImpl.findById(id).get();
			model.addAttribute("job", findJob);
			model.addAttribute("estimates", estimates);
			model.addAttribute("typeSettingActivities", typsettingActivities);
			model.addAttribute("finishingActivities", finishingActivities);
			model.addAttribute("coverJobPaper", coverJobPaper);			
			model.addAttribute("contentJobPapers", jobPapers);
			return "/billing/estimate/generated-estimate-result";
		}
		
		
}
