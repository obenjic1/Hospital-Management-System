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

import com.ppp.billing.model.PaperFormat;
import com.ppp.billing.serviceImpl.PaperFormatServiceImpl;

@Controller
@RequestMapping("paperformat/")
public class PaperFormatController {
	
//<------------------- Retrieve the value from application.properties  -------------------->	
	@Value("${paginationSise}")
	private int paginationSize;	
	
	@Autowired
	private PaperFormatServiceImpl paperFormatServiceImpl;
	
//<------------------- list Format -------------------->	
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}

//<------------------- Pagination -------------------->	
	@GetMapping("pagin/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = paginationSize;
		Page<PaperFormat> paperFormat = paperFormatServiceImpl.findAllWithPagination(pageNo, pageSize);
		List<PaperFormat> result = paperFormat.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", paperFormat.getTotalPages());
		model.addAttribute("totalItems", paperFormat.getTotalElements());
		model.addAttribute("allformat", result);
		
		return "setting/list-paper-format";
	}

}
