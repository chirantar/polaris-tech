package com.example.polaris.models;

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
@Table(name = "rider")
public class Rider {
    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="vehicle_number")
    private String vehicleNumber;
    private Double latitude;
    private Double longitude;
}
