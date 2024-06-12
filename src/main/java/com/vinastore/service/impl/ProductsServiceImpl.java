package com.vinastore.service.impl;

import com.vinastore.entities.Categories;
import com.vinastore.entities.Products;
import com.vinastore.repositories.CategoriesRepositories;
import com.vinastore.repositories.ProductsRepositories;
import com.vinastore.service.CategoriesService;
import com.vinastore.service.ProductsService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepositories productsRepositories;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private CategoriesRepositories categoriesRepositories;

    @Override
    public ResponseEntity<?> getAllProduct() {
        List<Products> products = productsRepositories.findAll();
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get All Products Successfully ")
                .payload(products).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getProductById(Integer id) {
        if(id == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Need enter id")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            Products products = productsRepositories.findById(id).orElse(null);
            if(products != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Product By Id Successfully ")
                        .payload(products).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Product not exit").build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
    }

    @Override
    public ResponseEntity<?> getProductByCatetory(Integer id) {
        List<Products> products = productsRepositories.getAllProductByCategories(id);
        if(products != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Product By Category Successfully ")
                    .payload(products).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Id Category not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getProductByName(String name) {
        List<Products> result = productsRepositories.findProductByName(name);
        if(result != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Product By Name Successfully ")
                    .payload(result).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Name product not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getProductByPrice(Double priceMin, Double priceMax) {
        List<Products> results = productsRepositories.getProductByPrice(priceMin, priceMax);
        if(results != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Product By Price Successfully ")
                    .payload(results).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Price product not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> saveProduct(Products entity) {
        if (entity.getCategory_id() == null || entity.getCategory_id().getId() == null) {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder()
                    .statusCode(500)
                    .message("Value Categories is null")
                    .build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        entity.setCreated_at(new Date());
        Categories categories = categoriesRepositories.getCategoriesById(entity.getCategory_id().getId());
        entity.setCategory_id(categories);
        Products products = productsRepositories.save(entity);
        ResponseBodyServer bodyServer = ResponseBodyServer.builder()
                .statusCode(200)
                .message("Create Products Successfully")
                .payload(products)
                .build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> updateProduct(Products entity, Integer id) throws Exception{
        Optional<Products> optionalProducts = productsRepositories.findById(id);
        if(optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            products.setName_product(entity.getName_product());
            products.setDescription(entity.getDescription());
            products.setPrice(entity.getPrice());
            products.setImg(entity.getImg());
            products.setQuantity(entity.getQuantity());
            products.setUpdated_at(new Date());
            productsRepositories.save(products);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Update Product Successfully")
                    .payload(products).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Product not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> deleteProductById(Integer id) throws Exception{
        Optional<Products> optionalProducts = productsRepositories.findById(id);
        if(optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            products.setDeleted_at(new Date());
            productsRepositories.save(products);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Deleted Product Successfully")
                    .payload(products).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Product not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public Page<Products> pageProduct(Integer pageNumber, Integer pageSize) {
        Page<Products> products = productsRepositories.findAll(PageRequest.of(pageNumber,pageSize));
        return products;
    }

    @Override
    public Page<Products> getFilteredProducts(int page, int limit, String sorBy, String name, String category, Integer priceMax, Integer priceMin, String order) {
        PageRequest pageRequest =createPageRequest(page, limit, sorBy, order);
        return productsRepositories.findFiltedProduct(name, category, priceMin, priceMax, pageRequest);
    }

    private PageRequest createPageRequest(int page, int limit, String sortBy, String order){
        Sort.Direction direction = order.equalsIgnoreCase("asc") ?Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort;
        switch(sortBy){
            case "price":
                sort = Sort.by(direction, "price");
                break;
            case "createdAt":
                sort = Sort.by(direction, "created_at");
                break;
            case "view":
                sort = Sort.by(direction, "id");
                break;
            case "sold":
                sort = Sort.by(direction, "quantity");
                break;
            default:
                sort = Sort.by(direction, "created_at");
        }
        return PageRequest.of(page - 1, limit, sort);
    }
}
