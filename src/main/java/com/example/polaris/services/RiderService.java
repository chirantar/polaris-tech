package com.example.polaris.services;

import com.example.polaris.models.Rider;
import com.example.polaris.repositories.RiderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService {
    private final RiderRepository riderRepository;

    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public Rider registerRider(Rider rider){
        return riderRepository.save(rider);
    }

    public Rider updateRiderLocation(Long riderId, Double latitude, Double longitude) {
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException("Rider not found"));

        rider.setLatitude(latitude);
        rider.setLongitude(longitude);

        return riderRepository.save(rider);
    }

    public List<Rider> findNearbyRiders(Double restaurantLat, Double restaurantLng, double v) {
        List<Rider> nearbyRiders = riderRepository.findNearbyRiders(restaurantLat, restaurantLng, v);

        return nearbyRiders;
    }
}
