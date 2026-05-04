package com.grocery.app.controller;

import com.grocery.app.dto.AuthRequest;
import com.grocery.app.dto.AuthResponse;
import com.grocery.app.model.User;
import com.grocery.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        String token = authService.login(
                request.getEmail(),
                request.getPassword());

        return new AuthResponse(token);
    }
}
