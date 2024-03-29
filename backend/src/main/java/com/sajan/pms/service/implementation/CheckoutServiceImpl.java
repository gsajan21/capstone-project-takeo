package com.sajan.pms.service.implementation;

import com.sajan.pms.dto.CheckoutRequest;
import com.sajan.pms.enums.OrderStatus;
import com.sajan.pms.model.*;
import com.sajan.pms.repo.*;
import com.sajan.pms.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final CartRepo cartRepo;
    private final OrderRepo orderRepo;
    private final OrderDetailsRepo orderDetailsRepo;
    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    @Override
    @Transactional
    public Optional<Order> checkout(CheckoutRequest request) {
        Optional<User> byId = userRepo.findById(request.getUserId());
        if(byId.isPresent()){
            User user = byId.get();
            Optional<List<CartItem>> allItemsByUser = cartRepo.findAllByUser(user);
            List<CartItem> cartItems = allItemsByUser.get();
            BigDecimal totalPrice = calculateTotalPrice(cartItems);

            Order newOrder = new Order();
            newOrder.setUser(user);
            List<OrderDetails> orderDetailsList = getOrderDetails(cartItems);

            newOrder.setTotalAmount(totalPrice);
            newOrder.setOrderStatus(OrderStatus.PENDING);
            newOrder.setDateCreated(LocalDateTime.now());
            Optional<Address> addressByUser = addressRepo.findAddressesByUserId(request.getUserId());
            newOrder.setAddress(addressByUser.get());
            orderRepo.save(newOrder);
            orderDetailsRepo.saveAll(orderDetailsList);

            clearCart(user); // clear the cart after placing the order

            return Optional.of(newOrder);
        } else return Optional.empty();

    }

    private static List<OrderDetails> getOrderDetails(List<CartItem> cartItems) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderDetails addOrderDetails = new OrderDetails();
            addOrderDetails.setProductId(cartItem.getProduct().getProductId());
            addOrderDetails.setProductName(cartItem.getProduct().getProductName());
            addOrderDetails.setProductDescription(cartItem.getProduct().getDescription());
            addOrderDetails.setQuantity(cartItem.getQuantity());
            addOrderDetails.setTotalPrice(cartItem.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            orderDetailsList.add(addOrderDetails);

        }
        return orderDetailsList;
    }

    private BigDecimal calculateTotalPrice(List<CartItem> cartItems) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            BigDecimal itemPrice = cartItem.getProduct().getPrice();
            BigDecimal itemTotalPrice = itemPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalPrice = totalPrice.add(itemTotalPrice);
        }
        return totalPrice;
    }

    private void clearCart(User user){
        cartRepo.deleteAllByUser(user);
    }
}
