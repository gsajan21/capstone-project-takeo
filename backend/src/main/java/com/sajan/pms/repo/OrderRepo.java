package com.sajan.pms.repo;

import com.sajan.pms.model.Order;
import com.sajan.pms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    Optional<List<Order>> getOrdersByUser(User user);
}
