package com.vinastore.apis;

import com.vinastore.service.CloudinaryImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cloudinary/upload")
public class CloudinaryApi {

    private final CloudinaryImageService cloudinaryImageService;

    @PostMapping
    public ResponseEntity<?> uploadFile(
            @RequestParam("image")MultipartFile file)
            throws IOException {
        String upLoadFile = cloudinaryImageService.uploadFile(file);
        return new ResponseEntity<>(upLoadFile, HttpStatus.OK);
    }
}
