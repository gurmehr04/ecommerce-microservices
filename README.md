
  

# E-commerce Microservices Project (Spring Boot + RabbitMQ + MongoDB + Docker)

  

## Overview

This project simulates a real-world e-commerce system using Microservices Architecture. It implements event-driven communication between services using RabbitMQ and stores data in MongoDB.

  

---

  

## Microservices

  

### 1. Order-Service (Port: 8081)

Handles placing orders, communicating with inventory and payment services, and updating order status.

  

#### Endpoints:

- POST /api/order/place - Place a new order

- GET /api/order/all - View all orders

- GET /api/order/status/{id} - Check order status

- DELETE /api/order/delete/{id} - Delete an order

  

### 2. Inventory-Service (Port: 8082)

Manages inventory including adding, updating, deleting products, checking stock, and reducing stock after order placement.

  

#### Endpoints:

- POST /api/inventory/add - Add a new product

- PUT /api/inventory/update/{productName} - Update product quantity

- DELETE /api/inventory/delete/{productName} - Delete a product

- GET /api/inventory/check/{productName}/{quantity} - Check stock availability

- GET /api/inventory/all - View all products

  

### 3. Payment-Service (Port: 8083)

Handles payment processing based on inventory verification and sends final order status back to order-service.

  

#### Endpoints:

- GET /api/payment/all - View all payment records

- GET /api/payment/status/{productName} - Get payment status for a product

  

---

  

## Architecture Workflow

  

User -> Order-Service -> RabbitMQ (order-queue) -> Inventory-Service

↓

Inventory checks & reduces stock -> RabbitMQ (payment-queue) -> Payment-Service

↓

Payment processed (PAID/FAILED) -> RabbitMQ (payment-queue) -> Order-Service updates final status

  

---

  

## How to Run

  

### Step 1: Package All Services

```

mvn clean package -DskipTests

```

  

### Step 2: Run using Docker Compose

```

docker-compose up --build

```

  

### Step 3: Stop the Services

```

docker-compose down

```

  

---

  

## Databases Used

  

| Service | Database |

| order-service | orderdb |

| inventory-service | inventorydb |

| payment-service | paymentdb |

  

Access via MongoDB Compass:

```

mongodb://localhost:27017

```

  

---

  

## RabbitMQ Dashboard

```

http://localhost:15672

Username: guest

Password: guest

```

  

---

  

## Tools & Technologies

- Spring Boot

- MongoDB

- RabbitMQ

- Docker

- Docker-Compose

- Maven

  

---