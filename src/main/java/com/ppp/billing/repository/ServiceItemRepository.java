package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.ServiceItem;
@Repository

public interface ServiceItemRepository   extends JpaRepository<ServiceItem, Long>{
	
    List<ServiceItem> findByCategory(String category);
    
    @Query(value = 
    	      "SELECT " +
    	      "  si.name AS service_name, " +
    	      "  COUNT(su.id) AS times_used, " +
    	      "  SUM(su.amount_charged) AS total_revenue " +
    	      "FROM service_usage su " +
    	      "JOIN service_item si ON su.service_item_id = si.id " +
    	      "WHERE MONTH(su.used_at) = MONTH(CURRENT_DATE()) " +
    	      "  AND YEAR(su.used_at) = YEAR(CURRENT_DATE()) " +
    	      "GROUP BY si.name " +
    	      "ORDER BY si.name",
    	      nativeQuery = true)
    	List<Object[]> getCurrentMonthServiceUsageStats();


}
