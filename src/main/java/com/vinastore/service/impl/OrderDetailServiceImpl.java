package com.vinastore.service.impl;

import com.vinastore.dto.OrderDetailDTO;
import com.vinastore.entities.OrderDetails;
import com.vinastore.repositories.OrderDetailRepositories;
import com.vinastore.service.OrderDetailService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepositories orderDetailRepositories;
    @Override
    public ResponseEntity<?> getAllOrderDetail() {
        List<OrderDetails> orderDetailDTO1 = orderDetailRepositories.findAll();
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get All OrderDetail Successfully ")
                .payload(orderDetailDTO1).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> createOderDetail(OrderDetails orderDetails) {
        OrderDetails orderDetailDTO2 =orderDetailRepositories.save(orderDetails);
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Create OrderDetail Successfully ")
                .payload(orderDetailDTO2).build();
        return ResponseEntity.status(200).body(bodyServer);
    }
}
