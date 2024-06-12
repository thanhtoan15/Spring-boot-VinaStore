package com.vinastore.service.impl;

import com.vinastore.entities.OrderStatus;
import com.vinastore.repositories.OrderStatusRepositories;
import com.vinastore.service.OrderStatusService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusRepositories orderStatusRepositories;
    @Override
    public ResponseEntity<?> getOrderStatsuById(Integer id) {
        OrderStatus result = orderStatusRepositories.findById(id).orElse(null);
        if(result != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get OrderStatus By Account Successfully")
                    .payload(result).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("OrderStatus not exit")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }
}
