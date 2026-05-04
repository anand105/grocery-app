package com.grocery.app.repository;

import com.grocery.app.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
}
