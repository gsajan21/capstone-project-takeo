package com.sajan.pms.service;

import com.sajan.pms.dto.AddToCartRequest;
import com.sajan.pms.dto.OrderRequest;
import com.sajan.pms.dto.UpdateProductQtyRequest;
import com.sajan.pms.model.Address;
import com.sajan.pms.model.CartItem;
import com.sajan.pms.model.Order;

import java.util.List;
import java.util.Optional;


public interface CartService {
    Optional<List<CartItem>> viewAllCartItems();
    Optional<CartItem> getCartItemById(Integer cartId);
    Optional<CartItem> addToCart(AddToCartRequest cartRequest);
    Optional<CartItem> removeFromCartById(Integer cartId);
    Optional<CartItem> updateQtyOfCartItem(Integer cartId, UpdateProductQtyRequest updateProductQtyRequest);
}
