package com.example.polaris.seeder;

import com.example.polaris.enums.OrderStatus;
import com.example.polaris.models.*;
import com.example.polaris.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final FoodItemRepository foodItemRepository;
    private final UserRepository userRepository;
    private final RiderRepository riderRepository;
    private final OrderRepository orderRepository;

    private boolean alreadySeeded = false;

    public DataSeeder(RestaurantRepository restaurantRepository,
                      FoodItemRepository foodItemRepository,
                      UserRepository userRepository,
                      RiderRepository riderRepository,
                      OrderRepository orderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodItemRepository = foodItemRepository;
        this.userRepository = userRepository;
        this.riderRepository = riderRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (alreadySeeded || restaurantRepository.count() > 0) {
            return;
        }

        Restaurant rest1 = new Restaurant();
        rest1.setName("Spice Hub");
        rest1.setAddress("Downtown");
        rest1.setCuisineType("Indian");
        rest1.setLatitude(28.6139);
        rest1.setLongitude(77.2090);

        Restaurant rest2 = new Restaurant();
        rest2.setName("Burger Planet");
        rest2.setAddress("Uptown");
        rest2.setCuisineType("American");
        rest2.setLatitude(19.0760);
        rest2.setLongitude(72.8777);

        Restaurant rest3 = new Restaurant();
        rest3.setName("Pizza Palace");
        rest3.setAddress("Central");
        rest3.setCuisineType("Italian");
        rest3.setLatitude(12.9716);
        rest3.setLongitude(77.5946);

        restaurantRepository.saveAll(List.of(rest1, rest2, rest3));

        Rider rider1 = new Rider();
        rider1.setName("Raj Kumar");
        rider1.setPhoneNumber("9876543210");
        rider1.setVehicleNumber("DL4CAF1234");
        rider1.setLatitude(28.6145);
        rider1.setLongitude(77.2095);

        Rider rider2 = new Rider();
        rider2.setName("Anita Sharma");
        rider2.setPhoneNumber("9876549876");
        rider2.setVehicleNumber("MH12CD3456");
        rider2.setLatitude(19.0750);
        rider2.setLongitude(72.8770);

        Rider rider3 = new Rider();
        rider3.setName("Vikram Singh");
        rider3.setPhoneNumber("9988776655");
        rider3.setVehicleNumber("KA05MK4321");
        rider3.setLatitude(12.9725);
        rider3.setLongitude(77.5950);

        Rider rider4 = new Rider();
        rider4.setName("Priya Patel");
        rider4.setPhoneNumber("9876554321");
        rider4.setVehicleNumber("GJ01AB1234");
        rider4.setLatitude(22.3072);
        rider4.setLongitude(72.8376);

        riderRepository.saveAll(List.of(rider1, rider2, rider3, rider4));

        FoodItem item1 = new FoodItem();
        item1.setName("Paneer Butter Masala");
        item1.setPrice(250.0);
        item1.setVeg(true);
        item1.setRestaurant(rest1);

        FoodItem item2 = new FoodItem();
        item2.setName("Chicken Biryani");
        item2.setPrice(300.0);
        item2.setVeg(false);
        item2.setRestaurant(rest1);

        FoodItem item3 = new FoodItem();
        item3.setName("Veg Burger");
        item3.setPrice(150.0);
        item3.setVeg(true);
        item3.setRestaurant(rest2);

        foodItemRepository.saveAll(List.of(item1, item2, item3));

        User user1 = new User();
        user1.setUserName("john_doe");
        user1.setPassword("password123");
        user1.setName("John Doe");

        User user2 = new User();
        user2.setUserName("jane_doe");
        user2.setPassword("password123");
        user2.setName("Jane Doe");

        userRepository.saveAll(List.of(user1, user2));

        OrderedItem orderedItem1 = new OrderedItem(item1.getId(), item1.getName(), 2, item1.getPrice());
        OrderedItem orderedItem2 = new OrderedItem(item2.getId(), item2.getName(), 1, item2.getPrice());

        Order order1 = new Order();
        order1.setUserId(user1.getId());
        order1.setRestaurantId(rest1.getId());
        order1.setRiderId(rider1.getId());
        order1.setFinalPrice((orderedItem1.getPrice() * orderedItem1.getQuantity()) +
                (orderedItem2.getPrice() * orderedItem2.getQuantity()));
        order1.setOrderStatus(OrderStatus.COMPLETED);
        order1.setFoodItems(List.of(orderedItem1, orderedItem2));

        OrderedItem orderedItem3 = new OrderedItem(item3.getId(), item3.getName(), 3, item3.getPrice());

        Order order2 = new Order();
        order2.setUserId(user2.getId());
        order2.setRestaurantId(rest2.getId());
        order2.setRiderId(rider2.getId());
        order2.setFinalPrice(orderedItem3.getPrice() * orderedItem3.getQuantity());
        order2.setOrderStatus(OrderStatus.ACCEPTED);
        order2.setFoodItems(List.of(orderedItem3));

        orderRepository.saveAll(List.of(order1, order2));

        alreadySeeded = true;
    }
}
