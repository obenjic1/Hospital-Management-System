package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.ContentType;
import com.ppp.billing.model.dto.ContentTypeDTO;
@Service
public interface ContentTypeService {


	ContentType findByName(String name);
	String updateContentType (ContentTypeDTO contentTypeDto, Long id);
	ContentType findById(Long id);
	List<ContentType> getAllContentTypes();
	String addContentType (ContentTypeDTO contentTypeDto);
	void deleteById(Long id);
}
