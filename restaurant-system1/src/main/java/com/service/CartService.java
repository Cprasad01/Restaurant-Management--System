package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CartItem;
import com.entity.MenuItem;
import com.entity.User;
import com.repository.CartItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private RestaurantService restaurantService;
    
    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }
    
    public void addToCart(Long userId, Long menuItemId) {
        CartItem existingItem = cartItemRepository.findByUserIdAndMenuItemId(userId, menuItemId);
        
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartItemRepository.save(existingItem);
        } else {
            MenuItem menuItem = restaurantService.getMenuItemById(menuItemId);
            if (menuItem != null) {
                CartItem cartItem = new CartItem();
                cartItem.setUser(new User(userId, null, null, null, null, null, null));
                cartItem.setMenuItem(menuItem);
                cartItem.setQuantity(1);
                cartItem.setAddedDate(new Date());
                cartItemRepository.save(cartItem);
            }
        }
    }
    
    public void removeFromCart(Long userId, Long menuItemId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndMenuItemId(userId, menuItemId);
        if (cartItem != null) {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
            } else {
                cartItemRepository.delete(cartItem);
            }
        }
    }
    
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
    
    public double getCartTotal(Long userId) {
        List<CartItem> cartItems = getCartItems(userId);
        return cartItems.stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
    }
}