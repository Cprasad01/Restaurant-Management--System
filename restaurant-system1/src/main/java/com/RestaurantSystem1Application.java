package com;

import com.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantSystem1Application  implements CommandLineRunner {

    @Autowired
    private RestaurantService restaurantService;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantSystem1Application.class, args);
    }

    @Override
    public void run(String... args) {
        restaurantService.initializeSampleData();

        System.out.println("==========================================");
        System.out.println("🍽️  RESTAURANT SYSTEM STARTED SUCCESSFULLY!");
        System.out.println("==========================================");
        System.out.println("🌐 Application URL: http://localhost:3036");
        System.out.println("🌐 Login Page:      http://localhost:3036/login");
        System.out.println("🌐 Restaurants:     http://localhost:3036/restaurants");
        System.out.println("==========================================");
    }
}