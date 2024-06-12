package com.vinastore.service;

import org.springframework.http.ResponseEntity;

public interface GalleryService {

    ResponseEntity<?> getGalleryById(Integer id);
}
