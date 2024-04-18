package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.ContentType;


public interface ContentTypeRepository extends JpaRepository<ContentType, Long> {

	Optional<ContentType>  findByName(String name);
	Optional<ContentType>  findById(long id );

}
