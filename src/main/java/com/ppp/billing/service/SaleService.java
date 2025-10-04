package com.ppp.billing.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.CheckoutRequest;
import com.ppp.billing.Dto.DailyRevenueDTO;
import com.ppp.billing.Dto.DailySaleDTO;
import com.ppp.billing.model.CartItem;
import com.ppp.billing.model.Facture;
import com.ppp.billing.model.FactureItem;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.RefrenceNumberGenerator;
import com.ppp.billing.model.Sale;
import com.ppp.billing.model.SaleItem;
import com.ppp.billing.repository.MedicineRepository;
import com.ppp.billing.repository.SaleRepository;

@Service
public class SaleService {

	
	  @Autowired
	    private MedicineRepository medicineRepository;

	    @Autowired
	    private SaleRepository saleRepository;
	    
	    @Autowired
	    private FactureService factureService;
	    
	    @Autowired
	    private ConsultationTypeService consultationTypeService;
	    
	    @Transactional
	    public Sale sales (CheckoutRequest checkoutRequest) {
	        Sale sale = new Sale();
	    	 sale.setCustomerName(checkoutRequest.getCustomerName());
	         sale.setSaleDate(LocalDate.now());
	         List<SaleItem> items = new ArrayList<>();
	         BigDecimal Grandtotal = BigDecimal.ZERO;
	      //   BigDecimal total = BigDecimal.ZERO;
	         
	         for (CartItem item : checkoutRequest.getCartItems()) {
	             Medicine medicine = medicineRepository.findById(item.getMedicineId())
	                     .orElseThrow(() -> new RuntimeException("Medicine not found"));

	             BigDecimal subTotal;

	             if ("packet".equalsIgnoreCase(item.getUnitType())) {
	            	 subTotal = medicine.sellPackets(item.getQuantity());
	             } else {
	            	 subTotal =  medicine.sellUnits(item.getQuantity());
	             }


	             
	             medicineRepository.save(medicine);
	           

	             SaleItem saleItem = new SaleItem();
	             saleItem.setMedicine(medicine);
	             saleItem.setQuantity(item.getQuantity());
	             saleItem.setUnitType(item.getUnitType());
	             sale.setCustomerName(checkoutRequest.getCustomerName());
	             
	             saleItem.setPrice(item.getPrice());
	             saleItem.setSubtotal(subTotal);
	             saleItem.setSale(sale);
	             
	             items.add(saleItem);
	             Grandtotal = Grandtotal.add(subTotal);
	         }
	             sale.setItems(items);
	             sale.setTotalAmount(Grandtotal);

	             saleRepository.save(sale);

	             
	             /* 1.  create bare Facture  */
	             Facture facture = new Facture();
	             facture.setVisit(null);
	             facture.setTotalAmount(Grandtotal);
	             facture.setDiscount((Grandtotal.subtract(Grandtotal)).doubleValue());
	             facture.setNetAmount(Grandtotal);
	             facture.setAmountPaid(Grandtotal.subtract(Grandtotal));
	             facture.setBalance(Grandtotal.doubleValue());
	             facture.setStatus("PENDING");
	             facture.setCreatedDate(LocalDate.now());
	             facture.setReferenceNumber(RefrenceNumberGenerator.nextInvoiceRef());
	             facture.setCreatedDate(LocalDate.now());
	            factureService.saveFacture(facture);
	             sale.setFacture(facture);
	             
	             List<FactureItem> factItems = new ArrayList<>();
	             for (SaleItem item : items) {
	                 FactureItem fi = new FactureItem();
	                 fi.setDescription(item.getMedicine().getName()); 
	                 fi.setQuantity(item.getQuantity());
	                 fi.setPrice((item.getMedicine().getUnitPrice()).doubleValue());  
	                 if(item.getUnitType().equalsIgnoreCase("packet")) {
	                	  fi.setUnitPrice((item.getMedicine().getPacketPrice()).doubleValue());// frozen unit price
	                 }else {
	                	 fi.setUnitPrice((item.getMedicine().getUnitPrice()).doubleValue());// frozen unit price
	                 }
	               
	                 fi.setSubTotal(item.getSubtotal());     
	                 fi.setFacture(facture); 
	                 factItems.add(fi);
	                 
	             }
	             facture.setCustomerName(checkoutRequest.getCustomerName());
	             facture.setItems(factItems);
	             factureService.saveFacture(facture);
	             sale.setFacture(facture);
	             saleRepository.save(sale);

	             return sale;

	    }
	    
	    public List<DailyRevenueDTO> getTodaysRevenue() {
	        LocalDate today = LocalDate.now();
	        List<Object[]> raw = saleRepository.findRevenueForDate(today);

	        if (raw.isEmpty()) {
	            return Collections.singletonList(new DailyRevenueDTO(today, BigDecimal.ZERO));
	        }

	        Object[] r = raw.get(0);
	        return Collections.singletonList(new DailyRevenueDTO((LocalDate) r[0], (BigDecimal) r[1]));
	    }
	    public BigDecimal getTodaysTotalSales() {
	        return saleRepository.getTotalSalesByDate(LocalDate.now());
	    }

	    
	    
	    public List<Sale> getSalesByDate(LocalDate date) {
	        return saleRepository.findBySaleDate(date);
	    }
	    
	   
	    public BigDecimal getTotalSalesAmountThisWeek() {
	        BigDecimal result = saleRepository.getTotalSalesAmountThisWeek();
	        return result != null ? result : BigDecimal.ZERO;
	    }

	    public BigDecimal getTotalSalesAmountThisMonth() {
	        BigDecimal result = saleRepository.getTotalSalesAmountThisMonth();
	        return result != null ? result : BigDecimal.ZERO;
	    }
	    
}

