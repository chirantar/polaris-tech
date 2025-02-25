package com.example.polaris.controllers;

import com.example.polaris.models.FoodItem;
import com.example.polaris.services.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants/{id}/food-item")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItem> addFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItem){
        FoodItem item = foodItemService.addFoodItem(id, foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItem);
    }
}
