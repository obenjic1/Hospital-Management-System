package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.ppp.billing.model.ContentType;
import com.ppp.billing.model.dto.ContentTypeDTO;
import com.ppp.billing.repository.ContentTypeRepository;
import com.ppp.billing.service.ContentTypeService;

public class ContentTypeServiceImpl implements ContentTypeService {
	
	@Autowired
	ContentTypeRepository contentTypeRepository;
	
	@Override
	public List<ContentType> getAllContentTypes() {
		return contentTypeRepository.findAll();
	}
	
	@Override
	public String updateContentType(ContentTypeDTO contentTypeDto, Long id) {
		Optional<ContentType> contentType = contentTypeRepository.findById(id);
        if (contentType.isPresent()) {
        	ContentType updateContentType = contentType.get();
        	updateContentType.setName(contentTypeDto.getName());
        	contentTypeRepository.save(updateContentType);
        	return "Content type updated";
        	}
        return "error";
	}

	@Override
	public ContentType findById(Long id) {
		Optional<ContentType> contentType = contentTypeRepository.findById(id);
        return contentType.orElse(null);
	}
	
	@Override
	public ContentType findByName(String name) {
		return contentTypeRepository.findByName(name);
	}

	@Override
	public String addContentType(ContentTypeDTO contentTypeDto) {
		ContentType newContentType = new ContentType();
		newContentType.setName(contentTypeDto.getName());
		contentTypeRepository.save(newContentType);
		return "new printtype added";
	}

	
	@Override
	public void deleteById(Long id) {
		contentTypeRepository.deleteById(id);
		
	}

}
