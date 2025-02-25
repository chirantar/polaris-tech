package com.example.polaris.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "food_item")
public class FoodItem {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private double price;

    private boolean isVeg;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;

}
