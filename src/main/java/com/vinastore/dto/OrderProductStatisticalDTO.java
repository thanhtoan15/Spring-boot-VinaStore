package com.vinastore.dto;

import com.vinastore.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductStatisticalDTO {
    private Integer id;
    private String nameProduct;
    private Integer price;
    private String img;
    private Integer quantity;
    private Categories category;
    private Long totalBuy;
}
