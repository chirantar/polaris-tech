package com.example.polaris.models;

import com.example.polaris.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;

    private Long userId;
    private Long riderId;
    private Long restaurantId;
    private Double finalPrice;
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderedItem> foodItems;
}
