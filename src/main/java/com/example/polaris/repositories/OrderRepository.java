package com.example.polaris.repositories;

import com.example.polaris.enums.OrderStatus;
import com.example.polaris.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // ✅ Fetch orders by user, ordered by most recent
    @Query("SELECT o FROM Order o WHERE o.userId = :userId ORDER BY o.orderId DESC")
    List<Order> findOrdersByUserId(@Param("userId") Long userId);

    // ✅ Fetch completed orders for a rider using enum parameter
    @Query("SELECT o FROM Order o WHERE o.riderId = :riderId AND o.orderStatus = :status")
    List<Order> findCompletedOrdersByRider(@Param("riderId") Long riderId, @Param("status") OrderStatus status);
}
