package com.gabriel.controller;

import com.gabriel.entity.OrderData;
import com.gabriel.entity.OrderItemData;
import com.gabriel.model.CheckoutRequest;
import com.gabriel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/checkout")
    public ResponseEntity<OrderData> processCheckout(@RequestBody CheckoutRequest checkoutRequest) {
        try {
            OrderData order = orderService.processCheckout(checkoutRequest);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderData>> getCustomerOrders(@PathVariable int customerId) {
        List<OrderData> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderData> getOrder(@PathVariable int orderId) {
        OrderData order = orderService.getOrderById(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<OrderData>> getAllOrders() {
        List<OrderData> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
