package com.vinastore.service.impl;

import com.vinastore.entities.Discounts;
import com.vinastore.repositories.DiscountsRepositories;
import com.vinastore.service.DiscountsService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscountsServiceImpl implements DiscountsService {

    @Autowired
    private DiscountsRepositories discountsRepositories;
    @Override
    public ResponseEntity<?> getAllDiscount() {
        List<Discounts> discounts = discountsRepositories.findAll();
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get All Discounts Successfully ")
                .payload(discounts).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getDiscountByName(String name) {
        List<Discounts> discounts = discountsRepositories.getAllDiscountsByName(name);
        if(discounts != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Discounts By Name Successfully ")
                    .payload(discounts).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Discount not find")
                .payload(null).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getDiscountById(Integer id) {
        if(id == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Have a issue!!!!")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            Discounts discounts = discountsRepositories.findById(id).orElse(null);
            if(discounts != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Discounts By Id Successfully ")
                        .payload(discounts).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Discounts is not exit")
                        .build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
    }

    @Override
    public ResponseEntity<?> createDiscounts(Discounts entity) {
        Discounts discounts = new Discounts();
        discounts.setName(entity.getName());
        discounts.setDescription(entity.getDescription());
        discounts.setDiscount_percent(entity.getDiscount_percent());
        discounts.setCreated_at(new Date());
        discounts.setIs_active(true);
        discountsRepositories.save(discounts);
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Create Categories Successfully ")
                .payload(discounts).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> updateDiscounts(Discounts entity, Integer id) {
        Discounts discounts = discountsRepositories.findById(id).orElse(null);
        if(discounts != null){
            discounts.setName(entity.getName());
            discounts.setDescription(entity.getDescription());
            discounts.setDiscount_percent(entity.getDiscount_percent());
            discounts.setIs_active(true);
            discounts.setUpdated_at(new Date());
            discountsRepositories.save(discounts);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Update Discount Successfully ")
                    .payload(discounts).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Discount not exit!!!")
                .payload(null).build();
        return ResponseEntity.status(200).body(bodyServer);
    }
}
