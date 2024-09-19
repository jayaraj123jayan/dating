package com.suu.service.profile.service.impl;

import com.suu.service.profile.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.IOException;

@Component
public class MinioStorageService implements StorageService {

    @Autowired
    private S3Client s3Client;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file, String filename) throws IOException {
        s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build(),
            RequestBody.fromBytes(file.getBytes()));

        return filename;
    }

    @Override
    public byte[] downloadFile(String fileName) throws IOException {
        return s3Client.getObject(GetObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .build()).readAllBytes();
    }
}
