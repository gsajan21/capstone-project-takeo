package com.sajan.pms.service;

import com.sajan.pms.model.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> viewOrders();
}
