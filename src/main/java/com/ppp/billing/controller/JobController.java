package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.Customer;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.JobType;
import com.ppp.billing.model.PaperFormat;
import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.JobColorCombinationDTO;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.model.dto.JobPaperDTO;
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
	
	@Autowired
	private JobServiceImpl jobServiceImpl;
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
	
	@PostMapping("/save")
	public String saveJob(JobDTO jobDTO, JobPaperDTO jobPaperDTO, JobColorCombinationDTO jobColorCombinationDTO,Model model) {
		Job newJob = jobServiceImpl.saveJob(jobDTO, jobPaperDTO, jobColorCombinationDTO);
		return "billing/list-job";
	}

	@PostMapping("/list-job")
	public String listJob(Model model) {
		List<Job> Jobs = jobServiceImpl.findall();
		model.addAttribute("jobs", Jobs);
		return "billing/list-job";
	}

}
