package com.sajan.pms.service;

import com.sajan.pms.dto.AddToCartRequest;
import com.sajan.pms.dto.UpdateProductQtyRequest;
import com.sajan.pms.model.CartItem;

import java.util.List;
import java.util.Optional;


public interface CartService {
    Optional<List<CartItem>> viewAllCartItems();
    Optional<CartItem> addToCart(CartItem cartItem);
    Optional<CartItem> removeFromCartById(Integer cartId);
    Optional<CartItem> updateQtyOfCartItem(Integer cartId, UpdateProductQtyRequest updateProductQtyRequest);
}
