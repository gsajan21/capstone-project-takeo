package com.sajan.pms.service.implementation;

import com.sajan.pms.dto.OrderRequest;
import com.sajan.pms.enums.OrderStatus;
import com.sajan.pms.model.Order;
import com.sajan.pms.model.User;
import com.sajan.pms.repo.OrderRepo;
import com.sajan.pms.repo.UserRepo;
import com.sajan.pms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    @Override
    public Optional<List<Order>> getAllOrders() {
        List<Order> allOrders = orderRepo.findAll();
        return Optional.of(allOrders);
    }

    @Override
    public Optional<Order> getOrderById(Integer orderId) {
        return  orderRepo.findById(orderId);
    }

    @Override
    public Optional<Order> updateOrderById(Integer orderId, OrderRequest orderRequest) {
        Optional<Order> byId = orderRepo.findById(orderId);
        if(byId.isPresent()){
            Order updateOrder = new Order();
            BeanUtils.copyProperties(orderRequest, updateOrder);
            orderRepo.save(updateOrder);
            return Optional.of(updateOrder);
        } else
            return Optional.empty();
    }

    @Override
    public Optional<Order> cancelOrderById(Integer orderId) {
        Optional<Order> byId = orderRepo.findById(orderId);
        if(byId.isPresent()){
            Order cancelOrder  = byId.get();
            cancelOrder.setOrderStatus(OrderStatus.valueOf("CANCELLED"));
            return Optional.of(cancelOrder);
        }else
            return Optional.empty();
    }

    @Override
    public Optional<List<Order>> getOrdersByUserId(Integer userId) {
        Optional<User> byId = userRepo.findById(userId);
        if(byId.isPresent()){
            return orderRepo.getOrdersByUser(byId.get());
        } else return Optional.empty();
    }

}
