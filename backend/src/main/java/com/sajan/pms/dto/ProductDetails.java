package com.sajan.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;
    private String images;
    private String category;
}
