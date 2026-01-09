# eCommerce Price Service

This project is a microservice designed to manage and retrieve product prices based on application dates, product identifiers, and brand IDs.

## 🚀 Tech Stack

*   **Java 21**
*   **Spring Boot 3.4.1** (Spring MVC, Spring Data JPA)
*   **Lombok abstraction** (for boilerplate reduction)
*   **MapStruct** (for bean mapping)
*   **Open API model validation** 
*   **JUnit 5 and Spring Boot Test** (for unit testing)
*   **H2 Database** (In-memory database for development and testing)
*   **Maven** (Dependency management)

## 🛠️ Project Structure

The project follows a clean, loosely coupled architecture. This is achieved by preventing the business logic from importing infrastructure or UI packages and by using interfaces. The main components are:
*   `controller`: Application entry ports via REST APIs and API converters/adapters.
*   `service`: Business logic.
*   `repository/entity`: Application entry ports via REST APIs and API converters/adapters.

## ⚙️ Prerequisites

*   JDK 21
*   Maven 3.8+

## 🏃 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/segundaBota/eCommerce.git
   ```
2. Build the project:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## 📉 API Reference

### Get Applicable Price
Retrieves the valid price for a specific product and brand at a given date. If multiple prices overlap for the requested date, the one with the highest **priority** is selected.

*   **URL:** `/prices`
*   **Method:** `GET`
*   **Query Parameters:**
    *   `applicationDate` (String, ISO-8601): The date and time for the price query.
    *   `productId` (Integer): The unique identifier of the product.
    *   `brandId` (Integer): The identifier for the brand/chain.

**Example Response:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```


## 🚀 Future Improvements & Next Steps

To evolve this API into a more robust and production-ready service, the following enhancements are suggested:

1.  **Security with Spring Security**:
    *   Integrate **Spring Security** to secure API access.
    *   Implement **JWT (JSON Web Tokens)** or **OAuth2** authentication to ensure only authorized clients can retrieve pricing data.
    *   Define fine-grained access control using roles (e.g., `ROLE_USER`, `ROLE_ADMIN`).
2.  **Caching Layer**: Implement a caching strategy (using Redis or Caffeine) to optimize performance for frequent queries on the same products and dates.
3.  **Observability**: Add **Spring Boot Actuator** and Micrometer to monitor application health, performance metrics, and logging.