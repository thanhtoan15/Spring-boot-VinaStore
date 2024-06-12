package com.vinastore.apis;

import com.vinastore.dto.OrderDTO;
import com.vinastore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("public/api/v1/orders")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<?> getAllOrder(){
        ResponseEntity<?> result = orderService.getAllOrder();
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id")Integer id){
        ResponseEntity<?> result = orderService.getAllOrderById(id);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<?> getAllOrderByStatus(@PathVariable("id")Integer id){
        ResponseEntity<?> result = orderService.getOrderByStatus(id);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getOrderByAccount(@PathVariable("id")Integer id){
        ResponseEntity<?> result = orderService.getOrderByAccount(id);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/account-status")
    public ResponseEntity<?> getOrderByAccountAndStatus(@RequestParam("id")Integer id,
                                                        @RequestParam("status")String status){
        ResponseEntity<?> result = orderService.getOrderByAccountAndStatus(id, status);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/status-count")
    public ResponseEntity<?> getOrderByStatusAndCount(){
        ResponseEntity<?> result = orderService.getOrderByStatusAndCount();
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/statis/{year}")
    public ResponseEntity<?> getOrderbyYear(@PathVariable("year")String year){
        ResponseEntity<?> result = orderService.getOrderByYear(year);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/top-products")
    public ResponseEntity<?> getTopProduct(){
        ResponseEntity<?> result = orderService.getTopProduct();
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/top-accounts")
    public ResponseEntity<?> getTopAccount(){
        ResponseEntity<?> result = orderService.getTopAccount();
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO){
        ResponseEntity<?> result = orderService.createOrder(orderDTO);
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping ("/status-shipping")
    public ResponseEntity<?> setStatusOrderShipping(@RequestParam("id")Integer id){
        ResponseEntity<?> result = orderService.setStatusOrderShipping(id);
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/status-complete")
    public ResponseEntity<?> setStatusOrderComplete(@RequestParam("id")Integer id){
        ResponseEntity<?> result = orderService.setStatusOrderComplete(id);
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/status-cancle")
    public ResponseEntity<?> setStatusOrderCancle(@RequestParam("id")Integer id){
        ResponseEntity<?> result = orderService.setStatusOrderCancle(id);
        return ResponseEntity.status(200).body(result);
    }
}
