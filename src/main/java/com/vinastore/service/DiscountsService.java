package com.vinastore.service;

import com.vinastore.entities.Discounts;
import org.springframework.http.ResponseEntity;

public interface DiscountsService {

    ResponseEntity<?> getAllDiscount();

    ResponseEntity<?> getDiscountByName(String name);

    ResponseEntity<?> getDiscountById(Integer id);

    ResponseEntity<?> createDiscounts(Discounts entity);

    ResponseEntity<?> updateDiscounts(Discounts entity, Integer id);
}
