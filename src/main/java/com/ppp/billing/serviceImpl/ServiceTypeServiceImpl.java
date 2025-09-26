package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.ServiceType;
import com.ppp.billing.repository.ServiceTypeRepository;
import com.ppp.billing.service.ServiceTypeService;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepository typeRepository;

    public ServiceTypeServiceImpl(ServiceTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public ServiceType saveServiceType(ServiceType type) {
        return typeRepository.save(type);
    }

    @Override
    public ServiceType getServiceTypeById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return typeRepository.findAll();
    }

    @Override
    public List<ServiceType> getByCategory(Long categoryId) {
        return typeRepository.findByCategoryId(categoryId);
    }

    @Override
    public void deleteServiceType(Long id) {
        typeRepository.deleteById(id);
    }
}
