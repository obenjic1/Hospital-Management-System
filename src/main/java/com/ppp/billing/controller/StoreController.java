package com.ppp.billing.controller;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;
import com.ppp.billing.Dto.MedicineDto;
import com.ppp.billing.Dto.StoreStats;
import com.ppp.billing.model.Category;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.repository.CategoryRepository;
import com.ppp.billing.repository.StockRequestRepository;
import com.ppp.billing.service.AccountingService;
import com.ppp.billing.service.MedicineService;
import com.ppp.billing.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final AccountingService accountingService;

    private final CategoryRepository categoryRepository;

    private final StockRequestRepository stockRequestRepository;

    @Autowired
    private MedicineService medicineService;
    
    
    private final StoreService storeService;

    public StoreController(StoreService storeService, StockRequestRepository stockRequestRepository, CategoryRepository categoryRepository, AccountingService accountingService) {
        this.storeService = storeService;
        this.stockRequestRepository = stockRequestRepository;
        this.categoryRepository = categoryRepository;
        this.accountingService = accountingService;
    }
    /*

    @GetMapping
    public String storeHome(Model model) {
        model.addAttribute("medicines", storeService.getStoreMedicines());
        return "store/store-list";
    } */


    
    
    @GetMapping
    public String storeHome(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "q", required = false) String q,
            Model model
    ) {
    	
    	// Stats (expiring within 30 days)
        StoreStats stats = storeService.computeStoreStats(30);
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

        return "store/store-list"; 
    }

    @GetMapping("/add")
    public String addMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "store/store-add";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMedicine(@ModelAttribute MedicineDto medicine) {
        storeService.addMedicine(medicine);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PostMapping("/transfer")
    @RequestMapping(value = "/transfer", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> transferMedicine(@RequestParam Long medicineId, @RequestParam int quantity) throws Exception {
    	
        try {
        	  storeService.transferToPharmacy(medicineId, quantity);
           	return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			
			 return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);	}
    }

    // View all medicines in store
    @GetMapping("/list")
    public String viewStore(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        model.addAttribute("medicines", medicines);
        return "store/store-list"; // JSP page
    }

    @GetMapping("/searchMedicines")
    @ResponseBody
    public List<Medicine> searchMedicines(@RequestParam("query") String query) {
        return medicineService.findByNameContainingIgnoreCase(query);
    }
    

    // Save new medicine
    @PostMapping("/save")
    public String saveMedicine(@ModelAttribute MedicineDto medicine, RedirectAttributes redirectAttributes) {
        medicineService.saveMedicine(medicine);
        redirectAttributes.addFlashAttribute("success", "Medicine added successfully!");
        return "redirect:/store/list";
    }

    // Show edit medicine form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Medicine medicine = medicineService.getMedicineById(id);
        model.addAttribute("medicine", medicine);
     // Category list
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "store/store-form";
    }

    // Delete medicine
    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        medicineService.deleteMedicine(id);
        redirectAttributes.addFlashAttribute("success", "Medicine deleted successfully!");
        return "redirect:/store/list";
    }
    
    @GetMapping("/add-category")
    public String getCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "store/add-category";
        }
        @PostMapping("/add-category")
        public ResponseEntity<String> addCategory( @RequestParam String name, @RequestParam(value = "description", required = false) String description) {
    	Category cat = new Category();
    	cat.setDescription(description);
    	cat.setName(name);
    	categoryRepository.save(cat);
    
        return new ResponseEntity<>(HttpStatus.CREATED);
        }
    
        // edit new medicine
        @PostMapping("/edit/{id}")
        public ResponseEntity<String> editMedicine(@PathVariable("id") Long id ,  @ModelAttribute Medicine medicine) {
            medicineService.edit(id,medicine);
            return   new ResponseEntity<>(HttpStatus.CREATED);

        }
        
        
        @PostMapping("/add-quantity")
        public ResponseEntity<String> addMedicine(@RequestParam Long medicineId, @RequestParam int quantity,Model model) {
           try {
        	   
           	medicineService.addQuantity(medicineId,quantity);
           	
           	

           	return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			 return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);	}
           
            }
        
        @GetMapping("/check-name")
        public ResponseEntity<Boolean> checkMedicineName(@RequestParam String name) {
            return ResponseEntity.ok(medicineService.existsByName(name));
        }
        
        
        @GetMapping("/history/{id}")
        public String getHistory (@PathVariable Long id, Model model) {
        	Medicine med = medicineService.findById(id).get();
            model.addAttribute("meds", med);

        	return "store/history";
        	
        }
        
        
}
