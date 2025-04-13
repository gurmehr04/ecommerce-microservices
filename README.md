
# E-commerce Microservices Project (Spring Boot + RabbitMQ + MongoDB + Docker)

## Overview
This project is a Microservices-based E-commerce Application built using:
- Spring Boot
- MongoDB
- RabbitMQ
- Docker & Docker-Compose

---

## Microservices Involved

|Service|Port|Responsibility|
|-------|----|--------------|
|Order Service|8081|Place Orders, Communicate with Inventory & Payment Services|
|Inventory Service|8082|Check Stock, Update Inventory|
|Payment Service|8083|Process Payment & Update Order Status|

---

## Architecture Flow

```
User → Order-Service → RabbitMQ → Inventory-Service → RabbitMQ → Payment-Service → RabbitMQ → Order-Service
```

---

## Folder Structure

```
ecommerce-microservices/
│
├── order-service/
│   └── Dockerfile
│
├── inventory-service/
│   └── Dockerfile
│
├── payment-service/
│   └── Dockerfile
│
├── docker-compose.yml
├── README.md
```

---

## Technologies Used

- Spring Boot 3.x
- RabbitMQ for Messaging
- MongoDB for Storage
- Docker for Containerization
- Docker-Compose for Multi-Container Management

---

## API Endpoints

### Place Order
```
POST http://localhost:8081/api/order/place
```
Request Body:
```json
{
  "productName": "iphone15",
  "quantity": 2
}
```

### Check Inventory Directly
```
GET http://localhost:8082/api/inventory/check/{productName}/{quantity}
```

---

## Running the Application

### Step 1 → Package All Services
In each service run:
```bash
mvn clean package -DskipTests
```

(inside order-service, inventory-service, payment-service)

---

### Step 2 → Build & Run Everything
```bash
docker-compose up --build
```

---

### Step 3 → Stop Everything
```bash
CTRL + C
docker-compose down
```

---

## MongoDB Databases Used
|Service|Database|
|-------|--------|
|order-service|orderdb|
|inventory-service|inventorydb|
|payment-service|paymentdb|

---

## RabbitMQ Queues
|Queue Name|Used For|
|-----------|--------|
|order-queue|Order → Inventory|
|payment-queue|Inventory → Payment → Order|

---

## Access RabbitMQ Dashboard
```
URL: http://localhost:15672
Username: guest
Password: guest
```

---

## Access MongoDB via Compass
```
URL: mongodb://localhost:27017
```

---

## Run Health Check (Basic)
Try:
```
curl http://localhost:8081/actuator/health
curl http://localhost:8082/actuator/health
curl http://localhost:8083/actuator/health
```

(If Spring Actuator Added)

---
