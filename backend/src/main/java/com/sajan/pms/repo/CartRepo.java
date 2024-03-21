package com.sajan.pms.repo;

import com.sajan.pms.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartItem, Integer> {

}
