package com.vinastore.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "[orders]")
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date created_at;

    private Long total_amount;

    private String fullname;

    private String phone;

    private String city;

    private String district;

    private String wards;

    private String specific_address;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private OrderStatus status_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Accounts account_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Discounts discount_id;

    @OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;
}
