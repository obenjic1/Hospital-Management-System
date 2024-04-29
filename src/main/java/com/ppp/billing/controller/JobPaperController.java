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

import com.ppp.billing.model.JobPaper;
import com.ppp.billing.serviceImpl.JobPaperServiceImpl;

@Controller
@RequestMapping("/jobpaper")
public class JobPaperController {
	
//	@Value("${page}")
//	private int initialPage;
	@Value("${paginationSise}")
	private int jobPaperPaginationSize;
	
	@Autowired 
	private JobPaperServiceImpl jobPaperServiceImpl;
	
//<----------------------- List with pagination ------------------------>
	@GetMapping("/list")
	public String list(Model model) {
//		int firstPage = initialPage;
		return pagination(1, model);
	}
	
//<---------------------- Pagination --------------------->
	@GetMapping("/pagination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = jobPaperPaginationSize;
		Page<JobPaper> jobPaper = jobPaperServiceImpl.findPaginatedJobPaper(pageNo, pageSize);
		List<JobPaper> result = jobPaper.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", jobPaper.getTotalPages());
		 model.addAttribute("totalItems", jobPaper.getTotalElements());
		 model.addAttribute("allJobPaper", result);
		 
		return "setting/list-jobpaper";
	}

}
