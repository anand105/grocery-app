package com.grocery.app.repository;

import com.grocery.app.model.Pincode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PincodeRepository extends MongoRepository<Pincode, String> {

    Optional<Pincode> findByCode(String code);
}