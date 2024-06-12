package com.vinastore.service.impl;

import com.vinastore.constant.StatusOrder;
import com.vinastore.dto.*;
import com.vinastore.entities.*;
import com.vinastore.repositories.*;
import com.vinastore.service.EmailService;
import com.vinastore.service.OrderService;
import com.vinastore.service.OrderStatusService;
import com.vinastore.service.ProductsService;
import com.vinastore.utils.ResponseBodyServer;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepositories orderRepositories;

    @Autowired
    private AccountsRepositories accountsRepositories;

    @Autowired
    private OrderStatusRepositories orderStatusRepositories;

    @Autowired
    private ProductsRepositories productsRepositories;

    @Autowired
    private DiscountsRepositories discountsRepositories;

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseEntity<?> getAllOrder() {
        List<Orders> orders = orderRepositories.findAll();
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get All Order Successfully ")
                .payload(orders).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getAllOrderById(Integer id) {
        if(id == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Need enter id")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            Orders orders = orderRepositories.findById(id).orElse(null);
            if(orders != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Order By Id Successfully")
                        .payload(orders).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Order not exit")
                        .payload(null).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
    }

    @Override
    public ResponseEntity<?> getOrderByStatus(Integer id) {
        if(id == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Need enter id")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            List<Orders> orders = orderRepositories.getOrdersByStatus(id);
            if(orders != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Order By Status Successfully")
                        .payload(orders).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Status not exit")
                        .payload(null).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
    }

    @Override
    public ResponseEntity<?> getOrderByAccount(Integer id) {
        List<Orders> orders = orderRepositories.getOrderByAccount(id);
        if(orders != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Order By Account Successfully")
                    .payload(orders).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Account not exit")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }

    @Override
    public ResponseEntity<?> getOrderByAccountAndStatus(Integer id, String status) {
        List<Orders> orders = orderRepositories.getAllOrderByIdAccountAndIdStatus(id,status);
        if(orders != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Order By Account And Status Successfully")
                    .payload(orders).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Order not exit")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }

    @Override
    public ResponseEntity<?> getOrderByStatusAndCount() {
        List<OrderStatusStatisticalDTO> orders = orderRepositories.getOrderByStatusAndCount();
        if(orders != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Order By Status And Total Successfully")
                    .payload(orders).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Order not exit")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }

    @Override
    public ResponseEntity<?> getOrderByYear(String year) {
        List<OrderYearStatisticalDTO> result = orderRepositories.getAllOrderByYear(year);
        if(result != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Order By Year Successfully")
                    .payload(result).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Year not exit")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }

    @Override
    public ResponseEntity<?> getTopProduct() {
        List<OrderProductStatisticalDTO> result = orderRepositories.getTopProduct();
        if(result != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Top Product Successfully")
                    .payload(result).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Have a issue")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }

    @Override
    public ResponseEntity<?> getTopAccount() {
        List<OrderAccountStatisticalDTO> result = orderRepositories.getTopAccount();
        if(result != null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Top Account Successfully")
                    .payload(result).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Have a issue")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }

    @Override
    public ResponseEntity<?> setStatusOrderShipping(Integer id) {
        Orders order = orderRepositories.findById(id).orElse(null);
        if(order != null){
            if(order.getStatus_id().getId().equals(StatusOrder.CHO_XAC_NHAN)){
                OrderStatus orderStatus = orderStatusRepositories.findById(StatusOrder.DANG_GIAO).orElse(null);
                order.setStatus_id(orderStatus);
                orderRepositories.save(order);
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Set Status Order Shipping Successfully")
                        .payload(order).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Status Order not wait for confirmation")
                        .payload(order).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(404).message("Not found id! "+order.getId())
                .payload(null).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> setStatusOrderComplete(Integer id) {
        Orders order = orderRepositories.findById(id).orElse(null);
        if(order != null){
            if(order.getStatus_id().getId().equals(StatusOrder.DANG_GIAO)){
                OrderStatus orderStatus = orderStatusRepositories.findById(StatusOrder.DA_GIAO).orElse(null);
                order.setStatus_id(orderStatus);
                orderRepositories.save(order);
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Set Status Order Complete Successfully")
                        .payload(order).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Status Order not shipping")
                        .payload(order).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(404).message("Not found id! "+order.getId())
                .payload(null).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> setStatusOrderCancle(Integer id) {
        Orders orders = orderRepositories.findById(id).orElse(null);
        if(orders != null){
            OrderStatus orderStatus = orderStatusRepositories.findById(StatusOrder.DA_HUY).orElse(null);
            orders.setStatus_id(orderStatus);
            orderRepositories.save(orders);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Set Status Order Cancle Successfully")
                    .payload(orders).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Not found id! ")
                .payload(null).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    @Transactional
    public ResponseEntity<?> createOrder(OrderDTO orderDTO) {
        Orders orders = new Orders();
        orders.setCreated_at(new Date());
        orders.setFullname(orderDTO.getFullname());
        orders.setPhone(orderDTO.getPhone());
        orders.setCity(orderDTO.getCity());
        orders.setDistrict(orderDTO.getDistrict());
        orders.setWards(orderDTO.getWards());
        orders.setSpecific_address(orderDTO.getSpecificAddress());
        orders.setAccount_id(accountsRepositories.findById(orderDTO.getAccountId()).orElse(null));
        orders.setStatus_id(orderStatusRepositories.findById(StatusOrder.CHO_XAC_NHAN).orElse(null));

        List<OrderDetails> orderDetailList = new ArrayList<>();
        Long totalPrice = 0L;

        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setQuantity(detailDTO.getQuantity());
            orderDetails.setOrder_id(orders);

            Products products = productsRepositories.findById(detailDTO.getProductId()).orElse(null);
            if (products == null) {
                return ResponseEntity.status(400).body("Product ID " + detailDTO.getProductId() + " not found");
            }

            if (products.getQuantity() < detailDTO.getQuantity()) {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder()
                        .statusCode(400)
                        .message("Product quantity in your cart must be smaller than quantity in stock")
                        .payload(orders)
                        .build();
                return ResponseEntity.status(400).body(bodyServer);
            }

            Integer price = products.getPrice();
            Long total = (long) (detailDTO.getQuantity() * price);
            totalPrice += total;

            products.setQuantity(products.getQuantity() - detailDTO.getQuantity());
            productsRepositories.save(products);

            orderDetails.setAmount(total);
            orderDetails.setProduct_id(products);
            orderDetailList.add(orderDetails);
        }

        orders.setOrderDetails(orderDetailList);
        orders.setTotal_amount(totalPrice);

        if (orderDTO.getDiscountId() != null) {
            Discounts discounts = discountsRepositories.findById(orderDTO.getDiscountId()).orElse(null);
            if (discounts != null) {
                orders.setDiscount_id(discounts);
                orders.setTotal_amount(totalPrice * (100 - discounts.getDiscount_percent()) / 100);
            }
        }

        Orders savedOrder = orderRepositories.save(orders);

        // Sending email asynchronously
        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(() -> {
            try {
                String email = savedOrder.getAccount_id().getEmail();
                String username = savedOrder.getAccount_id().getUsername();
                String subject = "Thư cảm ơn ";
                String content = "Chào " + username + ",\n\n" +
                        "Xin chân thành cảm ơn bạn đã mua hàng tại cửa hàng của chúng tôi! Rất vui được phục vụ bạn và chúng tôi hy vọng rằng bạn đã có một trải nghiệm mua sắm thú vị và hài lòng với sản phẩm mà bạn đã chọn.\n" +
                        "Chúng tôi đánh giá cao sự tin tưởng của bạn và cam kết cung cấp dịch vụ tốt nhất cho khách hàng. Nếu bạn có bất kỳ câu hỏi, đề xuất hoặc phản hồi nào, hãy xin vui lòng liên hệ với chúng tôi. Đội ngũ chăm sóc khách hàng của chúng tôi sẽ sẵn lòng giúp đỡ bạn.\n" +
                        "Một lần nữa, xin chân thành cảm ơn bạn đã lựa chọn mua hàng tại cửa hàng của chúng tôi. Rất mong được phục vụ bạn trong tương lai.\n\n" +
                        "Trân trọng";
                emailService.sendEmail(subject, email, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ResponseBodyServer bodyServer = ResponseBodyServer.builder()
                .statusCode(200)
                .message("Congratulations, your order was successful")
                .payload(savedOrder)
                .build();
        return ResponseEntity.status(200).body(bodyServer);
    }
}
