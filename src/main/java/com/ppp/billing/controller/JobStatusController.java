package com.ppp.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import com.ppp.billing.model.JobStatus;
import com.ppp.billing.serviceImpl.JobStatusServiceImpl;

@Controller
@RequestMapping("/jobstatus")
public class JobStatusController {
	
	@Value("${paginationSise}")
	private int paginationSise;

	@Autowired
	private JobStatusServiceImpl jobStatusServiceImpl;
	
//<--------------------- List ------------------------->	
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}

//<--------------------- Pagination function ------------------------->
	@GetMapping("/pagination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = paginationSise;
		Page<JobStatus> jobStatus = jobStatusServiceImpl.findPaginatedJobStatus(pageNo, pageSize);
		List<JobStatus> result = jobStatus.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", jobStatus.getTotalPages());
		model.addAttribute("totalItems", jobStatus.getTotalElements());
		model.addAttribute("allStatus", result);
		
		return "setting/list-jobstatus";
	}

}
