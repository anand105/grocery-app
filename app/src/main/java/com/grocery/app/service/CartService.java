package com.grocery.app.service;

import com.grocery.app.model.CartItem;
import com.grocery.app.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartItem addToCart(CartItem item) {
        return cartRepository.save(item);
    }

    public List<CartItem> getUserCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }
}
