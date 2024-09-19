package com.suu.service.profile.controller;

import com.suu.service.profile.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("v1/storage-service")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping(value = "save-object/{filename}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    private String saveFile(@RequestPart("file") MultipartFile object, @PathVariable String filename) throws IOException {
        return storageService.uploadFile(object, filename);
    }
    @GetMapping("get-object/{filename}")
    private byte[] getObject(@PathVariable String filename) throws IOException {
        return storageService.downloadFile(filename);
    }
}
