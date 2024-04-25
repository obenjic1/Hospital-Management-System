package com.ppp.billing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppp.billing.model.JobActivityOption;
import com.ppp.billing.model.PrintingMachine;
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
		return "/billing/job-activity-options-list";
	}
	
	// get a page to add activity option
	@GetMapping("/add-activity")
	public String getAddFrom(Model model) {
		model.addAttribute("JobActivityOption",new JobActivityOption());
		return"/billing/add-job-activity-options";
	}
	// adding a new activity option
	@PostMapping("/add-activity")
	public String addActivity(@Validated @ModelAttribute("JobActivityOption") JobActivityOptionDTO jobActivityOptionDto) {
		JobActivityOption newActivity = jobActivityOptionServiceImp.save(jobActivityOptionDto);
			return "/billing/job-activity-options-list";
		}
	
	@GetMapping("/view/{id}")
	public String findOneMachine(@PathVariable long id, Model model) {
		JobActivityOption activity = jobActivityOptionServiceImp.findById(id).get();
			if(activity == null) {
				return "errors/machine-not-found";
			}
			model.addAttribute("activity", activity);
	    return "/billing/job-activity-option-details";
	}
	
	// code to delete an activity
		@PostMapping(value="/delete-activity/{id}")
		public void removeById(@PathVariable long id) {
		  Optional<JobActivityOption> activity = jobActivityOptionServiceImp.findById(id);
		    if (activity.isPresent()) {
		    	jobActivityOptionServiceImp.delete(id);
		    }
		    return ;
		  }
		
		// to get the update page of job activity options
		@GetMapping("/update-form/{id}")
		public String getForm(@PathVariable Long id, Model model) {
			JobActivityOption jobActivityOptions = jobActivityOptionServiceImp.findById(id).get();
			model.addAttribute("jobActivityOptions",jobActivityOptions);
			return "/billing/update-job-activity-options";
		}
		
		@PostMapping("/update/{id}")
		public ResponseEntity<String> updateJobActivityOptions(@PathVariable long id, @RequestParam JobActivityOption jobActivityOption){
			try {
				jobActivityOptionServiceImp.update(jobActivityOption, id);
				return new ResponseEntity<String>("Success", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
			}
		}
}
