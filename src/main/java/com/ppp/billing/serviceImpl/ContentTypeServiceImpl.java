package com.ppp.billing.serviceImpl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.ContentType;
import com.ppp.billing.repository.ContentTypeRepository;
import com.ppp.billing.service.ContentTypeService;

@Service
public class ContentTypeServiceImpl implements ContentTypeService {
	
	@Autowired
	ContentTypeRepository contentTypeRepository;
	
	
//	@Override
//	public ContentType update(ContentTypeDTO contentTypeDto, long id) {
//		Optional<ContentType> contentType = contentTypeRepository.findById(id);
//        if (contentType.isPresent()) {
//        	ContentType updateContentType = contentType.get();
//        	updateContentType.setName(contentTypeDto.getName());
//        	return contentTypeRepository.save(updateContentType);
//        	 
//        	}
//        return null;
//	}

	@Override
	public Optional<ContentType> findById(int id) {
		
        return contentTypeRepository.findById(id);
	}
	
	@Override
	public Optional<ContentType> findByName(String name) {
		return contentTypeRepository.findByName(name);
	}

//	@Override
//	public ContentType save(ContentTypeDTO contentTypeDto) {
//		ContentType newContentType = new ContentType();
//		newContentType.setName(contentTypeDto.getName());
//		return contentTypeRepository.save(newContentType);
//	}

	
	@Override
	public void delete(int id) {
		contentTypeRepository.deleteById(id);
		
	}

//<------------------------ list paginated ------------------------->	
	@Override
	public Page<ContentType> findAllpaginate(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return contentTypeRepository.findAll(pageable);
	}

}
