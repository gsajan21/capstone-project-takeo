package com.sajan.pms.dto;

import com.sajan.pms.enums.OrderStatus;
import com.sajan.pms.model.CartItem;
import com.sajan.pms.model.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String address;
    private String paymentType;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private User user;
    private List<CartItem> cartItems;

}
