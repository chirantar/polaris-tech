package com.example.polaris.services;

import com.example.polaris.models.FoodItem;
import com.example.polaris.models.Restaurant;
import com.example.polaris.repositories.FoodItemRepository;
import com.example.polaris.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodItemService(FoodItemRepository foodItemRepository, RestaurantRepository restaurantRepository) {
        this.foodItemRepository = foodItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public FoodItem addFoodItem(Long restaurantId, FoodItem foodItem){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + restaurantId));
        foodItem.setRestaurant(restaurant);
        return foodItemRepository.save(foodItem);
    }
}
