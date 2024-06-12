package com.vinastore.dto;

import lombok.Data;

@Data
public class OrderYearStatisticalDTO {
    private Integer month;
    private Long totalPrice;

    public OrderYearStatisticalDTO(Integer month, Long totalPrice) {
        this.month = month;
        this.totalPrice = totalPrice;
    }
}
