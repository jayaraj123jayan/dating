package com.suu.service.profile.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface StorageService {
    String uploadFile(MultipartFile file, String filename) throws IOException;

    byte[] downloadFile(String fileName) throws IOException;
}
