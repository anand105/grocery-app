package com.grocery.app.service;

import com.grocery.app.model.CartItem;
import com.grocery.app.model.Order;
import com.grocery.app.model.Product;
import com.grocery.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderTrackingService trackingService;
    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(String userId) {

        List<CartItem> cartItems = cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = 0;

        for (CartItem item : cartItems) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            total += product.getPrice() * item.getQuantity();

            // Reduce inventory
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(cartItems);
        order.setTotalAmount(total);
        order.setStatus("CREATED");
        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cartRepository.deleteByUserId(userId);
        trackingService.addStatus(order.getId(), "CREATED");
        return savedOrder;
    }

    public List<Order> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
