package com.sajan.pms.service.implementation;

import com.sajan.pms.dto.AddToCartRequest;
import com.sajan.pms.dto.UpdateProductQtyRequest;
import com.sajan.pms.model.CartItem;
import com.sajan.pms.repo.CartRepo;
import com.sajan.pms.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    @Override
    public Optional<List<CartItem>> viewAllCartItems() {
        return Optional.of(cartRepo.findAll());
    }

    @Override
    public Optional<CartItem> addToCart(CartItem cartItem) {

        CartItem addToCart = new CartItem();
        BeanUtils.copyProperties(cartItem, addToCart);
        CartItem saveItem = cartRepo.save(addToCart);
        return Optional.of(saveItem);
    }

    @Override
    public Optional<CartItem> removeFromCartById(Integer cartId) {

        Optional<CartItem> byId = cartRepo.findById(cartId);
        if (byId.isPresent()) {
            CartItem removeCartItem = byId.get();
            cartRepo.delete(removeCartItem);
            return Optional.of(removeCartItem);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public Optional<CartItem> updateQtyOfCartItem(Integer cartId, UpdateProductQtyRequest updateProductQtyRequest) {
        Optional<CartItem> byId = cartRepo.findById(cartId);
        if(byId.isPresent()){
            CartItem updateCartItemQty = byId.get();
            updateCartItemQty.setQuantity(updateProductQtyRequest.getQuantity());
            cartRepo.save(updateCartItemQty);
            return Optional.of(updateCartItemQty);
        }else
            return Optional.empty();
    }


}
