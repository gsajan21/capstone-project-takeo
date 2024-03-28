package com.sajan.pms.repo;

import com.sajan.pms.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {
}
