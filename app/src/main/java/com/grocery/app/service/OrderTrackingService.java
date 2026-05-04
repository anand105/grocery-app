package com.grocery.app.service;

import com.grocery.app.model.OrderStatusHistory;
import com.grocery.app.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderTrackingService {

    @Autowired
    private OrderStatusRepository repository;

    public void addStatus(String orderId, String status) {

        OrderStatusHistory history = new OrderStatusHistory();
        history.setOrderId(orderId);
        history.setStatus(status);
        history.setTimestamp(LocalDateTime.now());

        repository.save(history);
    }

    public List<OrderStatusHistory> getHistory(String orderId) {
        return repository.findByOrderIdOrderByTimestampAsc(orderId);
    }
}
