package com.ppp.billing.service;

import java.util.List;

import com.ppp.billing.model.ServiceCategory;

public interface ServiceCategoryService {
	 ServiceCategory saveCategory(ServiceCategory category);
	    ServiceCategory getCategoryById(Long id);
	    List<ServiceCategory> getAllCategories();
	    void deleteCategory(Long id);
	    
	    

}
