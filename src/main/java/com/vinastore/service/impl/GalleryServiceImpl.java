package com.vinastore.service.impl;

import com.vinastore.entities.Galleries;
import com.vinastore.repositories.GalleryRepositories;
import com.vinastore.service.GalleryService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepositories galleryRepositories;
    @Override
    public ResponseEntity<?> getGalleryById(Integer id) {
        if(id == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Hava a issue!!!")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            Galleries gallery = galleryRepositories.findById(id).orElse(null);
            if(gallery != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Gallery By Id Success")
                        .payload(null).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Gallery is not exit")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }
}
