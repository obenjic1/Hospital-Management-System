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

import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.serviceImpl.PaperGrammageServiceImpl;

@Controller
@RequestMapping("/grammage")
public class PaperGrammageController {
	
//<------------------- Retrieve the value from application.properties  -------------------->	
	@Value("${paginationSise}")
	private int paginationSize;	
	
	@Autowired
	private PaperGrammageServiceImpl paperGrammageServiceImpl;
	
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(0, model);
	}

	@GetMapping("/page/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = paginationSize;
		Page<PaperGrammage> paperGrammage = paperGrammageServiceImpl.findPaginatedJobType(pageNo, pageSize);
		List<PaperGrammage> result = paperGrammage.getContent();
		int totalElement = result.size();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", paperGrammage.getTotalPages());
		 model.addAttribute("totalItems", paperGrammage.getTotalElements());
		 model.addAttribute("allgrammages", result);
		 model.addAttribute("totalElement", totalElement);
		 
		return "setting/list-paper-grammage";
	}

}
