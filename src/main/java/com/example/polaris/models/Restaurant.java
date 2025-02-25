package com.example.polaris.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    @Column(name = "cuisine_type")
    private String cuisineType;

    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private List<FoodItem> foodItems;
}
