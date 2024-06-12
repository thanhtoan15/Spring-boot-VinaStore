package com.vinastore.service.impl;

import com.vinastore.entities.Categories;
import com.vinastore.repositories.CategoriesRepositories;
import com.vinastore.service.CategoriesService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepositories categoriesRepositories;

    @Override
    public ResponseEntity<?> getALlCategories() {
        List<Categories> categories = categoriesRepositories.getAllCategories();
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get All Categories Successfully ")
                .payload(categories).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getCategoriesById(Integer id) {
        if(id == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Hava a issue!!!")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            Categories categories = categoriesRepositories.getCategoriesById(id);
            if(categories != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Categories By Id Successfully ")
                        .payload(categories).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Categories is not exit")
                        .payload(null).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
    }

    @Override
    public ResponseEntity<?> createCategories(Categories entity) {
        Categories categories = new Categories();
        categories.setName(entity.getName());
        categories.setDescription(entity.getDescription());
        categories.setCreated_at(new Date());
        categoriesRepositories.save(categories);
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Create Categories Successfully ")
                .payload(categories).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> updateCategories(Categories entity, Integer id) {
        Categories categories = categoriesRepositories.getCategoriesById(id);
        if(categories != null){
            categories.setName(entity.getName());
            categories.setDescription(entity.getDescription());
            categories.setUpdated_at(new Date());
            categoriesRepositories.save(categories);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Update Categories Successfully ")
                    .payload(categories).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Categories not exit")
                .payload(null).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> deleteCategories(Integer id) {
        Categories categories = categoriesRepositories.getCategoriesById(id);
        if(categories != null){
            categories.setDeleted_at(new Date());
            categoriesRepositories.save(categories);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Delete categories success").build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Categories is not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }
}
