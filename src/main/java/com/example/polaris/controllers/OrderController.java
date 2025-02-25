package com.example.polaris.controllers;

import com.example.polaris.models.Order;
import com.example.polaris.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final SimpMessagingTemplate messagingTemplate;


    public OrderController(OrderService orderService, SimpMessagingTemplate messagingTemplate) {
        this.orderService = orderService;
        this.messagingTemplate = messagingTemplate;
    }

    @PutMapping("/{orderId}/assign-rider")
    public ResponseEntity<Order> assignRider(
            @PathVariable Long orderId,
            @RequestParam Long riderId) {

        Order updatedOrder = orderService.assignRider(orderId, riderId);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/rider/{riderId}")
    public ResponseEntity<List<Order>> getRiderOrders(@PathVariable Long riderId) {
        return ResponseEntity.ok(orderService.getCompletedOrdersByRiderId(riderId));
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order createdOrder = orderService.placeOrder(order);

        messagingTemplate.convertAndSend("/topic/restaurant/" + order.getRestaurantId(), createdOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{orderId}/restaurant-response")
    public ResponseEntity<Order> restaurantResponse(
            @PathVariable Long orderId,
            @RequestParam("accept") boolean accept) {

        Order updatedOrder = orderService.restaurantResponse(orderId, accept);

        messagingTemplate.convertAndSend("/topic/user/" + updatedOrder.getUserId(), updatedOrder);

        return ResponseEntity.ok(updatedOrder);
    }
}
