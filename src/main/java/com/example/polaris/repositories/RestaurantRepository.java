package com.example.polaris.repositories;

import com.example.polaris.models.Restaurant;
import com.example.polaris.services.RestaurantService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
