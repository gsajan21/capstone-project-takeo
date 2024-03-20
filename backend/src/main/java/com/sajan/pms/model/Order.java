package com.sajan.pms.model;

import jakarta.persistence.*;

@Entity
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    private boolean shipped;
}
