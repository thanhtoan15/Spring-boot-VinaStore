package com.vinastore.service;

import com.vinastore.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductsService {

    ResponseEntity<?> getAllProduct();

    ResponseEntity<?> getProductById(Integer id);

    ResponseEntity<?> getProductByCatetory(Integer id);

    ResponseEntity<?> getProductByName(String name);

    ResponseEntity<?> getProductByPrice(Double priceMin, Double priceMax);

    ResponseEntity<?> saveProduct(Products entity);

    ResponseEntity<?> updateProduct(Products entity, Integer id) throws Exception;

    ResponseEntity<?> deleteProductById(Integer id) throws Exception;

    Page<Products> pageProduct(Integer pageNumber, Integer pageSize);

    Page<Products> getFilteredProducts(int page, int limit, String sorBy, String name, String category, Integer priceMax, Integer priceMin, String order);
}
