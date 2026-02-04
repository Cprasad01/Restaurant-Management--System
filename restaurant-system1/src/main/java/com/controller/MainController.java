package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.User;
import com.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String home(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/restaurants";
        }
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/restaurants";
        }
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password,
                       HttpSession session,
                       Model model) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            return "redirect:/restaurants";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    
    @GetMapping("/register")
    public String showRegisterPage(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/restaurants";
        }
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email,
                          @RequestParam String fullName,
                          @RequestParam String phone,
                          Model model) {
        // Check if username exists
        if (userService.checkUsernameExists(username)) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        
        // Check if email exists
        if (userService.checkEmailExists(email)) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // In production, encrypt this
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPhone(phone);
        
        userService.registerUser(user);
        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}