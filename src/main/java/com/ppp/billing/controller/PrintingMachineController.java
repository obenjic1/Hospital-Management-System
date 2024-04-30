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
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;
import com.ppp.billing.serviceImpl.PrintingMachineServiceImpl;


@Controller
@RequestMapping("/machine")
public class PrintingMachineController {

	@Autowired
	private PrintingMachineServiceImpl printMachineServiceImp;
	
	// end point to show the list machine page 
	@GetMapping("/list")
    public String showListMachines(Model model ,String name) {
		List<PrintingMachine> machines=printMachineServiceImp.listMachines();
		model.addAttribute("machines", machines);
		return "/billing/list-machine";
	}
	
	// end point to show the add machine form 
	
	@RequestMapping("/add")
    public String getAddForm(Model model ) {
		model.addAttribute("PrintingMachine", new PrintingMachine());
		return "/billing/add-machine";
	}
	
	// end point to add a new machine and return the list-machine page 
	@PostMapping(value = "/add-machine")
	public String save (@Validated @ModelAttribute("PrintingMachine") PrintingMachineDTO machineDTO) throws Exception {
 		PrintingMachine newMachine = printMachineServiceImp.save(machineDTO);
 		if(newMachine.equals("error")) {
			throw new Exception("machine already exist or image cant be converted");
		}
		return "/billing/list-machine";
	}
	
	// find  a machine By Id
	@GetMapping("/viewMachine/{id}")
	public String findOneMachine(@PathVariable long id, Model model) {
		PrintingMachine findMachine = printMachineServiceImp.findById(id).get();
			if(findMachine == null) {
				return "errors/machine-not-found";
			}
			model.addAttribute("findMachine", findMachine);
	    return "/billing/view-machine-profile";
	}
	
	// to delete a Machine
	@PostMapping(value="/delete/{id}")
	public void removeById(@PathVariable long id) {
	  Optional<PrintingMachine> machine = printMachineServiceImp.findById(id);
	    if (machine.isPresent()) {
	      printMachineServiceImp.delete(id);
	    }
	    return ;
	  }
	
	// to get the update page of a machine
	@GetMapping("/update-form/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model) {
		PrintingMachine findMachine = printMachineServiceImp.findById(id).get();
		model.addAttribute("findMachine", findMachine);
	    return "/billing/update-machine";
	}
		
	// to update a Machine
	@PostMapping("/update/{id}")
	public ResponseEntity<String> updateMachine(@PathVariable long id, @RequestParam PrintingMachine machine){
		try {
			printMachineServiceImp.update(machine, id);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}
