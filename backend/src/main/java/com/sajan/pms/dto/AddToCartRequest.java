package com.sajan.pms.dto;

import com.sajan.pms.model.Product;
import com.sajan.pms.model.User;
import java.math.BigDecimal;

public class AddToCartRequest {
    private User user;
    private Product product;
    private Integer quantity;
    private BigDecimal totalPrice;
}
