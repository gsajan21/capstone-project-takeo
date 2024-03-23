package com.sajan.pms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sajan.pms.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @OneToOne
    private Address address;
    private BigDecimal totalAmount;
    private LocalDateTime dateCreated;
    private OrderStatus orderStatus;
    @OneToMany
    private List<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
}

