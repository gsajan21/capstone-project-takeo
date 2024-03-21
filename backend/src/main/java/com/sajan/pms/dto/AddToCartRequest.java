package com.sajan.pms.dto;

import com.sajan.pms.model.Product;
import com.sajan.pms.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequest {
    private User user;
    private Product product;
    private Integer quantity;
    private BigDecimal totalPrice;
}
