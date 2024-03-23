package com.sajan.pms.controller;

import com.sajan.pms.dto.OrderRequest;
import com.sajan.pms.model.CartItem;
import com.sajan.pms.model.Order;
import com.sajan.pms.service.implementation.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*
     /checkout/{userId}
     fetch userCartItem from carts table using the id of user
     perform necessary calculations
     save the records in order table,
     after saving the data in order table, save the product_order
     */

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestBody OrderRequest orderRequest){
        Optional<Order> orderOptional = cartService.addCartItemsToOrder(orderRequest);
        return orderOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
