package com.sajan.pms.service;

import com.sajan.pms.dto.AddToCartRequest;
import com.sajan.pms.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Override
    public Optional<CartItem> addToCart(AddToCartRequest addToCartRequest) {
        return Optional.empty();
    }

    @Override
    public Optional<CartItem> removeFromCartById(Integer cartId) {
        return Optional.empty();
    }
}
