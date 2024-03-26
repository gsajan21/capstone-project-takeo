package com.sajan.pms.service.implementation;

import com.sajan.pms.dto.OrderRequest;
import com.sajan.pms.dto.UpdateProductQtyRequest;
import com.sajan.pms.model.*;
import com.sajan.pms.repo.CartRepo;
import com.sajan.pms.repo.OrderRepo;
import com.sajan.pms.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final OrderRepo orderRepo;

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
    public Optional<Order> addCartItemsToOrder(OrderRequest orderRequest) {
        if(orderRequest == null){
            return Optional.empty();
        }

        Order newOrder = new Order();
        newOrder.setAddress(orderRequest.getAddress());
        newOrder.setOrderStatus(orderRequest.getOrderStatus());
        newOrder.setDateCreated(LocalDateTime.now());

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for(Product singleProduct: orderRequest.getProduct()){
            OrderDetails orderItem = new OrderDetails();
            orderItem.setQuantity(singleProduct.getQuantity());
            orderItem.setPrice(singleProduct.getPrice());
            orderItem.setTotalPrice(singleProduct.getPrice().multiply(BigDecimal.valueOf(singleProduct.getQuantity())));

            orderDetailsList.add(orderItem);
        }

        newOrder.setOrderDetails(orderDetailsList);
        return Optional.of(orderRepo.save(newOrder));
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

    // Helper method to calculate the total amount for the order
    private BigDecimal calculateTotalAmount(List<CartItem> cartItems) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            BigDecimal itemTotalPrice = cartItem.getTotalPrice();
            totalAmount = totalAmount.add(itemTotalPrice);
        }
        return totalAmount;
    }



}
