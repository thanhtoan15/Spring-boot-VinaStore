package com.vinastore.service;

import com.vinastore.dto.OrderDetailDTO;
import com.vinastore.entities.OrderDetails;
import org.springframework.http.ResponseEntity;

public interface OrderDetailService {

    ResponseEntity<?> getAllOrderDetail();

    ResponseEntity<?> createOderDetail(OrderDetails orderDetails);
}
