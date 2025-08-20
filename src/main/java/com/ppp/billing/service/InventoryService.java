package com.ppp.billing.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.ActivityLog;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.PharmacyItem;
import com.ppp.billing.model.StockRequest;
import com.ppp.billing.model.StoreItem;
import com.ppp.billing.repository.ActivityLogRepository;
import com.ppp.billing.repository.MedicineRepository;
import com.ppp.billing.repository.PharmacyItemRepository;
import com.ppp.billing.repository.StockRequestRepository;
import com.ppp.billing.repository.StoreItemRepository;
import com.ppp.user.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final StoreItemRepository storeItemRepository;

    private final UserServiceImpl userServiceImpl;

    @Autowired private StoreItemRepository storeRepo;
    @Autowired private PharmacyItemRepository pharmacyRepo;
    @Autowired private StockRequestRepository requestRepo;
    @Autowired private ActivityLogRepository logRepo;
    @Autowired private MedicineRepository  medicineRepo;
    
    public InventoryService() {
		this.storeItemRepository = null;
		this.userServiceImpl = new UserServiceImpl();
    }

    InventoryService( UserServiceImpl userServiceImpl, StoreItemRepository storeItemRepository) {
        this.userServiceImpl = userServiceImpl;
        this.storeItemRepository = storeItemRepository;
    }

    public List<Medicine> listMedicines(){ 
    	return medicineRepo.findAll(); }
    public List<StockRequest> pendingRequests(){ 
    	return requestRepo.findByFulfilledFalse(); }

    public void transferFromStoreToPharmacy(Long medId, int qty, String actor){
        StoreItem storeItem = storeRepo.findByMedicineId(medId).get();
        if(storeItem.getQuantity() < qty) throw new IllegalArgumentException("Not enough in store");
        storeItem.setQuantity(storeItem.getQuantity() - qty);
        storeRepo.save(storeItem);
        
        PharmacyItem ph = pharmacyRepo.findByMedicineId(medId).orElse(null);
        if(ph==null){ 
        ph = new PharmacyItem();
        ph.setMedicine(storeItem.getMedicine()); 
        ph.setQuantity(qty);}
        else ph.setQuantity(ph.getQuantity()+qty);
        pharmacyRepo.save(ph);
        ActivityLog log = new ActivityLog();
        log.setAction("Transfer "+qty+" of "+storeItem.getMedicine().getName()+" to pharmacy");
        log.setActor(actor); 
        log.setTimestamp(java.time.LocalDateTime.now());
        logRepo.save(log);
    }

    public void requestStockFromStore(Long pharmacyItemId, int qty, String actor){
    	PharmacyItem pi = pharmacyRepo.findById(pharmacyItemId).orElse(null);
    	StockRequest r = new StockRequest(); 
    	r.setPharmacyItem(pi); 
    	r.setRequestedQuantity(qty); 
    	r.setFulfilled(false);
        r.setRequestDate(java.time.LocalDateTime.now()); requestRepo.save(r);
        ActivityLog log = new ActivityLog(); log.setAction("Requested "+qty+" of "+pi.getMedicine().getName()); log.setActor(actor);
        log.setTimestamp(java.time.LocalDateTime.now()); logRepo.save(log);
    }

    public void fulfillRequest(Long requestId, String actor){
        StockRequest r = requestRepo.findById(requestId).orElse(null);
        if(r.isFulfilled()) 
        	return;
        transferFromStoreToPharmacy(r.getPharmacyItem().getMedicine().getId(), r.getRequestedQuantity(), actor);
        r.setFulfilled(true);
        requestRepo.save(r);
    }
}
