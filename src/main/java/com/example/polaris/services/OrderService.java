package com.example.polaris.services;

import com.example.polaris.enums.OrderStatus;
import com.example.polaris.models.Order;
import com.example.polaris.repositories.OrderRepository;
import com.example.polaris.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Order placeOrder(Order order) {
        order.setOrderStatus(OrderStatus.PLACED);
        restaurantRepository.findById(order.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        return orderRepository.save(order);
    }

    public Order restaurantResponse(Long orderId, boolean accept) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (order.getOrderStatus() != OrderStatus.PLACED) {
            throw new IllegalStateException("Order already processed");
        }

        if (accept) {
            order.setOrderStatus(OrderStatus.ACCEPTED);
        } else {
            order.setOrderStatus(OrderStatus.REJECTED);
        }

        return orderRepository.save(order);
    }

    public Order assignRider(Long orderId, Long riderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (order.getOrderStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException("Order must be accepted before assigning a rider");
        }

        order.setRiderId(riderId);
        order.setOrderStatus(OrderStatus.IN_PROGRESS);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    public List<Order> getCompletedOrdersByRiderId(Long riderId) {
        return orderRepository.findCompletedOrdersByRider(riderId, OrderStatus.COMPLETED);
    }
}

