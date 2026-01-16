# 📚 Book Collection Backend

A Spring Boot backend application for managing a book collection, designed using **Domain-Driven Design (DDD)** and **CQRS** principles with a clean, maintainable architecture.

---

## 🛠 Tech Stack

- **Java**: 21
- **Spring Boot**: 4.x
- **Build Tool**: Maven
- **Database**: H2 (in-memory)
- **Architecture**: Clean Architecture + DDD + CQRS
- **API**: REST
- **Documentation**: OpenAPI / Swagger
- **IDE**: IntelliJ IDEA (recommended)

---

## 📂 Project Structure
```text
book-collection
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com.example.bookcollection
    │   │       ├── BookCollectionApplication.java
    │   │       │
    │   │       ├── domain
    │   │       │   ├── book
    │   │       │   │   ├── Book.java
    │   │       │   │   └── BookId.java
    │   │       │   │
    │   │       │   └── category
    │   │       │       └── CategoryId.java
    │   │       │
    │   │       ├── application
    │   │       │   ├── book
    │   │       │   │   ├── BookNotFoundException.java
    │   │       │   │
    │   │       │   │   ├── command
    │   │       │   │   │   ├── CreateBookCommand.java
    │   │       │   │   │   ├── UpdateBookCommand.java
    │   │       │   │   │   ├── DeleteBookCommand.java
    │   │       │   │   │   └── BookCommandHandler.java
    │   │       │   │   │
    │   │       │   │   └── query
    │   │       │   │       ├── BookView.java
    │   │       │   │       ├── BookQueryRepository.java
    │   │       │   │       └── BookQueryHandler.java
    │   │       │   │
    │   │       │   └── category
    │   │       │       └── query
    │   │       │           ├── CategoryView.java
    │   │       │           ├── CategoryQueryRepository.java
    │   │       │           └── CategoryQueryHandler.java
    │   │       │
    │   │       ├── infrastructure
    │   │       │   ├── persistence
    │   │       │   │   └── JdbcBookRepository.java
    │   │       │   │
    │   │       │   └── query
    │   │       │       ├── JdbcBookQueryRepository.java
    │   │       │       └── JdbcCategoryQueryRepository.java
    │   │       │
    │   │       └── api
    │   │            ├── BookController.java
    │   │            ├── CategoryController.java
    │   │            ├── GlobalExceptionHandler.java
    │   │            │
    │   │            ├── config
    │   │            │   └── OpenApiConfig.java
    │   │            │
    │   │            └── dto
    │   │                 ├── CreateBookRequest.java
    │   │                 └── UpdateBookRequest.java
    │   │
    │   └── resources
    │       └── application.yml
    │
    └── test
        └── java
            └── com.arif.bookservice
                └── application
                    └── book
                        └── BookCommandHandlerTest.java
```
## 📥 Clone the Repository
```text
git clone git@github.com:abushaista/book-collection-service.git
cd book-collection-service
```
## ▶️ Run Using Maven (Recommended)
```text
mvn clean spring-boot:run
```
The application will start on:
```text
http://localhost:8080
```
## 🧪 Run Tests
```text
mvn test
```
## 🗄️ Database (H2)
- Database: **H2 in-memory**

Optional H2 Console (if enabled):
```text
http://localhost:8080/h2-console
```
## 🔁 Available API Endpoints
| Method | Endpoint          | Description     |
| ------ | ----------------- | --------------- |
| POST   | `/api/books`      | Create a book   |
| PUT    | `/api/books/{id}` | Update a book   |
| DELETE | `/api/books/{id}` | Delete a book   |
| GET    | `/api/books/{id}` | Get book by ID  |
| GET    | `/api/books`      | List all books  |
| GET    | `/api/categories` | List categories |


# 🧠 Assumptions & Design Decisions
## 1️⃣ CQRS (Command / Query Separation)
- **Commands**
  - Located in application.book.command
  - Located in application.book.command
  - Handle state-changing operations
- **Queries**
  - Located in application.book.query
  - Read-only models (BookView)
  - Optimized for read use cases
- Commands never return domain entities.

## 2️⃣ Clean Architecture Boundaries
| Layer            | Responsibility                |
|------------------| ----------------------------- |
| `domain`         | Business rules & invariants   |
| `application`    | Use cases & orchestration     |
| `infrastructure` | Database & technical concerns |
| `api`            | REST controllers & DTOs       |

## 3️⃣ Persistence Strategy
- **Command Side**
  - Uses JpaBookRepository
  - Focused on Domain Consistency
- **Query Side**
  - Uses JdbcBookQueryRepository
  - Uses Projection Models (BookView)
- Allow future migration to event sourcing 



