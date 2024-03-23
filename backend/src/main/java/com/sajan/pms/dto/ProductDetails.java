package com.sajan.pms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sajan.pms.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String images;
    private Category category;
}
