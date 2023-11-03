# Vibranium OrderBook Project

👋 Welcome to the Vibranium OrderBook project! This project provides a  order matching and trading system for Vibranium.
The core components of this system include OrderBookManager, TradeManager and VibraniumAPI. Additionally, RabbitMQ is used to manage queues (`order-queue`, `trade-queue`, `user-queue`), and PostgreSQL is employed as the backend database for storing and reading data.

## Getting Started

To get started with this project, follow these steps:

### Clone the Repository

You can clone the project's Git repository using the following command:

git clone [https://github.com/yourusername/vibranium-orderbook.git](https://github.com/gabrielpborba/VibraniumOrderBook.git)

### Run Docker Compose
After cloning the repository, navigate to the project's root directory and run the Docker Compose using the provided docker-compose.yml file:

cd VibraniumOrderBook
docker-compose up

This will set up the required infrastructure, including RabbitMQ, PostgreSQL, orderbookmanager, trademanager and vibraniumAPI.

### User Management
You can create a user by making a POST request to the following endpoint:

#### Endpoint: 
[http:localhost:8080/vibranium/user](http:localhost:8080/vibranium/user)
#### POST
Example Request:
```
{
    "name": "Joaozinho",
    "amount": 10000,
    "quantity": 321
}
```

### Order Creation
To create an order, make a POST request to the following endpoint:

####Endpoint: 
[http:localhost:8080/vibranium/order](http:localhost:8080/vibranium/order)
#### POST
Example Request:
```
{
  "type": "BUY",
  "price": 103,
  "quantity": 10,
  "user": {
    "id": 2
  }
}
```

###  Real-time Order Dashboard
You can visualize real-time order data by opening the following endpoint in your web browser:

[http://localhost:8080/vibranium/order/order-book](http://localhost:8080/vibranium/order/order-book)

This will display a real-time order book dashboard, allowing you to monitor and manage orders efficiently.


#### Enjoy using Vibranium OrderBook!

