package com.grocery.app.repository;

import com.grocery.app.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AddressRepository extends MongoRepository<Address, String> {

    Address findByOrderId(String orderId);
}