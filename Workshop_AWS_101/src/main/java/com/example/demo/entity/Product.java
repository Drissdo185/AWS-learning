package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    private String id;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "category", length = 30)
    private String category;

    @ColumnDefault("true")
    @Column(name = "in_stock")
    private Boolean inStock;


}