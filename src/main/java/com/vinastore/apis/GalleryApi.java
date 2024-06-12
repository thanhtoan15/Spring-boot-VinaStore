package com.vinastore.apis;

import com.vinastore.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("public/api/v1/gallery")
public class GalleryApi {

    @Autowired
    private GalleryService galleryService;

    @GetMapping
    public ResponseEntity<?> getGalleryById(@PathVariable("id")Integer id){
        ResponseEntity gallery = galleryService.getGalleryById(id);
        return ResponseEntity.status(200).body(gallery);
    }
}
