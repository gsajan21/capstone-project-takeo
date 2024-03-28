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
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private BigDecimal totalPrice;
}
