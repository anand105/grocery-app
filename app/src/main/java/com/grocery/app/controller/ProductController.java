package com.grocery.app.controller;

import com.grocery.app.model.Product;
import com.grocery.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ADMIN only
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // USER + ADMIN
    @GetMapping
    public List<Product> getAllProducts() {
        System.out.println("this is the product call");
        return productService.getAllProducts();
    }
}
