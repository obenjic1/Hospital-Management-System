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

import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.serviceImpl.JobColorCombinationServiceImpl;

@Controller
@RequestMapping("/job-color-combinaisation")
public class JobColorCombinationController {

	@Value("${paginationSise}")
	private int jobColorCombinationPaginationSize;
	
	@Autowired
	private JobColorCombinationServiceImpl jobColorCombinationServiceImpl;
	
//<------------------------ List with pagination ---------------------------->
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}

//<------------------------ Pagination ---------------------------->	
	@GetMapping("pagination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = jobColorCombinationPaginationSize;
		Page<JobColorCombination> jobColorCombination = jobColorCombinationServiceImpl.pagination(pageNo, pageSize);
		List<JobColorCombination> result = jobColorCombination.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", jobColorCombination.getTotalPages());
		 model.addAttribute("totalItems", jobColorCombination.getTotalElements());
		 model.addAttribute("allJobColorCombination", result);
		 
		return "setting/job-color-combinaisation";
	}
}
