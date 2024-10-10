package com.ppp.billing.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ppp.billing.model.JobType;
import com.ppp.billing.model.dto.JobTypeDTO;
import com.ppp.billing.serviceImpl.JobTypeServiceImpl;

@Controller
@RequestMapping("/jobtype")
public class JobTypeController {

//<------------------- Retrieve the value from application.properties  -------------------->		
	@Value("${paginationSise}")
	private int paginationSize;	

//<------------------- Injection of dependencies  -------------------->
	@Autowired
	private JobTypeServiceImpl jobTypeServiceImpl;
	
//	@GetMapping("/list-all")
//	public String list(Model model) {
//		return pagination(0, model);
//	}
	
	//<------------------- end Point to list all JobType -------------------->	
	@GetMapping("/list-job-type")
	public String list(Model model) {
		List<JobType> jobTypes = jobTypeServiceImpl.findAll();
		model.addAttribute("jobTypes", jobTypes);
		return "/billing/list-job-type";
	}
	
// end point to show the add jobType form 
	
	@GetMapping("/displayform")
    public String getAddForm(Model model ) {
		model.addAttribute("jobType", new JobType());
		return "/billing/save-jobType";
	}

	
	// saving a new jobType  	
	
	@PostMapping(value = "/add-jobType")
	public String save (@Validated @ModelAttribute("JobType") JobTypeDTO jobTypeDto) throws Exception {
		try {
		if(jobTypeDto.getId()==0) {
			jobTypeServiceImpl.save(jobTypeDto);
			return "/billing/list-job-type";}
		else {
			jobTypeServiceImpl.update(jobTypeDto);
			return "/billing/list-job-type";
		}
		} catch (Exception e) {
			e.printStackTrace();
			return "KO";
		}
	}

	//<--------------- get edit form  ---------------------->
		@GetMapping("/update/{id}")
		public String findOneMachine(@PathVariable long id, Model model) {
			JobType jobType = jobTypeServiceImpl.findById(id).get();
			model.addAttribute("jobType", jobType);
		    return "/billing/edit-jobtype";
		}
		
//<------------------- Pagination -------------------->
	@GetMapping("/page/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = paginationSize;
		Page<JobType> jobType = jobTypeServiceImpl.findPaginatedJobType(pageNo, pageSize);		
		List<JobType> result = jobType.getContent();
		int totalElement = result.size();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", jobType.getTotalPages());
		 model.addAttribute("totalItems", jobType.getTotalElements());
		 model.addAttribute("allJobtypes", result);
		 model.addAttribute("totalElement", totalElement);
		return "setting/list-job-type";
	}
	
	
}
