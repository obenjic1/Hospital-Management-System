package com.ppp.billing.service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.PayrollDto;
import com.ppp.billing.Dto.PayrollSummaryDto;
import com.ppp.billing.model.Payroll;
import com.ppp.billing.model.Staff;
import com.ppp.billing.repository.PayrollRepository;

@Service
public class PayrollService {

	    @Autowired
	    private PayrollRepository payrollRepository;
	    @Autowired StaffService staffService;

	    public Payroll createPayroll(PayrollDto payrollDto) {
	    	Payroll payroll = new Payroll();
	    	Staff staff = staffService.findById(payrollDto.getStaff()).get();
	    	payroll.setAllowances(payrollDto.getAllowances());
	    	payroll.setDeductions(payrollDto.getDeductions());
	    	payroll.setStaff(staff);
	    	payroll.setBasicSalary(payrollDto.getBasicSalary());
	    	payroll.setStatus("PENDING");
		 //   payroll.setPayDate(LocalDate.now());
		    payroll.setNetSalary(payrollDto.getNetSalary());

	        return payrollRepository.save(payroll);
	    }

	    public List<Payroll> getPayrollsByStaff(Long staffId) {
	        return payrollRepository.findByStaffId(staffId);
	    }

	    public List<Payroll> getAllPayrolls() {
	    	List<Payroll> payroll  = payrollRepository.findAll();
	    	payroll.sort(Comparator.comparing(Payroll::getId));
	    	Collections.reverse(payroll);
	        return payroll;
	    }

	
	    public List<PayrollSummaryDto> getPayrollSummary() {
	        List<Object[]> results = payrollRepository.getDepartmentPayrollSummary();
	        List<PayrollSummaryDto> summaries = new ArrayList<>();

	        for (Object[] row : results) {
	            String department = (String) row[0];
	            Integer month = (Integer) row[1];
	            Double total = (Double) row[2];

	            summaries.add(new PayrollSummaryDto(department, month, total));
	        }

	        return summaries;
	    }

		public Payroll getPayrollById(Long id) {
			// TODO Auto-generated method stub
			return payrollRepository.findById(id).get();
		}
		
		public ByteArrayInputStream generatePayslip(Payroll payroll) {
	     //   Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        
	      //      PdfWriter.getInstance(document, out);
	        //    document.open();
//
	            // Title
	       //     Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
	    //        Paragraph title = new Paragraph("Hospital Staff Payslip", titleFont);
	   //         title.setTextAlignment(Element.ALIGN_CENTER);
	   //         document.add(title);

	      //      document.add(new Paragraph(" "));
	      //      document.add(new Paragraph("Pay Date: " + payroll.getPayDate()));
	     //       document.add(new Paragraph("Status: " + payroll.getStatus()));
	    //        document.add(new Paragraph(" "));

	            // Staff Info
	          //  document.add(new Paragraph("Staff: " + payroll.getStaff().getFirstName() + " " + payroll.getStaff().getLastName()));
	        //    document.add(new Paragraph("Department: " + payroll.getStaff().getDepartment().getName()));
	      //      document.add(new Paragraph(" "));

	            // Salary Breakdown Table
	       //     PdfPTable table = new PdfPTable(2);
//	            table.setWidthPercentage(100);
//	            table.addCell("Base Salary");
//	            table.addCell(payroll.getBasicSalary().toString());
//	            table.addCell("Allowances");
//	            table.addCell(payroll.getAllowances().toString());
//	            table.addCell("Deductions");
//	            table.addCell(payroll.getDeductions().toString());
//	            table.addCell("Net Salary");
//	            table.addCell(payroll.getNetSalary().toString());
//
//	            document.add(table);
//
//	            document.close();

	      
	        
	        return new ByteArrayInputStream(out.toByteArray());
	    }
	
}
