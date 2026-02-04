package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.CartItem;
import com.service.CartService;
import com.service.RestaurantService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private CartService cartService;
    
    @GetMapping
    public String listRestaurants(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "restaurants";
    }
    
    @GetMapping("/{id}")
    public String viewRestaurant(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("restaurant", restaurantService.getRestaurantById(id));
        model.addAttribute("menuItems", restaurantService.getMenuItemsByRestaurantId(id));
        return "restaurant-details";
    }
    
    @GetMapping("/menu/{itemId}")
    public String viewMenuItem(@PathVariable Long itemId, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("menuItem", restaurantService.getMenuItemById(itemId));
        return "menu-item-details";
    }
    
    @PostMapping("/cart/add/{itemId}")
    @ResponseBody
    public String addToCart(@PathVariable Long itemId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "ERROR: User not logged in";
        }
        
        cartService.addToCart(userId, itemId);
        return "SUCCESS: Item added to cart";
    }
    
    @PostMapping("/cart/remove/{itemId}")
    @ResponseBody
    public String removeFromCart(@PathVariable Long itemId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "ERROR: User not logged in";
        }
        
        cartService.removeFromCart(userId, itemId);
        return "SUCCESS: Item removed from cart";
    }
    
    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        
        Long userId = (Long) session.getAttribute("userId");
        List<CartItem> cartItems = cartService.getCartItems(userId);
        double total = cartService.getCartTotal(userId);
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "cart";
    }
    
    @PostMapping("/cart/clear")
    public String clearCart(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            cartService.clearCart(userId);
        }
        return "redirect:/restaurants/cart";
    }
}