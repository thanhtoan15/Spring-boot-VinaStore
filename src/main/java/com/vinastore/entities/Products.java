package com.vinastore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[products]")
public class Products implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name_product;

    private String description;

    private Integer price;

    private String img;

    private Integer quantity;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_at;

    @Temporal(TemporalType.DATE)
    private Date updated_at;

    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Categories category_id;

    @JsonIgnore
    @OneToMany(mappedBy = "product_id")
    private List<Galleries> galleries;

    @JsonIgnore
    @OneToMany(mappedBy = "product_id")
    private List<Reviews> reviews;
}
