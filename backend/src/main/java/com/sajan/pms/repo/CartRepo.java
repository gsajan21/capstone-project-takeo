package com.sajan.pms.repo;

import com.sajan.pms.model.CartItem;
import com.sajan.pms.model.Product;
import com.sajan.pms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartItem, Integer> {

    Optional<List<CartItem>> findAllByUser(User user);
    void deleteAllByUser(User user);
}
