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

import com.ppp.billing.model.JobActivity;
import com.ppp.billing.serviceImpl.JobActivityServiceImpl;

@Controller
@RequestMapping("/jobactivity")
public class jobActivityController {
	
	@Value("${paginationSise}")
	private int jobActivitypagination;
	
	@Autowired
	private JobActivityServiceImpl jobActivityServiceImpl;

//<------------------- list with pagination -------------------->
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}
	
//<------------------- Pagination -------------------->	
	@GetMapping("/pagination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = jobActivitypagination;
		Page<JobActivity> jobActivity = jobActivityServiceImpl.findPaginatedJobActivity(pageNo, pageSize);
		List<JobActivity> result = jobActivity.getContent();
		model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", jobActivity.getTotalPages());
	    model.addAttribute("totalItems", jobActivity.getTotalElements());
		model.addAttribute("allJobAc", result);
	   return "setting/list-jobactivity";
	}

}
