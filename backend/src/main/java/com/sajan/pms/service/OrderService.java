package com.sajan.pms.service;

import com.sajan.pms.dto.OrderRequest;
import com.sajan.pms.model.Order;
import com.sajan.pms.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<List<Order>> getAllOrders();
    Optional<Order> getOrderById(Integer orderId);
    Optional<Order> updateOrderById(Integer orderId, OrderRequest orderRequest);
    Optional<Order> cancelOrderById(Integer orderId);
    Optional<List<Order>> getOrdersByUserId(Integer userId);
}
