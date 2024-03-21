package com.sajan.pms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;
    private String images;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
}
