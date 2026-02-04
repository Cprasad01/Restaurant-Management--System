package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    
    public User loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // In real application, use password encryption
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public boolean checkUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
    
    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}