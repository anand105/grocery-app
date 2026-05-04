package com.grocery.app.controller;

import com.grocery.app.model.Order;
import com.grocery.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Place Order
    @PostMapping("/{userId}")
    public Order placeOrder(@PathVariable String userId) {
        return orderService.placeOrder(userId);
    }

    // Get Orders
    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable String userId) {
        return orderService.getUserOrders(userId);
    }
}
