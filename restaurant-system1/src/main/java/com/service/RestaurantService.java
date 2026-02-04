package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.MenuItem;
import com.entity.Restaurant;
import com.repository.MenuItemRepository;
import com.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    // 🔥 THIS METHOD WILL INSERT DATA
    public void initializeSampleData() {

        if (restaurantRepository.count() == 0) {

            // ================= RESTAURANT 1 =================
            Restaurant jamaRestaurant = new Restaurant();
            jamaRestaurant.setName("Jamavarestaurant");
            jamaRestaurant.setDescription("Authentic South Indian cuisine with traditional recipes");
            jamaRestaurant.setImageUrl("/images/Jamavarestaurant-7.jpeg");   
            jamaRestaurant.setAddress("123 Main Street, Bangalore");
            jamaRestaurant.setPhone("+91 9876543210");
            jamaRestaurant.setEmail("jama@restaurant.com");

            // ================= RESTAURANT 2 =================
            Restaurant fisherRestaurant = new Restaurant();
            fisherRestaurant.setName("The Fishers Wharf Restaurant");
            fisherRestaurant.setDescription("Fresh seafood and coastal delicacies");
            fisherRestaurant.setImageUrl("/images/ThefisherswharfRestaurant-8.jpeg"); 
            fisherRestaurant.setAddress("456 Marine Drive, Banglore");
            fisherRestaurant.setPhone("+91 9876543211");
            fisherRestaurant.setEmail("fisher@restaurant.com");

            restaurantRepository.save(jamaRestaurant);
            restaurantRepository.save(fisherRestaurant);

            addJamaMenuItems(jamaRestaurant);
            addFisherMenuItems(fisherRestaurant);
        }
    }

    // ================= JAMAVA MENU =================
    private void addJamaMenuItems(Restaurant restaurant) {

        MenuItem biscuits = new MenuItem();
        biscuits.setName("Biscuits");
        biscuits.setDescription("Freshly baked traditional biscuits");
        biscuits.setPrice(120.0);
        biscuits.setCategory("Bakery");
        biscuits.setImageUrl("/images/Biscuits.jpeg");   
        biscuits.setRestaurant(restaurant);

        MenuItem cake = new MenuItem();
        cake.setName("Cake");
        cake.setDescription("Delicious homemade cake");
        cake.setPrice(250.0);
        cake.setCategory("Dessert");
        cake.setImageUrl("/images/Cake.jpeg");           
        cake.setRestaurant(restaurant);

        MenuItem chapati = new MenuItem();
        chapati.setName("Chapati");
        chapati.setDescription("Soft and fresh chapati");
        chapati.setPrice(20.0);
        chapati.setCategory("Bread");
        chapati.setImageUrl("/images/Chapati.jpeg");    
        chapati.setRestaurant(restaurant);

        MenuItem biryani = new MenuItem();
        biryani.setName("Chicken Biryani");
        biryani.setDescription("Aromatic Hyderabadi chicken biryani");
        biryani.setPrice(350.0);
        biryani.setCategory("Main Course");
        biryani.setImageUrl("/images/ChickenBiriyani.jpeg"); 
        biryani.setRestaurant(restaurant);

        MenuItem coffee = new MenuItem();
        coffee.setName("Coffee");
        coffee.setDescription("Freshly brewed coffee");
        coffee.setPrice(80.0);
        coffee.setCategory("Beverage");
        coffee.setImageUrl("/images/Coffee.jpeg");      
        coffee.setRestaurant(restaurant);

        menuItemRepository.save(biscuits);
        menuItemRepository.save(cake);
        menuItemRepository.save(chapati);
        menuItemRepository.save(biryani);
        menuItemRepository.save(coffee);
    }

    // ================= FISHERS MENU =================
    private void addFisherMenuItems(Restaurant restaurant) {

        MenuItem coolDrink = new MenuItem();
        coolDrink.setName("Cool Drink");
        coolDrink.setDescription("Refreshing cool drink");
        coolDrink.setPrice(60.0);
        coolDrink.setCategory("Beverage");
        coolDrink.setImageUrl("/images/CoolDrink.jpeg"); 
        coolDrink.setRestaurant(restaurant);

        MenuItem dosa = new MenuItem();
        dosa.setName("Dosa");
        dosa.setDescription("Crispy South Indian dosa");
        dosa.setPrice(100.0);
        dosa.setCategory("Main Course");
        dosa.setImageUrl("/images/Dosa.jpeg");          
        dosa.setRestaurant(restaurant);

        MenuItem fishFry = new MenuItem();
        fishFry.setName("Fish Fry");
        fishFry.setDescription("Crispy fried fish with spices");
        fishFry.setPrice(400.0);
        fishFry.setCategory("Seafood");
        fishFry.setImageUrl("/images/FishFry.jpeg");     
        fishFry.setRestaurant(restaurant);

        MenuItem gobi = new MenuItem();
        gobi.setName("Gobi Manchurian");
        gobi.setDescription("Crispy cauliflower in tangy sauce");
        gobi.setPrice(180.0);
        gobi.setCategory("Starter");
        gobi.setImageUrl("/images/GobiManchurian.jpeg"); 
        gobi.setRestaurant(restaurant);

        MenuItem idly = new MenuItem();
        idly.setName("Idly");
        idly.setDescription("Soft and fluffy idly");
        idly.setPrice(70.0);
        idly.setCategory("Breakfast");
        idly.setImageUrl("/images/Idly.jpeg");          
        idly.setRestaurant(restaurant);

        MenuItem meals = new MenuItem();
        meals.setName("Meals");
        meals.setDescription("Complete South Indian thali");
        meals.setPrice(300.0);
        meals.setCategory("Thali");
        meals.setImageUrl("/images/Meals.jpeg");         // ✅
        meals.setRestaurant(restaurant);

        MenuItem chickenFry = new MenuItem();
        chickenFry.setName("Chicken Fry Pieces");
        chickenFry.setDescription("Spicy chicken fry pieces");
        chickenFry.setPrice(320.0);
        chickenFry.setCategory("Starter");
        chickenFry.setImageUrl("/images/ChickenFrypieces.jpeg"); 
        chickenFry.setRestaurant(restaurant);

        menuItemRepository.save(coolDrink);
        menuItemRepository.save(dosa);
        menuItemRepository.save(fishFry);
        menuItemRepository.save(gobi);
        menuItemRepository.save(idly);
        menuItemRepository.save(meals);
        menuItemRepository.save(chickenFry);
    }
}
