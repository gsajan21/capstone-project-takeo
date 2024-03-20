package com.sajan.pms.service;

import com.sajan.pms.dto.ProductDetails;
import com.sajan.pms.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> saveProduct(ProductDetails productDetails);
    Optional<List<Product>> getAllProducts();
    Optional<Product> getProductById(Integer productId);
    Optional<Product> updateProductById(Integer productId);

    Optional<Product> deleteProductById(Integer productId);
}
