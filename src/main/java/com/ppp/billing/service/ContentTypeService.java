package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.ppp.billing.model.ContentType;
@Service
public interface ContentTypeService {


	Optional<ContentType> findByName(String name);
	Optional<ContentType>  findById(int id);
	Page<ContentType> findAllpaginate(int pageNo, int pageSize);
	void delete(int id);
}
