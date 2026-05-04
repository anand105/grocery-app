package com.grocery.app.controller;

import com.grocery.app.model.Delivery;
import com.grocery.app.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    // Ship order
    @PostMapping("/ship/{orderId}")
    public Delivery shipOrder(@PathVariable String orderId,
                              @RequestParam String address) {
        return deliveryService.shipOrder(orderId, address);
    }

    // Mark delivered
    @PutMapping("/deliver/{deliveryId}")
    public Delivery deliver(@PathVariable String deliveryId) {
        return deliveryService.markDelivered(deliveryId);
    }
}