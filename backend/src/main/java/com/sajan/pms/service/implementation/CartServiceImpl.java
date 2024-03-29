package com.sajan.pms.service.implementation;

import com.sajan.pms.dto.AddToCartRequest;
import com.sajan.pms.dto.UpdateProductQtyRequest;
import com.sajan.pms.model.*;
import com.sajan.pms.repo.CartRepo;
import com.sajan.pms.repo.ProductRepo;
import com.sajan.pms.repo.UserRepo;
import com.sajan.pms.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;


    @Transactional(readOnly = true)
    @Override
    public Optional<List<CartItem>> viewAllCartItems() {
        List<CartItem> itemList = cartRepo.findAll();
        return Optional.of(itemList);
    }

    @Override
    public Optional<List<CartItem>> getAllCartItemsByUserId(Integer userId) {
        Optional<User> byId = userRepo.findById(userId);
        if(byId.isPresent())
            return cartRepo.findAllByUser(byId.get());
        else return Optional.empty();
    }

    @Override
    public Optional<CartItem> getCartItemById(Integer cartId) {
        return cartRepo.findById(cartId);
    }

    @Override
    public Optional<CartItem> addToCart(AddToCartRequest cartRequest) {
        if (cartRequest == null || cartRequest.getProductId() == null || cartRequest.getUserId() == null) {
            // Handle invalid input, such as missing product or user
            return Optional.empty();
        }

        Product product = productRepo.findById(cartRequest.getProductId()).get();
        User user = userRepo.findById(cartRequest.getUserId()).get();
        // Create a new CartItem instance and copy properties
        CartItem newCartItem = new CartItem();

        newCartItem.setProduct(product); // Ensure product reference is set properly
        newCartItem.setUser(user); // Ensure user reference is set properly
        newCartItem.setQuantity(cartRequest.getQuantity());

        // Save the new CartItem to the repository
        CartItem savedCartItem = cartRepo.save(newCartItem);

        return Optional.of(savedCartItem);
    }

    @Override
    public Optional<CartItem> removeFromCartById(Integer cartId) {

        Optional<CartItem> byId = cartRepo.findById(cartId);
        if (byId.isPresent()) {
            cartRepo.delete(byId.get());
            return byId;
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
