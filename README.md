# 📦 Food Delivery Service Backend

A backend system for a food delivery service similar to Zomato/Swiggy, built using **Spring Boot**, **JPA**, **WebSockets**, and **JPQL**. This system handles user registration, restaurant management, order placement, real-time order tracking, rider assignments, and scalable infrastructure planning.

---

## 📚 Table of Contents

1. [Project Overview](#project-overview)
2. [API Documentation](#api-documentation)
3. [Database Structure](#database-structure)
4. [Scaling Strategy](#scaling-strategy)
5. [Technologies Used](#technologies-used)

---

## 📝 Project Overview

This backend system supports the following core features:

- **User, Rider, and Restaurant Registration**
- **Menu and Food Item Management**
- **Order Placement, Acceptance, and Assignment**
- **Real-Time Order Notifications (WebSocket-based)**
- **Rider Location Updates and Nearest Rider Assignment**

---

## 📊 API Documentation

### 🧑 User APIs

- **Register User**  
  `POST /api/users/register`  
  **Request:**
  ```json
  {
    "userName": "john_doe",
    "password": "secure123",
    "name": "John Doe"
  }
  ```
  **Response:** `201 Created`

### 🏍️ Rider APIs

- **Register Rider**  
  `POST /api/riders/register`

- **Update Rider Location**  
  `PUT /api/riders/{riderId}/location?latitude=12.9716&longitude=77.5946`

- **Find Nearest Rider**  
  `GET /api/riders/nearest?restaurantLat=12.9716&restaurantLng=77.5946`

### 🍽️ Restaurant APIs

- **Register Restaurant**  
  `POST /api/restaurants`

- **Get All Restaurants**  
  `GET /api/restaurants/all`

- **Get Restaurant By ID**  
  `GET /api/restaurants/{id}`

### 🧾 Food Item APIs

- **Add Food Item to Restaurant**  
  `POST /api/restaurants/{id}/food-items`

- **Get Menu for a Restaurant**  
  `GET /api/restaurants/{id}/menu`

### 📦 Order APIs

- **Place Order**  
  `POST /api/orders`

- **Restaurant Accept/Reject Order**  
  `PUT /api/orders/{orderId}/restaurant-response?accept=true`

- **Assign Rider to Order**  
  `PUT /api/orders/{orderId}/assign-rider?riderId=101`

- **Get Orders by User**  
  `GET /api/orders/user/{userId}`

- **Get Completed Orders by Rider**  
  `GET /api/orders/rider/{riderId}`

---

## 🗄️ Database Structure

### 📁 User Table
| Field     | Type    | Description       |
|-----------|---------|-------------------|
| id        | BIGINT  | Primary Key       |
| userName  | VARCHAR | User login name   |
| password  | VARCHAR | Hashed password   |
| name      | VARCHAR | Full name         |

### 📁 Rider Table
| Field      | Type    | Description           |
|------------|---------|-----------------------|
| id         | BIGINT  | Primary Key           |
| name       | VARCHAR | Rider name            |
| phoneNumber| VARCHAR | Contact number        |
| latitude   | DOUBLE  | Current latitude      |
| longitude  | DOUBLE  | Current longitude     |

### 📁 Restaurant Table
| Field        | Type    | Description         |
|--------------|---------|---------------------|
| id           | BIGINT  | Primary Key         |
| name         | VARCHAR | Restaurant name     |
| location     | VARCHAR | Physical address    |
| cuisineType  | VARCHAR | Cuisine offered     |

### 📁 FoodItem Table
| Field        | Type    | Description        |
|--------------|---------|--------------------|
| id           | BIGINT  | Primary Key        |
| itemName     | VARCHAR | Name of the dish   |
| price        | DOUBLE  | Price of the item  |
| isVeg        | BOOLEAN | Vegetarian or not  |
| restaurantId | BIGINT  | FK to Restaurant   |

### 📁 Order Table
| Field        | Type    | Description              |
|--------------|---------|--------------------------|
| orderId      | BIGINT  | Primary Key              |
| userId       | BIGINT  | FK to User               |
| restaurantId | BIGINT  | FK to Restaurant         |
| riderId      | BIGINT  | FK to Rider              |
| finalPrice   | DOUBLE  | Total order cost         |
| orderStatus  | VARCHAR | PLACED, ACCEPTED, etc.   |

### 📁 OrderedItem (Embedded)
| Field      | Type    | Description        |
|------------|---------|--------------------|
| foodItemId | BIGINT  | FK to FoodItem     |
| itemName   | VARCHAR | Name of the item   |
| quantity   | INT     | Quantity ordered   |
| price      | DOUBLE  | Item price         |

---

## 📈 Scaling Strategy

1. **WebSocket Scaling:**
    - Use **Redis Pub/Sub** or **Kafka** for distributing WebSocket messages across multiple instances.
    - Implement **sticky sessions** if using load balancers.

2. **Database Optimization:**
    - Index fields like `userId`, `restaurantId`, and `riderId` in the **Order** table.
    - Use **GeoSpatial indexing** (e.g., PostGIS or Redis GEO) for efficient location-based queries.

3. **Load Balancing:**
    - Deploy behind a load balancer (NGINX/HAProxy) to manage incoming requests.

4. **Microservices Decomposition (Future):**
    - Split into microservices: **User Service**, **Order Service**, **Rider Service**, and **Notification Service**.

5. **Caching:**
    - Use **Redis** for caching frequently accessed data (e.g., restaurant menus, rider locations).

---

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot** (Web, JPA, WebSocket)
- **PostgreSQL** (Database)
- **Lombok** (Boilerplate reduction)
- **Docker** (For containerization)

---