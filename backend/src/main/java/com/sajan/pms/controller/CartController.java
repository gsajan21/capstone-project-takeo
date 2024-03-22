package com.sajan.pms.controller;

import com.sajan.pms.model.CartItem;
import com.sajan.pms.service.CartService;
import com.sajan.pms.service.implementation.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;
    @GetMapping("/all")
    public ResponseEntity<List<CartItem>> getAllCartItem(){
        return cartService.viewAllCartItems().map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.noContent().build());

    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem){
        return cartService.addToCart(cartItem).map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }
}
