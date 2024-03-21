package com.sajan.pms.model;

import com.sajan.pms.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String address;
    private String paymentType;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<CartItem> cartItems;
}

