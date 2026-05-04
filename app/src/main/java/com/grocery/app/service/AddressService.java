package com.grocery.app.service;

import com.grocery.app.model.Address;
import com.grocery.app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public Address save(Address address) {
        return repository.save(address);
    }

    public Address getByOrderId(String orderId) {
        return repository.findByOrderId(orderId);
    }
}