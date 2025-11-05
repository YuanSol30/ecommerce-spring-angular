# E-Commerce Application

A full-stack e-commerce application with Spring Boot backend, MySQL database, and Angular frontend featuring a complete checkout and payment system.

## Features

- 🛍️ Product browsing and management
- 🛒 Shopping cart functionality
- 💳 Mock payment processing system
- 📦 Order management and history
- 🔐 CORS-enabled REST API
- 🐳 Docker containerization

## Tech Stack

### Backend
- **Spring Boot 2.7.5** - Java framework
- **MySQL 8.0** - Database
- **JPA/Hibernate** - ORM
- **Maven** - Build tool
- **Docker & Docker Compose** - Containerization

### Frontend (Not included in this repo)
- Angular 14
- TypeScript
- RxJS
- Bootstrap

## Prerequisites

Before running this application, ensure you have:

- **Docker** installed ([Download Docker](https://www.docker.com/products/docker-desktop))
- **Docker Compose** installed (usually comes with Docker Desktop)
- **Git** installed

## How to Run

### Option 1: Using Docker Compose (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/YuanSol30/ecommerce-spring-angular.git
   cd ecommerce-spring-angular
   ```

2. **Navigate to the Docker directory**
   ```bash
   cd ecommerce/ecommerce
   ```

3. **Start the application**
   ```bash
   docker-compose up --build -d
   ```

4. **Wait for services to start** (about 20-30 seconds)

5. **Verify containers are running**
   ```bash
   docker ps
   ```
   You should see two containers:
   - `ecommerce_1` (Spring Boot application)
   - `mysql1` (MySQL database)

6. **Access the API**
   - Backend API: http://localhost:8080
   - Health check: http://localhost:8080/api/products

### Option 2: Manual Setup

If you prefer to run without Docker:

#### 1. Setup MySQL Database

```sql
CREATE DATABASE ecom;
USE ecom;

-- Import the SQL files from /ecom/ directory
SOURCE ecom_hibernate_sequence.sql;
SOURCE ecom_product_data.sql;
SOURCE ecom_menu_data.sql;
SOURCE ecom_order_item_data.sql;
```

#### 2. Configure Application

Edit `ecommerce/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ecom
    username: your_mysql_username
    password: your_mysql_password
```

#### 3. Build and Run

```bash
cd ecommerce
mvn clean package
java -jar target/ecommerce-1.0-SNAPSHOT.jar
```

## API Endpoints

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID

### Orders
- `POST /api/orders/checkout` - Process checkout
- `GET /api/orders` - Get all orders
- `GET /api/orders/{orderId}` - Get order by ID
- `GET /api/orders/customer/{customerId}` - Get orders by customer

### Categories
- `GET /api/categories` - Get all categories

### Menu
- `GET /api/menu` - Get menu items

## Testing the Checkout API

Use Postman or curl to test the checkout endpoint:

```bash
curl -X POST http://localhost:8080/api/orders/checkout \
  -H "Content-Type: application/json" \
  -d '{
    "customer": {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com",
      "address": "123 Main St"
    },
    "payment": {
      "cardNumber": "4111111111111111",
      "expiryMonth": "12",
      "expiryYear": "2026",
      "cvv": "123",
      "cardHolderName": "John Doe",
      "paymentMethod": "credit_card"
    },
    "items": [
      {
        "productId": 1,
        "productName": "Sample Product",
        "price": 29.99,
        "quantity": 2
      }
    ],
    "totalAmount": 59.98
  }'
```

## Stopping the Application

### Docker
```bash
docker-compose down
```

### Manual
Press `Ctrl+C` in the terminal running the application

## Troubleshooting

### Port Already in Use
If port 8080 or 3306 is already in use:

**Windows:**
```powershell
# Find process using port 8080
netstat -ano | findstr :8080

# Kill the process (replace PID with actual process ID)
taskkill /PID <PID> /F
```

**Linux/Mac:**
```bash
# Find and kill process using port 8080
lsof -ti:8080 | xargs kill -9
```

### MySQL Connection Issues
If you get MySQL connection errors:
1. Ensure MySQL container is fully started (wait 30 seconds after `docker-compose up`)
2. Check MySQL logs: `docker logs mysql1`
3. Restart the backend: `docker restart ecommerce_1`

### CORS Issues
If you're connecting from a frontend on a different port, the application is configured to allow requests from `http://localhost:4200`. To change this, edit:
```java
// ecommerce/src/main/java/com/gabriel/config/CorsConfig.java
config.setAllowedOrigins(Arrays.asList("http://your-frontend-url"));
```

## Project Structure

```
ecommerce-spring-angular/
├── ecom/                          # SQL database files
│   ├── ecom_hibernate_sequence.sql
│   ├── ecom_product_data.sql
│   ├── ecom_menu_data.sql
│   └── ecom_order_item_data.sql
├── ecommerce/
│   ├── docker-compose.yml         # Docker composition file
│   ├── Dockerfile                 # Docker build instructions
│   ├── pom.xml                    # Maven configuration
│   ├── ecommerce/                 # Nested Docker setup
│   └── src/
│       └── main/
│           ├── java/com/gabriel/
│           │   ├── config/        # Configuration (CORS, etc.)
│           │   ├── controller/    # REST API controllers
│           │   ├── entity/        # JPA entities
│           │   ├── model/         # DTOs and models
│           │   ├── repository/    # Data repositories
│           │   ├── service/       # Business logic
│           │   └── serviceimpl/   # Service implementations
│           └── resources/
│               └── application.yml # Application configuration
```

## Environment Variables

You can customize the application using environment variables:

```yaml
SPRING_DATASOURCE_URL: jdbc:mysql://mysqldb:3306/ecom
SPRING_DATASOURCE_USERNAME: mysqluser
SPRING_DATASOURCE_PASSWORD: password
```

## Database Schema

The application uses the following main tables:
- `product_data` - Product information
- `order_data` - Order details
- `order_item_data` - Order line items
- `menu_data` - Menu/category information
- `customer_data` - Customer information

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Contact

James Bongcac - theoldhistoryof@gmail.com

Project Link: [https://github.com/YuanSol30/ecommerce-spring-angular](https://github.com/YuanSol30/ecommerce-spring-angular)

## Acknowledgments

- Spring Boot Documentation
- Docker Documentation
- MySQL Documentation
