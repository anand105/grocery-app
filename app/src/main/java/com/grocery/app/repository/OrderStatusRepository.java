package com.grocery.app.repository;

import com.grocery.app.model.OrderStatusHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderStatusRepository extends MongoRepository<OrderStatusHistory, String> {

    List<OrderStatusHistory> findByOrderIdOrderByTimestampAsc(String orderId);
}
