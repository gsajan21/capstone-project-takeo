package com.sajan.pms.service.implementation;

import com.sajan.pms.model.Order;
import com.sajan.pms.model.User;
import com.sajan.pms.repo.OrderRepo;
import com.sajan.pms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    @Override
    public Optional<List<Order>> viewAllOrders() {
        List<Order> allOrders = orderRepo.findAll();
        return Optional.of(allOrders);
    }

    @Override
    public Optional<Order> viewAllOrderByUser(User userCustomer) {
        return orderRepo.getOrdersByUser(userCustomer);
    }
}
