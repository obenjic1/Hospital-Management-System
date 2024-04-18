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
	public ContentType update(ContentTypeDTO contentTypeDto, long id) {
		Optional<ContentType> contentType = contentTypeRepository.findById(id);
        if (contentType.isPresent()) {
        	ContentType updateContentType = contentType.get();
        	updateContentType.setName(contentTypeDto.getName());
        	return contentTypeRepository.save(updateContentType);
        	 
        	}
        return null;
	}

	@Override
	public Optional<ContentType> findById(long id) {
		
        return contentTypeRepository.findById(id);
	}
	
	@Override
	public Optional<ContentType> findByName(String name) {
		return contentTypeRepository.findByName(name);
	}

	@Override
	public ContentType save(ContentTypeDTO contentTypeDto) {
		ContentType newContentType = new ContentType();
		newContentType.setName(contentTypeDto.getName());
		return contentTypeRepository.save(newContentType);
	}

	
	@Override
	public void delete(long id) {
		contentTypeRepository.deleteById(id);
		
	}

}
