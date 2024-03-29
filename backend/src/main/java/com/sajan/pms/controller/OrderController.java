package com.sajan.pms.controller;

import com.sajan.pms.dto.OrderRequest;
import com.sajan.pms.model.Order;
import com.sajan.pms.service.implementation.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    @GetMapping("/admin/all")
    public ResponseEntity<List<Order>> getAllOrders(){
        return orderService.getAllOrders().map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId){
        return orderService.getOrderById(orderId).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable Integer userId){
        return orderService.getOrdersByUserId(userId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrderById(@PathVariable Integer orderId, @RequestBody OrderRequest orderRequest){
        return orderService.updateOrderById(orderId, orderRequest).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/cancel-order/{orderId}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Integer orderId){
        return orderService.cancelOrderById(orderId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
