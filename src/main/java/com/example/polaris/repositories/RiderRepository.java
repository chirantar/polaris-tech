package com.example.polaris.repositories;

import com.example.polaris.models.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    @Query("SELECT r FROM Rider r WHERE " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(r.latitude)) * " +
            "cos(radians(r.longitude) - radians(:lng)) + sin(radians(:lat)) * sin(radians(r.latitude)))) " +
            "< :radius")
    List<Rider> findNearbyRiders(@Param("lat") Double lat,
                                 @Param("lng") Double lng,
                                 @Param("radius") Double radius);

}
