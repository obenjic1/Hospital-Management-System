package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.JobType;
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
	
	@GetMapping("/list-all")
	public String list(Model model) {
		return pagination(0, model);
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
