package com.vinastore.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[order_details]")
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Long amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnoreProperties
            ({"applications", "hibernateLazyInitializer", "orderDetails", "status_id", "account_id", "discount_id"})
    private Orders order_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"applications", "hibernateLazyInitializer", "category_id", "galleries", "reviews"})
    private Products product_id;
}
