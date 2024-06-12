package com.vinastore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryImageService {

    String uploadFile(MultipartFile file)throws IOException;
}
