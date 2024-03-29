package com.sajan.pms.controller;

import com.sajan.pms.dto.AddToCartRequest;
import com.sajan.pms.dto.OrderRequest;
import com.sajan.pms.dto.UpdateProductQtyRequest;
import com.sajan.pms.model.CartItem;
import com.sajan.pms.model.Order;
import com.sajan.pms.service.implementation.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    @GetMapping("/{cartId}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Integer cartId){
        return cartService.getCartItemById(cartId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody AddToCartRequest cartRequest){
        System.out.println(cartRequest);
        return cartService.addToCart(cartRequest).map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }

    /*
     /checkout/{userId}
     fetch userCartItem from carts table using the id of user
     perform necessary calculations
     save the records in order table,
     after saving the data in order table, save the product_order
     */


    @DeleteMapping("/{cartId}")
    public ResponseEntity<CartItem> removeItemById(@PathVariable Integer cartId){
        return cartService.removeFromCartById(cartId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{cartId}")
    public ResponseEntity<CartItem> updateItemByCartId(@PathVariable Integer cartId, UpdateProductQtyRequest request){
        return cartService.updateQtyOfCartItem(cartId, request).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
