package com.ppp.billing.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.ServiceCategory;
import com.ppp.billing.repository.ServiceCategoryRepository;
import com.ppp.billing.service.ServiceCategoryService;

@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    private final ServiceCategoryRepository categoryRepository;

    public ServiceCategoryServiceImpl(ServiceCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ServiceCategory saveCategory(ServiceCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public ServiceCategory getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<ServiceCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
