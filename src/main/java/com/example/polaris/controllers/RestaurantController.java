package com.example.polaris.controllers;

import com.example.polaris.models.Restaurant;
import com.example.polaris.models.Rider;
import com.example.polaris.services.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(){
        List<Restaurant> restaurants = restaurantService.getALlRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id){
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.OK).body(createdRestaurant);
    }
}
