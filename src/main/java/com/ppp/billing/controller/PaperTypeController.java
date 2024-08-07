package com.ppp.billing.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.dto.PaperTypeDTO;
import com.ppp.billing.serviceImpl.PaperTypeServiceImpl;

@Controller
@RequestMapping("/papertype")
public class PaperTypeController {

//<------------------- Retrieve the value from application.properties  -------------------->	
	@Value("${paginationSise}")
	private int paginationSize;	
		
	@Autowired
	private PaperTypeServiceImpl paperTypeServiceImpl;

//<------------------- Display paper type form -------------------->	
	@PreAuthorize("hasAuthority('ROLE_MANAGE_SETINGS')")
	@GetMapping("/displayform")
	public String displayForm(Model model) {
		model.addAttribute("PaperTypeDTO", new PaperTypeDTO());
		
		return "billing/save-papertype";
	}
	
//<------------------- Save paper type -------------------->	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody PaperTypeDTO paperTypeDTO, Model model) {
		try {
			paperTypeServiceImpl.save(paperTypeDTO);
			return new ResponseEntity<String>("Success", HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Failes", HttpStatus.BAD_REQUEST);
		}
	}

//<------------------- find paper type to update -------------------->	
	@PreAuthorize("hasAuthority('ROLE_MANAGE_SETINGS')")
	@GetMapping("/toUpdate/{id}")
	public String findToUpdate(@PathVariable int id, Model model) {
		PaperType result = paperTypeServiceImpl.findById(id);
		model.addAttribute("update", result);
		return "billing/update-papertype";
	}

//<------------------- Update paper type -------------------->	
	@PreAuthorize("hasAuthority('ROLE_MANAGE_SETINGS')")
	@PostMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable int id, @RequestBody PaperTypeDTO paperTypeDTO) {		
		try {
			paperTypeServiceImpl.update(paperTypeDTO, id);
			return ResponseEntity.status(HttpStatus.OK).body("Success");			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur");
		}
	}

//<------------------- Find by name -------------------->
	@PreAuthorize("hasAuthority('ROLE_VIEW_SETINGS')")
	@GetMapping("/find/{id}")
	public String findByName(@PathVariable int id, Model model){
		PaperType result = paperTypeServiceImpl.findById(id);
		model.addAttribute("papertype", result);
		
		return "billing/update-paperty";
	}
	
//<------------------- list with pagination -------------------->	
	@PreAuthorize("hasAuthority('ROLE_VIEW_SETINGS')")
	@GetMapping("/list")
	public String list(Model model) {
		return pageable(1, model);
	}
	
//<------------------- Pagination -------------------->		
	@GetMapping("/page/{pageNo}")
	public String pageable(@PathVariable int pageNo, Model model ) {
		int pageSize = paginationSize;
		Page<PaperType> papertype = paperTypeServiceImpl.paginatedList(pageNo, pageSize);
		List<PaperType> allPaperType = papertype.getContent();
		int totalElement = allPaperType.size();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalElement", totalElement);
		 model.addAttribute("totalPages", papertype.getTotalPages());
		 model.addAttribute("totalItems", papertype.getTotalElements());
		 model.addAttribute("allPaperTypes", allPaperType);
		 
		return "billing/list-papertype";
	}

//<------------------- Find by id -------------------->	
	@PreAuthorize("hasAuthority('ROLE_MANAGE_SETINGS')")
	@GetMapping("/paper/{id}")
	public String findById(@PathVariable int id, Model model) {
		PaperType result = paperTypeServiceImpl.findById(id);
		model.addAttribute("papertype", result);
		
		return "billing/papertype-details";
	}
	
//<------------------- Delete -------------------->	
	@PreAuthorize("hasAuthority('ROLE_MANAGE_SETINGS')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id){		
		try {
			paperTypeServiceImpl.delete(id);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
}
