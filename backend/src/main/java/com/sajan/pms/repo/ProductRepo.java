package com.sajan.pms.repo;

import com.sajan.pms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    Optional<Product> getProductsByProductId(Integer productId);
//    Optional<Product> deleteProductByProductId(Integer productId);

}
