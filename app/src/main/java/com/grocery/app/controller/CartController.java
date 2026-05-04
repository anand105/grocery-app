package com.grocery.app.controller;

import com.grocery.app.model.CartItem;
import com.grocery.app.repository.CartRepository;
import com.grocery.app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    // Add item to cart
    @PostMapping
    public CartItem addToCart(@RequestBody CartItem item) {
        return cartService.addToCart(item);
    }

    // Get cart items by userId
    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        return cartService.getUserCart(userId);
    }

    // Clear cart
    @DeleteMapping("/{userId}")
    public String clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return "Cart cleared";
    }
    // UPDATE quantity
    @PutMapping("/{cartItemId}")
    public CartItem updateQuantity(@PathVariable String cartItemId,
                                   @RequestParam int quantity) {

        CartItem item = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        item.setQuantity(quantity);
        return cartRepository.save(item);
    }

    // DELETE item
    @DeleteMapping("/item/{cartItemId}")
    public String removeItem(@PathVariable String cartItemId) {
        cartRepository.deleteById(cartItemId);
        return "Item removed";
    }
}
