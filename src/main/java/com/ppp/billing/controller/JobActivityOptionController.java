package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ppp.billing.model.JobActivityOption;
import com.ppp.billing.model.dto.JobActivityOptionDTO;
import com.ppp.billing.serviceImpl.JobActivityOptionServiceImpl;

@Controller
@RequestMapping("activityOption")
public class JobActivityOptionController {
	
	@Autowired
	private JobActivityOptionServiceImpl jobActivityOptionServiceImp;
	
	// get a page of all the listed job activities
	@GetMapping("/list-activityOptions")
	public String showListPage(Model model,String name) {
	List<JobActivityOption> activities =  jobActivityOptionServiceImp.getAllJobActivityOptions();
		model.addAttribute("activities",activities);
		return "/billing/activity-list";
	}
	
	// get a page to add activity option
	@GetMapping("/add-activity")
	public String getAddFrom(Model model) {
		model.addAttribute("JobActivityOption",new JobActivityOption());
		return"/billing/add-activity-options";
	}
	// adding a new activity option
	@PostMapping("/add-activity")
	public String addActivity(@Validated @ModelAttribute("JobActivityOption") JobActivityOptionDTO jobActivityOptionDto) {
		JobActivityOption newActivity = jobActivityOptionServiceImp.save(jobActivityOptionDto);
			return "/billing/activity-list";
		}
		
	
}
