package com.vinastore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[discount]")
public class Discounts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name discount is required")
    private  String name;

    private String description;

    @NotNull(message = "Discount_percent is required")
    private Integer discount_percent;

    private Boolean is_active;

    @Temporal(TemporalType.DATE)
    private Date created_at;

    @Temporal(TemporalType.DATE)
    private Date updated_at;

    @JsonIgnore
    @OneToMany(mappedBy = "discount_id")
    private List<Orders> orders;
}
