package com.ppp.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.JobTracking;
import com.ppp.billing.serviceImpl.JobTrackingServiceImpl;

@Controller
@RequestMapping("/jobtracking")
public class JobTrackingController {

//<---------------- Application properties variable ---------------------->
	@Value("${paginationSise}")
	private int paginationSize;
	
	@Autowired
	private JobTrackingServiceImpl jobTrackingServiceImpl;

//<---------------- List ---------------------->
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}

//<---------------- Pagination ---------------------->
	@GetMapping("/pgination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = paginationSize;
		Page<JobTracking> jobTracking = jobTrackingServiceImpl.findPaginatedJobTracking(pageNo, pageSize);
		List<JobTracking> result = jobTracking.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", jobTracking.getTotalPages());
		 model.addAttribute("totalItems", jobTracking.getTotalElements());
		 model.addAttribute("alltracking", result);
		 
		return "setting/job-tracking";
	}

}
