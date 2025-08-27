package com.ppp.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.ServiceItem;
import com.ppp.billing.model.Tracking;
import com.ppp.billing.repository.StockRequestRepository;
import com.ppp.billing.service.ServiceItemService;

@Controller
@RequestMapping("/services")
public class ServiceItemController {

    private final StockRequestRepository stockRequestRepository;
	
	 @Autowired
	    private ServiceItemService serviceItemService;

    ServiceItemController(StockRequestRepository stockRequestRepository) {
        this.stockRequestRepository = stockRequestRepository;
    }

	  @GetMapping
	    public String listServiceItems(Model model) {
	        model.addAttribute("items", serviceItemService.getAllServices());
	        return "service-items/list-service"; 
	    }

	    @GetMapping("/new")
	    public String showCreateForm(Model model) {
	        model.addAttribute("item", new ServiceItem());
	        return "service-items/create-item";
	    }

	    @PostMapping
	    public ResponseEntity<String> createServiceItem(@ModelAttribute ServiceItem item) {
	        try {
	        	 serviceItemService.createServiceItem(item);
	            return new ResponseEntity<>("Success", HttpStatus.OK);
	        } catch (Exception e) {
	            System.err.println("Error saving payroll: " + e.getMessage());
	            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    @GetMapping("/view/{id}")
	    public String view(@PathVariable Long id, Model model) {
	        model.addAttribute("item", serviceItemService.getServiceById(id));
	        return "service-items/view";
	    }

	    @GetMapping("/edit/{id}")
	    public String showEditForm(@PathVariable Long id, Model model) {
	        model.addAttribute("item", serviceItemService.getServiceById(id));
	        return "service-items/edit-item";
	    }

	    @PostMapping("/update/{id}")
	    public ResponseEntity<String> updateServiceItem(@PathVariable Long id, @ModelAttribute ServiceItem item) {
	        try {
		        serviceItemService.updateServiceItem(id, item);
	            return new ResponseEntity<>("Success", HttpStatus.OK);
	        } catch (Exception e) {
	            System.err.println("Error saving payroll: " + e.getMessage());
	            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
	        }	    }

	    @GetMapping("/delete/{id}")
	    public String deleteServiceItem(@PathVariable Long id) {
	        serviceItemService.Disable(id);
	        return "redirect:/service-items";
	    }

	    @GetMapping("/history/{id}")
	    public String getHistory(@PathVariable Long id, Model model) {
	        model.addAttribute("tracking", serviceItemService.getServiceById(id).getTracking());
	        return "service-items/history";
	    }
}
