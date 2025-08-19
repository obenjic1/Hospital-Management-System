package com.ppp.user.service;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class FileDownloadService {

	 public File downloadFile(String fileName, String fileStoragePath) {
	        String filePath = fileStoragePath + "/" + fileName;
	        File file = new File(filePath);
	        if (file.exists()) {
	            return file;
	        } else {
	            return null;
	        }
	    }
}
