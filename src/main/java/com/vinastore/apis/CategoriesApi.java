package com.vinastore.apis;

import com.vinastore.entities.Categories;
import com.vinastore.service.CategoriesService;
import com.vinastore.service.impl.CategoriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("public/api/v1/categories")
public class CategoriesApi {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping()
    public ResponseEntity<?> getAllCategories(){
        ResponseEntity<?> categories = categoriesService.getALlCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriesById(@PathVariable("id")Integer id){
        ResponseEntity<?> categories = categoriesService.getCategoriesById(id);
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping()
    public ResponseEntity<?> createCategories(@RequestBody Categories entity){
        ResponseEntity<?> categories = categoriesService.createCategories(entity);
        return ResponseEntity.status(200).body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategories(@RequestBody Categories entity,
                                              @PathVariable Integer id){
        ResponseEntity<?> categories = categoriesService.updateCategories(entity,id);
        return ResponseEntity.status(200).body(categories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategories(@PathVariable("id")Integer id){
        ResponseEntity<?> categories = categoriesService.deleteCategories(id);
        return ResponseEntity.status(200).body(categories);
    }
}
