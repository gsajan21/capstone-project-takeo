package com.sajan.pms.dto;

import com.sajan.pms.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Integer cartId;
    private List<Product> products;
    private Double totalPrice;
    private LocalDateTime dateCreated;
}
