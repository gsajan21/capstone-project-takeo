package com.sajan.pms.service;

import com.sajan.pms.model.Order;
import com.sajan.pms.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<List<Order>> viewAllOrders();
    Optional<Order> viewAllOrderByUser(User userCustomer);
}
