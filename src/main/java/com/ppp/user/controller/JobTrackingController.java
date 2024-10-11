package com.ppp.user.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.Invoice;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.model.JobTracking;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.repository.JobTrackingRepository;
import com.ppp.billing.serviceImpl.JobTrackingServiceImpl;
import com.ppp.user.model.User;

@Controller
@RequestMapping("/jobtracking")
public class JobTrackingController {

//<---------------- Application properties variable ---------------------->
	@Value("${paginationSise}")
	private int paginationSize;
	
	@Autowired
	private JobTrackingServiceImpl jobTrackingServiceImpl;
	@Autowired
	private JobTrackingRepository jobTrackingRepository;

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

	@GetMapping ("/profile")
	public String  profile(Model model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = jobTrackingServiceImpl.findConnectedUser(name);
		List<Job> jobs = jobTrackingServiceImpl.findByUser();
		double amount = 0;
		int count = jobs.size();
		for(Job job : jobs) {
			for (JobEstimate jobestimate : job.getJobEstimates()){
				 if(jobestimate.isInvoiced()) {
					for (EstimatePricing estimateP: jobestimate.getEstimatePricings()) {
						for(Invoice invoice : estimateP.getInvoices()) {
						if(estimateP.getInvoices()!=null) {
							amount = amount + invoice.getNetPayable();
						}
						}
					}
				 }
			}
		}
		Collections.reverse(jobs);
		model.addAttribute("user", user);
		model.addAttribute("jobs", jobs);
		model.addAttribute("count", count);
		model.addAttribute("Amount", amount);
	
		return "user/view-user-dashboard";
		
	}
}
