package com.sajan.pms.dto;

import com.sajan.pms.model.Order;
import com.sajan.pms.model.Product;
import com.sajan.pms.model.User;
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
    private User user;
    private List<Order> orders;
}
