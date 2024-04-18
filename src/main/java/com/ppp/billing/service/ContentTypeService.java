package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ppp.billing.model.ContentType;
import com.ppp.billing.model.dto.ContentTypeDTO;
@Service
public interface ContentTypeService {


	Optional<ContentType> findByName(String name);
	ContentType update (ContentTypeDTO contentTypeDto, long id);
	Optional<ContentType>  findById(long id);
	List<ContentType> getAllContentTypes();
	ContentType save (ContentTypeDTO contentTypeDto);
	void delete(long id);
}
