package com.ppp.billing.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/file")
@CrossOrigin(origins="*")
public class DownloadController {
	@Value("${folder.controlSheet}")
	private String controlSheetFolder;
	@Value("${folder.estimate}")
	private String estimateFolder;
	
	@Autowired
	private ServletContext context;
	
	@GetMapping("/download")
	public void downloadFile(@RequestParam("file") String file, @RequestParam("dir") String resourceDir, HttpServletResponse response) throws IOException {
		String dir = "";
		switch (resourceDir) {
		case "folder.controlSheet":
			dir= controlSheetFolder;
			break;
		case "folder.estimate":
			dir= estimateFolder;
			break;

		default:
			throw new NotFoundException("File not found");
		}
		File resource= new File(dir+file);
		response.setHeader("Content-Type", context.getMimeType(file));
		response.setHeader("Content-Length", String.valueOf(resource.length()));
		response.setHeader("Content-Disposition", "inline,filename=\""+file+"\"");
		Files.copy(resource.toPath(), response.getOutputStream());
	}

}
