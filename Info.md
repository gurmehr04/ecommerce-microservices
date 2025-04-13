FINAL FUNCTIONALITY RECAP — What your E-commerce Microservices Project Does Now:
ARCHITECTURE AT A GLANCE:
csharp
Copy
Edit
         [Order-Service:8081]
                 |
         RabbitMQ - order-queue
                 |
         [Inventory-Service:8082]
                 |
         RabbitMQ - payment-queue
                 |
         [Payment-Service:8083]
                 |
         RabbitMQ - payment-queue
                 |
         [Order-Service updates Status]
Each Microservice Role & Functionality:
Service	Port	Responsibility	APIs
Order-Service	8081	Place Order, Emit Event to Inventory, Update Order Status	POST /api/order/place
Inventory-Service	8082	Check Stock, Emit Event to Payment	GET /api/inventory/check/{product}/{qty}
Payment-Service	8083	Process Payment, Emit Event to Order	Internal Only
Flow of How it Works:
1. Place Order (User API Call)
API Hit → /api/order/place

JSON Body:

json
Copy
Edit
{
  "productName": "iphone15",
  "quantity": 2
}
2. Order-Service:
Saves in MongoDB → Status = PENDING

Emits OrderEvent to → order-queue

3. Inventory-Service:
Consumes from order-queue

Checks if Stock is Available in MongoDB

Emits updated OrderEvent (status=SUCCESS or FAILED) to → payment-queue

4. Payment-Service:
Consumes from payment-queue

If Inventory SUCCESS → Processes Payment → Saves Payment in MongoDB

Emits Final OrderEvent (status=COMPLETED or FAILED) to → payment-queue

5. Order-Service:
Listens again to payment-queue

Updates Order Status in MongoDB to Final status: COMPLETED or FAILED

MongoDB Collections:
Service	DB	Collections
Order-Service	orderdb	orders
Inventory-Service	inventorydb	inventory
Payment-Service	paymentdb	payments
RabbitMQ Queues:
Queue Name	Used For
order-queue	Order-Service → Inventory-Service
payment-queue	Inventory-Service → Payment-Service → Order-Service
How to Test:
Place Order
bash
Copy
Edit
POST localhost:8081/api/order/place
Body:
{
  "productName": "iphone15",
  "quantity": 2
}
Check Inventory Direct API
sql
Copy
Edit
GET localhost:8082/api/inventory/check/iphone15/2
View MongoDB Data
nginx
Copy
Edit
MongoDB Compass → mongodb://localhost:27017
View RabbitMQ Dashboard
makefilew
Copy
Edit
http://localhost:15672
User: guest
Pass: guest
Running the System:
bash
Copy
Edit
docker-compose down
docker-compose up --build
Stopping Everything:
bash
Copy
Edit
CTRL + C
docker-compose down