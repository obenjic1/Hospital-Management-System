package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.ServiceItem;
@Repository

public interface ServiceItemRepository   extends JpaRepository<ServiceItem, Long>{
	
    List<ServiceItem> findByCategory(String category);

}
