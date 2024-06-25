package com.ppp.user.service;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

//<------------- Access control contained in application.properties ------------------->
    @Value("${folder.storage.path}")
    private String fileStoragePath;

    @Value("${folder.user.images}")
    private String userImagePath;

    @Value("${folder.customer.images}")
    private String customerImagePath;

//<--- Store the image in the fileStorage folder, which contains the variable access path declared in application.properties --->
    public String storeFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = fileStoragePath + "/" + fileName;

        File destFile = new File(filePath);
        file.transferTo(destFile);

        return fileName;
    }

    public String storeUserFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = userImagePath + "/" + fileName;

        File destFile = new File(filePath);
        file.transferTo(destFile);

        return fileName;
    }

    public String storeCustomerFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = customerImagePath + "/" + fileName;

        File destFile = new File(filePath);
        file.transferTo(destFile);

        return fileName;
    }
}
