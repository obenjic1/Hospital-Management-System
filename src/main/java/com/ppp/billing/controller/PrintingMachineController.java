package com.ppp.billing.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;
import com.ppp.billing.serviceImpl.PrintingMachineServiceImpl;


@Controller
@RequestMapping("/machine")
public class PrintingMachineController {

	@Autowired
	private PrintingMachineServiceImpl printMachineServiceImp;
	
	// end point to show the list machine page 
	
	@GetMapping("/getListPage")
    public String showListMachines(Model model ,String name) {
		List<PrintingMachine> machines=printMachineServiceImp.listMachines();
		model.addAttribute("machines", machines);
		return "billing/list-machine";
		
	}
	// end point to show the add machine form 
	
	@RequestMapping("/getAddForm")
    public String getAddForm(Model model ) {
		model.addAttribute("PrintingMachine", new PrintingMachine());
		return "billing/add-machine";
		
	}
	
	// end point to add a new machine and return the list-machine page 
	@PostMapping(value = "/add-machine")
	public String save (PrintingMachineDTO machineDTO) throws Exception {
 		PrintingMachine newMachine = printMachineServiceImp.save(machineDTO);
 		if(newMachine.equals("error")) {
			throw new Exception("machine already exist or image cant be converted");
		}
		return "redirect /getListPage";
	}
	
	// find  a machine By Id
	@GetMapping("/viewMachine/{id}")
	public String findOneMachine(@PathVariable long id, Model model) {
		PrintingMachine findMachine = printMachineServiceImp.findById(id).get();
			if(findMachine == null) {
				return "errors/machine-not-found";
			}
			model.addAttribute("findMachine", findMachine);
	    return "billing/view-machine-profile";
	}
	
	// to delete a Machine
	@PostMapping("/delete-machine/{id}")
	public void removeById(@PathVariable long id) {
	    PrintingMachine machine = printMachineServiceImp.findById(id).get();
	    if (machine == null) {
	      return ;
	    }
	    printMachineServiceImp.delete(id);
	    return ;
	  }
	// to get the update page of a machine
	@GetMapping("/update-form/{id}")
	public String getUpdateForm(@PathVariable long id, Model model) {
		PrintingMachine findMachine = printMachineServiceImp.findById(id).get();
			if(findMachine == null) {
				return "errors/machine-not-found";
			}
			model.addAttribute("findMachine", findMachine);
	    return "billing/update-machine";
	}
	// to update a Machine
	public String updateMachine(@RequestBody PrintingMachine printingMachine, @PathVariable long id) throws Exception {
		printMachineServiceImp.update(printingMachine, id) ;
	    return "redirect /getListPage";
	}
}
