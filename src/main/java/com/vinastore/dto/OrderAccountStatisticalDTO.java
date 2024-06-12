package com.vinastore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAccountStatisticalDTO {
    private Integer id;
    private String email;
    private String fullname;
    private String img;
    private Long totalAmount;
}
