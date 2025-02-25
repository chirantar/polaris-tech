package com.example.polaris.services;


import com.example.polaris.models.Restaurant;
import com.example.polaris.models.Rider;
import com.example.polaris.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getALlRestaurants(){
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id){
        return restaurantRepository.findById(id).get();
    }

    public Restaurant addRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }
}
