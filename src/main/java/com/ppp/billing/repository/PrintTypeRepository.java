package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.PrintType;
public interface PrintTypeRepository extends JpaRepository<PrintType,Long>  {

	PrintType findByName(String name);

}
