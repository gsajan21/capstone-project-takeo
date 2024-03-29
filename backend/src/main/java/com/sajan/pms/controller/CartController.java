package com.sajan.pms.controller;

import com.sajan.pms.dto.AddToCartRequest;
import com.sajan.pms.dto.CartResponse;
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
    @GetMapping("/admin/all")
    public ResponseEntity<List<CartItem>> getAllCartItems(){
        return cartService.viewAllCartItems().map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.noContent().build());

    }
    @GetMapping("/user/{userId}") // {userId}
    public ResponseEntity<List<CartItem>> getAllCartItemsById(@PathVariable Integer userId){
        return cartService.getAllCartItemsByUserId(userId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
