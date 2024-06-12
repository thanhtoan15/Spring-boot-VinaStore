package com.vinastore.service;

import com.vinastore.entities.Categories;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {

    ResponseEntity<?> getALlCategories();

    ResponseEntity<?> getCategoriesById(Integer id);

    ResponseEntity<?> createCategories(Categories entity);

    ResponseEntity<?> updateCategories(Categories entity,Integer id);

    ResponseEntity<?> deleteCategories(Integer id);
}
