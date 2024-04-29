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
	
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}

//<------------------- Pagination -------------------->
	@GetMapping("/pagination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = paginationSize;
		Page<JobType> jobvType = jobTypeServiceImpl.findPaginatedJobType(pageNo, pageSize);
		List<JobType> result = jobvType.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", jobvType.getTotalPages());
		 model.addAttribute("totalItems", jobvType.getTotalElements());
		 model.addAttribute("allCustomers", result);
		 
		return "setting/job-type";
	}
}
