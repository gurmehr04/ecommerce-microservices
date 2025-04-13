
# E-commerce Microservice Architecture Project

## Tech Stack
- Spring Boot (Java 17)
- MongoDB
- RabbitMQ
- REST APIs
- Maven
- Docker & Docker Compose (for easy setup)

## Microservices in this Project
| Service | Port | Responsibilities |
|---------|------|-----------------|
| Order Service | 8081 | Place new orders & trigger inventory check |
| Inventory Service | 8082 | Manage product stock & trigger payment |
| Payment Service | 8083 | Process payment after inventory confirmation |

## Pre-requisites
Make sure these are installed in your system:
- Java 17+
- Maven
- Docker Desktop
- Postman (for API testing)

## To Run the Entire Project Locally

### 1. Start Docker (MongoDB + RabbitMQ)
```
docker-compose up
```
> This will start:
- MongoDB → port 27017
- RabbitMQ → ports 5672 & 15672 (Dashboard)

RabbitMQ Dashboard → http://localhost:15672  
Username: `guest`  
Password: `guest`

### 2. Run Each Microservice Individually

#### Order Service
```
cd order-service
mvn spring-boot:run
```

#### Inventory Service
```
cd inventory-service
mvn spring-boot:run
```

#### Payment Service
```
cd payment-service
mvn spring-boot:run
```

## How to Test in Postman

### 1. Place an Order
```
POST → http://localhost:8081/order/place
Body: raw JSON
{
  "product": "Laptop",
  "quantity": 1
}
```

→ Order Event will be sent to RabbitMQ Queue → Inventory will process it.

### 2. Check Inventory Service Logs
→ Inventory will either:
- Update stock & trigger Payment event  
- Or log insufficient stock.

### 3. Payment Service Logs
→ If Inventory was sufficient → Payment Service will process the order.

## MongoDB GUI (Optional)
Download Mongo Compass: https://www.mongodb.com/products/compass  
Use connection string:
```
mongodb://localhost:27017
```

View:
- `orderdb`  
- `inventorydb`  
- `paymentdb`

## Ports Recap
| Service | Port |
|---------|------|
| Order Service | 8081 |
| Inventory Service | 8082 |
| Payment Service | 8083 |
| MongoDB | 27017 |
| RabbitMQ | 5672 (service) & 15672 (dashboard) |

## Important RabbitMQ Terms Used
| Term | Value |
|------|-------|
| Exchange | order_exchange |
| Queue | order_queue |
| Routing Key | order_routingKey |

## Commands for Maintenance

### Stop All Docker Containers
```
docker-compose down
```

### Clean Maven Build
```
mvn clean install
```

## Notes
- Always start `docker-compose` first.
- Then run all 3 services individually.
- Use Postman to test order placing.
- Track flow in console logs.
