package com.grocery.app.service;

import com.grocery.app.repository.PincodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PincodeService {

    @Autowired
    private PincodeRepository repository;

    public boolean isServiceable(String code) {
        return repository.findById(code).isPresent();
    }
}