package com.ppp.billing.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.CartItemDto;
import com.ppp.billing.Dto.CheckoutRequest;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Sale;
import com.ppp.billing.model.SaleItem;
import com.ppp.billing.repository.ActivityLogRepository;
import com.ppp.billing.repository.MedicineRepository;
import com.ppp.billing.repository.SaleRepository;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;

@Service
public class SalesService {
    @Autowired private ActivityLogRepository logRepo;
    
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Transactional
    public Sale processSale(CheckoutRequest saleDto) {
    	
    	String userName = SecurityContextHolder.getContext().getAuthentication().getName();
       User pharmacist = userRepository.findByUsername(userName);

        Sale sale = new Sale();
        sale.setSaleDate(LocalDateTime.now());
        sale.setPharmacist(pharmacist);
        sale.setCustomerName(saleDto.getCustomerName());
        sale.setPaymentMethod(saleDto.getPaymentMethod());
        sale.setReceiptNumber(generateReceiptNumber() );
        sale.setSaleDate(LocalDateTime.now());
        

        BigDecimal total = BigDecimal.ZERO;
        int totalQuantity = 0;
        for (CartItemDto item : saleDto.getCartItems()) {
            Medicine medicine = medicineRepository.findById(item.getId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found"));

            if (medicine.getPharmacyQuantity() < item.getQty()) {
                throw new RuntimeException("Not enough stock for " + medicine.getName());
            }

            // update stock
            medicine.setPharmacyQuantity(medicine.getPharmacyQuantity() - item.getQty());
            medicine.addTracking("SOLD", "sold : " + item.getQty() + " of " + item.getName() + " at the pharmacy to :" + sale.getCustomerName()   );
            medicine.setQuantity(medicine.getQuantity() - item.getQty());

            medicineRepository.save(medicine);

            // create SaleItem
            SaleItem saleItem = new SaleItem();
            saleItem.setMedicine(medicine);
            saleItem.setQuantity(item.getQty());

            BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQty()));
            saleItem.setSubtotal(subtotal);

            sale.addItem(saleItem);

            total = total.add(subtotal);
            totalQuantity=totalQuantity + saleItem.getQuantity();
        }
        sale.setQuantity(totalQuantity);
        sale.setTotal(total);
        

        return saleRepository.save(sale);
    }

		    private String generateReceiptNumber() {
		        LocalDate today = LocalDate.now();
		        String datePart = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		        // Find max receipt number for today from DB (assumes format RCPT-yyyyMMdd-XXXX)
		        String lastReceipt = saleRepository.findMaxReceiptNumberForDate(datePart);
		        int nextNumber = 1;
		
		        if (lastReceipt != null && lastReceipt.length() >= 15) {
		            // Extract the number part, e.g., RCPT20250809-0012 -> 12
		            String numberPart = lastReceipt.substring(13);
		            try {
		                nextNumber = Integer.parseInt(numberPart) + 1;
		            } catch (NumberFormatException e) {
		                // ignore, default to 1
		            }
		        }
		
		        // Format with leading zeros (e.g., 0001)
		        String numberStr = String.format("%04d", nextNumber);
		        return "RCPT" + datePart + "-" + numberStr;
		    }
		   
		    public Sale findById (Long id) {
		    	return saleRepository.findById(id).get();
		    }

			public  List<Sale>salesReport(Date sDate, Date eDate) {
				return saleRepository.findBySaleDateBetween(sDate,eDate);
			}
}
