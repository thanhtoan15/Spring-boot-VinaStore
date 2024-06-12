package com.vinastore.service;

import com.vinastore.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> getAllOrder();

    ResponseEntity<?> getAllOrderById(Integer id);

    ResponseEntity<?> getOrderByStatus(Integer id);

    ResponseEntity<?> getOrderByAccount(Integer id);

    ResponseEntity<?> getOrderByAccountAndStatus(Integer id, String status);

    ResponseEntity<?> getOrderByStatusAndCount();

    ResponseEntity<?> getOrderByYear(String year);

    ResponseEntity<?> getTopProduct();

    ResponseEntity<?> getTopAccount();

    ResponseEntity<?> setStatusOrderShipping(Integer id);

    ResponseEntity<?> setStatusOrderComplete(Integer id);

    ResponseEntity<?> setStatusOrderCancle(Integer id);

    ResponseEntity<?> createOrder(OrderDTO orderDTO);
}
