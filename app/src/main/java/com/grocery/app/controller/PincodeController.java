package com.grocery.app.controller;

import com.grocery.app.model.Pincode;
import com.grocery.app.repository.PincodeRepository;
import com.grocery.app.service.PincodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pincode")
@CrossOrigin(origins = "http://localhost:3000")
public class PincodeController {

    @Autowired
    private PincodeRepository repository;

    @Autowired
    private PincodeService service;

    // ✅ Add pincode (admin)
    @PostMapping("/bulk")
    public List<Pincode> addBulk(@RequestBody List<String> pincodes) {

        List<Pincode> list = pincodes.stream()
                .map(Pincode::new)
                .toList();

        return repository.saveAll(list);
    }

    // ✅ Validate pincode
    @GetMapping("/validate/{code}")
    public boolean validate(@PathVariable String code) {
        return service.isServiceable(code);
    }
}