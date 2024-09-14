package com.example.demo.dto;

import com.example.demo.entity.Product;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link Product}
 */
@Data

public class ProductDto implements Serializable {
    String id;
    String productName;
    String productDescription;
    BigDecimal price;
    String category;
    Boolean inStock;
}