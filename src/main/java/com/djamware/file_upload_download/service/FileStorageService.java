package com.djamware.file_upload_download.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path fileStoragePath;

    @PostConstruct
    public void init() {
        try {
            fileStoragePath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(fileStoragePath);
            System.out.println("Upload directory initialized at: " + fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    public Path getFileStoragePath() {
        return fileStoragePath;
    }
}
