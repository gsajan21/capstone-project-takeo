package com.sajan.pms.service.implementation;

import com.sajan.pms.dto.CheckoutRequest;
import com.sajan.pms.enums.OrderStatus;
import com.sajan.pms.model.CartItem;
import com.sajan.pms.model.Order;
import com.sajan.pms.model.OrderDetails;
import com.sajan.pms.model.User;
import com.sajan.pms.repo.CartRepo;
import com.sajan.pms.repo.OrderDetailsRepo;
import com.sajan.pms.repo.OrderRepo;
import com.sajan.pms.repo.UserRepo;
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
            List<OrderDetails> orderDetailsList = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                OrderDetails addOrderDetails = new OrderDetails();
                addOrderDetails.setProduct(cartItem.getProduct());
                addOrderDetails.setQuantity(cartItem.getQuantity());
                addOrderDetails.setTotalPrice(cartItem.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
                orderDetailsList.add(addOrderDetails);
            }

            newOrder.setTotalAmount(totalPrice);
            newOrder.setOrderStatus(OrderStatus.PENDING);
            newOrder.setDateCreated(LocalDateTime.now());
            orderRepo.save(newOrder);
            orderDetailsRepo.saveAll(orderDetailsList);

            clearCart(user); // clear the cart after placing the order

            return Optional.of(newOrder);
        } else return Optional.empty();

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
