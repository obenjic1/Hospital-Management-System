package com.ppp.user.controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ppp.user.model.User;
import com.ppp.user.service.FileDownloadService;
import com.ppp.user.service.UserService;

@Controller
public class fileController {
	
	@Autowired
	private  FileDownloadService fileDownloadService; 
	@Autowired
	private UserService userService;
	
//<------------- Access control contained in application.properties ------------------->
	@Value("${file.storage.path}")
    private String fileStoragePath;
	
//<----------- Download the image via the access path contained in fileStoragePath -----------> 
//	@GetMapping("/download/{fileName}")
//	public void downloadFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
//       File fileResource = fileDownloadService.downloadFile(fileName, fileStoragePath);
//
//       String mimeType = URLConnection.guessContentTypeFromName(fileResource.getName());
//       response.setContentType(mimeType);
//       response.setHeader("Content-Disposition", String.format("attachement; filename=\""+fileResource.getName()+"\""));
//       InputStream inputStream = new BufferedInputStream(new FileInputStream(fileResource));
//       FileCopyUtils.copy(inputStream, response.getOutputStream());
//       inputStream.close();
//	}
//	
//<----------- Download the user authenticated image via the access path contained in fileStoragePath -----------> 
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/download-profile-image")
	public void downloadProfileImage(HttpServletResponse response) {
	    try {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String username = authentication.getName();
	        User user = userService.findUserByUsername(username);
	        String imageName = user.getImagePath(); 
	        String imagePath = fileStoragePath + "/" + imageName; 

	        Path path = Paths.get(imagePath);
	        if (Files.exists(path) && Files.isRegularFile(path)) {
	            String contentType = Files.probeContentType(path);

	            response.setContentType(contentType);
	            response.setHeader("Content-Disposition", "inline; filename=\"" + path.getFileName() + "\"");

	            Files.copy(path, response.getOutputStream());
	            response.getOutputStream().flush();
	        } else {
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	}
	
	

}
