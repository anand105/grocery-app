package com.grocery.app.service;

import com.grocery.app.model.Order;
import com.grocery.app.model.Payment;
import com.grocery.app.repository.OrderRepository;
import com.grocery.app.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private OrderTrackingService trackingService;
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Payment processPayment(String orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(order.getTotalAmount());

        // Mock logic (simulate success)
        payment.setStatus("SUCCESS");

        Payment savedPayment = paymentRepository.save(payment);

        // Update order status
        order.setStatus("PAID");
        trackingService.addStatus(orderId, "PAID");
        orderRepository.save(order);

        return savedPayment;
    }
}
