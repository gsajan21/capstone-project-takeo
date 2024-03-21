package com.sajan.pms.repo;

import com.sajan.pms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Integer, Order> {
}