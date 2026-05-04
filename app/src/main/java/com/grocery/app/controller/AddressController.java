package com.grocery.app.controller;

import com.grocery.app.model.Address;
import com.grocery.app.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping
    public Address save(@RequestBody Address address) {
        return service.save(address);
    }

    @GetMapping("/{orderId}")
    public Address getAddress(@PathVariable String orderId) {
        return service.getByOrderId(orderId);
    }
}