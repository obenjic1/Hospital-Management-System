package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppp.billing.Dto.ConsultationSubtypeDTO;
import com.ppp.billing.Dto.ConsultationSubtypeDto;
import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.ConsultationType;
import com.ppp.billing.repository.ConsultationSubTypeRepository;
import com.ppp.billing.service.ConsultationSubtypeService;
import com.ppp.billing.service.ConsultationTypeService;

@Controller
@RequestMapping("/admin/consultation-types")
public class ConsultationTypeController {

	 @Autowired
	    private ConsultationTypeService typeService;

	    @Autowired
	    private ConsultationSubtypeService subtypeService;
	    
	    @Autowired
	    private ConsultationSubTypeRepository consultationTypeRepository;
	    
	   

	    @GetMapping
	    public String showTypeForm(Model model) {
	        model.addAttribute("consultationType", new ConsultationType());
	        model.addAttribute("subtype", new ConsultationSubtype());
	        model.addAttribute("types", typeService.findAll());
	        return "Consultation/consultation-type";
	    }

	    @PostMapping("/add-type")
	    public String addType(@ModelAttribute ConsultationType consultationType) {
	        typeService.save(consultationType);
	        return "Consultation/consultation-type";
	    }

	    @PostMapping("/add-subtype")
	    public String addSubtype(@ModelAttribute ConsultationSubtype subtype, @RequestParam Long typeId) {
	        ConsultationType type = new ConsultationType();
	        type.setId(typeId);  
	        subtype.setConsultationType(type);
	        subtypeService.save(subtype);
	        return "Consultation/consultation-type";
}
	    
	    
	    @GetMapping("/consultation-subtypes/{typeId}")
	    @ResponseBody
	    public List<ConsultationSubtype> getSubtypesByType(@PathVariable Long typeId) {
//	    	ConsultationType consult = typeService.findById(typeId);
//	    	List<ConsultationSubtype> subype = consult.getSubtypes();
	    	List<ConsultationSubtype> list = subtypeService.findByConsultationTypeId(typeId);
	    	return list;
	    	}
	    	
	  
	    }
	        

