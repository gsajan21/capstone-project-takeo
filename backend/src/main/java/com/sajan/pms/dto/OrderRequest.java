package com.sajan.pms.dto;

import com.sajan.pms.enums.OrderStatus;
import com.sajan.pms.model.Address;
import com.sajan.pms.model.CartItem;
import com.sajan.pms.model.Product;
import com.sajan.pms.model.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Address address;
    private User user;
    private List<CartItem> cartItems;
    private LocalDateTime dateCreated;
    private OrderStatus orderStatus;
    private Integer quantity;
    private BigDecimal price;
}
