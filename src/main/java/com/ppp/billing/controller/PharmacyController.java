package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppp.billing.Dto.PharmacyStats;
import com.ppp.billing.Dto.StoreStats;
import com.ppp.billing.model.Category;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.repository.CategoryRepository;
import com.ppp.billing.repository.StockRequestRepository;
import com.ppp.billing.service.MedicineService;
import com.ppp.billing.service.PharmacyService;
import com.ppp.billing.service.StoreService;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {

         private final PharmacyService pharmacyService;
	
	
	     private final CategoryRepository categoryRepository;

	    private final StockRequestRepository stockRequestRepository;

	    @Autowired
	    private MedicineService medicineService;
	    
	   
	    
	    
	    private final StoreService storeService;

	    public PharmacyController(StoreService storeService, StockRequestRepository stockRequestRepository, CategoryRepository categoryRepository, PharmacyService pharmacyService) {
	        this.storeService = storeService;
	        this.stockRequestRepository = stockRequestRepository;
	        this.categoryRepository = categoryRepository;
	        this.pharmacyService = pharmacyService;
	    }
	    
	
	@GetMapping
    public String pharmacyHome( @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "q", required = false) String q,
            Model model) {
		
	    	
	    	// Stats (expiring within 30 days)
	        PharmacyStats stats = pharmacyService.computePharmacyStats(30);
	        model.addAttribute("stats", stats);

	        // Category list
	        List<Category> categories = categoryRepository.findAll();
	        model.addAttribute("categories", categories);
	        
	        

	        // Medicines (Apply filters)
	        List<Medicine> medicines = storeService.listStoreMedicines(category, q);
	        model.addAttribute("medicines", medicines);

	        // echo filters back to view
	        
	        model.addAttribute("selectedCategory", category == null ? "All" : category);
	        model.addAttribute("q", q == null ? "" : q);

		return "pharmacy/pharmacy-list";
	}
	
//  @PostMapping("/transfer")
  @RequestMapping(value = "/transfer", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<String> transferMedicine(@RequestParam Long medicineId, @RequestParam int quantity) throws Exception {
  	
      try {
      	  storeService.transferToPharmacy(medicineId, quantity);
         	return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			
			 return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);	}
  }
}
