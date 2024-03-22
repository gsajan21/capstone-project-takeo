package com.sajan.pms.service.implementation;

import com.sajan.pms.dto.ProductDetails;
import com.sajan.pms.model.Product;
import com.sajan.pms.repo.ProductRepo;
import com.sajan.pms.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Optional<Product> saveProduct(ProductDetails productDetails) {
        Product saveProduct = new Product();
        BeanUtils.copyProperties(productDetails, saveProduct);
        return Optional.of(productRepo.save(saveProduct));
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        return Optional.of(allProducts);
    }

    @Override
    public Optional<Product> getProductById(Integer productId) {
        return productRepo.getProductsByProductId(productId);

    }


    @Override
    public Optional<Product> updateProductById(Integer productId) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteProductById(Integer productId) {
        return Optional.empty();
    }
}
