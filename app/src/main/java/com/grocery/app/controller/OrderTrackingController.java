package com.grocery.app.controller;

import com.grocery.app.model.OrderStatusHistory;
import com.grocery.app.service.OrderTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tracking")
public class OrderTrackingController {

    @Autowired
    private OrderTrackingService service;

    @GetMapping("/{orderId}")
    public List<OrderStatusHistory> getTracking(@PathVariable String orderId) {
        return service.getHistory(orderId);
    }
}