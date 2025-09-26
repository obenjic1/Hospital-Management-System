package com.ppp.billing.service;

import java.util.List;

import com.ppp.billing.model.ServiceType;

public interface ServiceTypeService {
	  ServiceType saveServiceType(ServiceType type);
	    ServiceType getServiceTypeById(Long id);
	    List<ServiceType> getAllServiceTypes();
	    List<ServiceType> getByCategory(Long categoryId);
	    void deleteServiceType(Long id);
}
