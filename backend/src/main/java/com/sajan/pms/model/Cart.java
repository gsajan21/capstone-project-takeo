package com.sajan.pms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    private BigDecimal price;
    private LocalDateTime date;
    private Integer paidAmount;
    private Boolean shipped;
    private Integer quantity;

}
