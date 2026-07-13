package com.careerBridge.careerBridge.service;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

@Service
public class FileStorageService {
    private final Path uploadPath = Paths.get("uploads/cvs");

    public FileStorageService() {
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory.", e);
        }
    }

    public String saveFile(MultipartFile file) {

        if(file.isEmpty()) {
            throw new IllegalArgumentException("Please upload a CV.");
        }

        String originalFileName= file.getOriginalFilename();

        if(originalFileName==null||originalFileName.isBlank()) {
            throw new IllegalArgumentException("Invalid file name.");
        }

        if (!originalFileName.toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed.");
        }

        String uniqueFileName = UUID.randomUUID()+"-"+originalFileName;
        Path destination = uploadPath.resolve(uniqueFileName);
        try {
            file.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file.", e);
        }

        return uniqueFileName;

    }

    public void deleteFile(String fileName) {
        Path filePath = uploadPath.resolve(fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file.", e);
        }
    }


}
