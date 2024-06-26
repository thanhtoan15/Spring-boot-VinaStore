package com.vinastore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String fullname;
    private String phone;
    private String city;
    private String district;
    private String wards;
    private String specificAddress;
    private List<OrderDetailDTO> orderDetails;
    private Integer accountId;
    private Integer discountId;
}
