package com.ppp.billing.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppp.billing.Dto.CheckoutRequest;
import com.ppp.billing.Dto.DailyRevenueDTO;
import com.ppp.billing.model.Category;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Sale;
import com.ppp.billing.repository.CategoryRepository;
import com.ppp.billing.repository.SaleRepository;
import com.ppp.billing.repository.StockRequestRepository;
import com.ppp.billing.service.MedicineService;
import com.ppp.billing.service.PharmacyService;
import com.ppp.billing.service.SaleService;
import com.ppp.billing.service.StoreService;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {


         private final PharmacyService pharmacyService;
	
	
	     private final CategoryRepository categoryRepository;

	    private final StockRequestRepository stockRequestRepository;

	    @Autowired
	    private MedicineService medicineService;
	    
	    @Autowired
	    private SaleService saleService;
	    
	    @Autowired
	    private SaleRepository saleRepo;
	    
	   
	   
	    
	    
	    private final StoreService storeService;

	    public PharmacyController(StoreService storeService, StockRequestRepository stockRequestRepository,
	    		CategoryRepository categoryRepository, PharmacyService pharmacyService) {
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
	        
	        

	        // Category list
	        List<Category> categories = categoryRepository.findAll();
	        model.addAttribute("categories", categories);
	        
	        

	        // Medicines (Apply filters)
	        List<Medicine> medicines = storeService.listStoreMedicines(category, q);
	        model.addAttribute("medicines", medicines);

	        // echo filters back to view
	        
	        model.addAttribute("selectedCategory", category == null ? "All" : category);
	        model.addAttribute("q", q == null ? "" : q);
	        
	        model.addAttribute("amountSold", saleService.getTodaysTotalSales());
	        
	       


		return "pharmacy/pharmacy-list";
	}
	

	
  @RequestMapping(value = "/transfer", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<String> transferMedicine(@RequestParam Long medicineId, @RequestParam int quantity) throws Exception {
  	
      try {
      	  storeService.transferToPharmacy(medicineId, quantity);
         	return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			
			 return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);	}
  }
  
  	@PostMapping("/checkout")
	public  ResponseEntity<?> checkout(@RequestBody CheckoutRequest checkoutRequest) {
  		Sale ref = saleService.sales(checkoutRequest);   // returns invoice reference
  	    return ResponseEntity.ok(ref.getCustomerName());
		
	}
  	
  	 @GetMapping("/pharmacy")
     public String showDailyRevenue(Model model) {
  		 LocalDate date = LocalDate.now();
         List<DailyRevenueDTO> revenue = saleService.getTodaysRevenue();
         model.addAttribute("dailyRevenue", revenue);
         return "statistics/pharmacy"; // JSP view
     }

     @GetMapping("/pharmacy/details")
     public String showDailyDetails(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
         List<Sale> sales = saleService.getSalesByDate(date);
         model.addAttribute("sales", sales);
         model.addAttribute("date", date);
         return "statistics/pharmacy-details"; // JSP view
     }
}
