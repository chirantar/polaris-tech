package com.example.polaris.controllers;

import com.example.polaris.models.Rider;
import com.example.polaris.repositories.RiderRepository;
import com.example.polaris.services.RiderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riders")
public class RiderController {

    private final RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping("/register")
    public ResponseEntity<Rider> registerRider(@RequestBody Rider rider){
        Rider createdRider = riderService.registerRider(rider);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRider);
    }

    @PutMapping("/{riderId}/location")
    public ResponseEntity<Rider> updateRiderLocation(
            @PathVariable Long riderId,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {

        Rider updatedRider = riderService.updateRiderLocation(riderId, latitude, longitude);
        return ResponseEntity.ok(updatedRider);
    }

    @GetMapping("/nearest")
    public ResponseEntity<Rider> getNearestRider(
            @RequestParam Double restaurantLat,
            @RequestParam Double restaurantLng) {

        List<Rider> nearbyRiders = riderService.findNearbyRiders(restaurantLat, restaurantLng, 5.0);

        if (nearbyRiders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(nearbyRiders.get(0));
    }
}
