package com.sajan.pms.controller;

import com.sajan.pms.dto.CheckoutRequest;
import com.sajan.pms.model.Order;
import com.sajan.pms.model.User;
import com.sajan.pms.service.implementation.CheckoutServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final CheckoutServiceImpl checkoutService;
    @PostMapping()
    public ResponseEntity<Order> checkout(@RequestBody CheckoutRequest request){

       return checkoutService.checkout(request).map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
