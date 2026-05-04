package com.grocery.app.service;

import com.grocery.app.model.Delivery;
import com.grocery.app.model.Order;
import com.grocery.app.repository.DeliveryRepository;
import com.grocery.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderTrackingService trackingService;

    // Start delivery
    public Delivery shipOrder(String orderId, String address) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!"PAID".equals(order.getStatus())) {
            throw new RuntimeException("Order not paid");
        }

        Delivery delivery = new Delivery();
        delivery.setOrderId(orderId);
        delivery.setAddress(address);
        delivery.setStatus("SHIPPED");
        Delivery saved = deliveryRepository.save(delivery);

        // Update order
        order.setStatus("SHIPPED");
        orderRepository.save(order);

        trackingService.addStatus(orderId, "SHIPPED");

        return saved;
    }

    // Mark delivered
    public Delivery markDelivered(String deliveryId) {

        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus("DELIVERED");

        Delivery updated = deliveryRepository.save(delivery);

        // Update order
        Order order = orderRepository.findById(delivery.getOrderId())
                .orElseThrow();

        order.setStatus("DELIVERED");
        trackingService.addStatus(order.getId(), "DELIVERED");
        orderRepository.save(order);

        return updated;
    }
}