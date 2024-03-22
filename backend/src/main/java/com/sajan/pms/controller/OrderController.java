package com.sajan.pms.controller;

import com.sajan.pms.dto.OrderRequest;
import com.sajan.pms.model.Order;
import com.sajan.pms.service.implementation.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders(){
        return orderService.getAllOrders().map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId){
        return orderService.getOrderById(orderId).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrderById(@PathVariable Integer orderId, @RequestBody OrderRequest orderRequest){
        return orderService.updateOrderById(orderId, orderRequest).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
