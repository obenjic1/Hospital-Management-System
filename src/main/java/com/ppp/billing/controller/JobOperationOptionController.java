package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppp.billing.model.JobOperationOption;
import com.ppp.billing.serviceImpl.JobOperationOptionServiceImpl;


@Controller
@RequestMapping("/job-opr-opt")
public class JobOperationOptionController {

	@Value("${paginationSise}")
	private int jobOperOpaginationSize;
	
	@Autowired
	private JobOperationOptionServiceImpl jobOperationOptionServiceImpl;
	
//<------------------- Pagination -------------------->
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}
	
//<-------------------- Pagination ---------------------->
	@GetMapping("/pagination/{pageNo}")
	public String pagination(@RequestParam int pageNo, Model model) {
		int pageSize = jobOperOpaginationSize;
		Page<JobOperationOption> jobOperationOption = jobOperationOptionServiceImpl.findPaginatedJobOperationOption(pageNo, pageSize);
		List<JobOperationOption> result = jobOperationOption.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", jobOperationOption.getTotalPages());
		 model.addAttribute("totalItems", jobOperationOption.getTotalElements());
		 model.addAttribute("allOprOpt", result);

		return "setting/list-job-opr-opt";
	}
	
}
