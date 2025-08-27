package com.ppp.billing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.ServiceItem;
import com.ppp.billing.repository.ServiceItemRepository;

@Service
public class ServiceItemService {
	@Autowired
    private ServiceItemRepository repo;

    public ServiceItem createServiceItem(ServiceItem serviceItem) {
    	serviceItem.addTracking("CREATED", "Registered this Service Item");
    	serviceItem.setStatus("Running");
        return repo.save(serviceItem);
    }

    public List<ServiceItem> getAllServices() {
    	
        return repo.findAll();
    }

    public ServiceItem getServiceById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void Disable(Long id) {
        repo.deleteById(id);
    }

	public ServiceItem updateServiceItem(Long id, ServiceItem updatedItem) {
		ServiceItem item = repo.findById(id).get();
		String oldP = item.getPrice().toString();
		String name = item.getName();
		item.setCategory(updatedItem.getCategory());
		item.setDescription(updatedItem.getDescription());
		item.setPrice(updatedItem.getPrice());
		item.setName(updatedItem.getName());
		item.addTracking("Updated", "Updated this Service Item old informations :" + oldP +" "+ name + " to : " + item.getPrice() +" "+ item.getName());

        return repo.save(item);
		
	}

}
