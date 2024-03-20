package com.sajan.pms.controller;

import com.sajan.pms.dto.ProductDetails;
import com.sajan.pms.model.Product;
import com.sajan.pms.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts().orElse(Collections.emptyList());
        return ResponseEntity.status(HttpStatus.OK).body(productList);

    }
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDetails productDetails){
        Product savedProduct = productService.saveProduct(productDetails).orElseThrow(() ->
                new RuntimeException("Failed to save the product"));

        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId){
        Product product = productService.getProductById(productId).orElseThrow(() ->
                new NoSuchElementException("Product not found."));

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
