package com.vinastore.service;

import org.springframework.http.ResponseEntity;

public interface OrderStatusService {

    ResponseEntity<?> getOrderStatsuById(Integer id);
}
