# NFL Database REST API

As a big football fan I decided to make a production-ready RESTful API for managing NFL teams and players, its built with Spring Boot and PostgreSQL. 
It features automated data seeding from ESPN's public API and full Docker containerization for seamless deployment.

## 🚀 Quick Start

**Prerequisites:** Docker Desktop installed and running

```bash
# Clone the repository
git clone https://github.com/Tate-Smith/nfl-database-api.git
cd nfl-database-api

# Start everything with one command
docker-compose up --build

# API will be available at http://localhost:8080
```

## 📡 API Endpoints

### Teams
```bash
GET    /api/v1/teams              # Get all NFL teams
GET    /api/v1/teams/{id}         # Get specific team
GET    /api/v1/teams/{id}/players # Get all players for a team
POST   /api/v1/teams              # Create new team
PUT    /api/v1/teams/{id}         # Update team
DELETE /api/v1/teams/{id}         # Delete team
```

### Players
```bash
GET    /api/v1/players            # Get all players
GET    /api/v1/players/{id}       # Get specific player
POST   /api/v1/players            # Create new player
PUT    /api/v1/players/{id}       # Update player
DELETE /api/v1/players/{id}       # Delete player
```

---

## 🧪 Testing the API

### Browser
```
http://localhost:8080/api/v1/teams
http://localhost:8080/api/v1/players
```

### curl Examples

**Get all teams:**
```bash
curl http://localhost:8080/api/v1/teams
```

**Create a player:**
```bash
curl -X POST http://localhost:8080/api/v1/players \
  -H "Content-Type: application/json" \
  -d '{
    "externalId": "56764",
    "fullName": "Jaxon Smith-Njigba",
    "position": "WR",
    "status": "ACTIVE",
    "number": "11",
    "teamName": "Seahawks"
  }'
```

**Update a player:**
```bash
curl -X PUT http://localhost:8080/api/v1/players/56764 \
  -H "Content-Type: application/json" \
  -d '{
    "externalId": "56764",
    "fullName": "Jaxon Smith-Njigba",
    "position": "WR",
    "status": "ACTIVE",
    "number": "11",
    "teamName": "Seahawks"
  }'
```

**Delete a player:**
```bash
curl -X DELETE http://localhost:8080/api/v1/players/56764
```

---

## 🏗️ Architecture

This application follows a **three-tier layered architecture**:

```
┌─────────────────────────────────────┐
│   Controller Layer (REST API)       │  ← HTTP Requests/Responses
├─────────────────────────────────────┤
│   Service Layer (Business Logic)    │  ← Data transformation & validation
├─────────────────────────────────────┤
│   Repository Layer (Data Access)    │  ← JPA/Hibernate
├─────────────────────────────────────┤
│   PostgreSQL Database               │  ← Data persistence
└─────────────────────────────────────┘
```

### Key Design Patterns:
- **DTO Pattern:** Decouples API contracts from internal entities
- **Dependency Injection:** Loose coupling via Spring's IoC container
- **Repository Pattern:** Abstraction over data access logic
- **Global Exception Handling:** Consistent error responses with `@ControllerAdvice`

---

## 🛠️ Tech Stack

**Backend:**
- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Hibernate ORM

**Database:**
- PostgreSQL 16

**DevOps:**
- Docker & Docker Compose
- Multi-stage builds for optimized images

**External APIs:**
- ESPN Public API (for data seeding)

---

## ✨ Features

- ✅ **RESTful API** with full CRUD operations
- ✅ **Automated Data Seeding** from ESPN's public API
- ✅ **Docker Containerization** for easy deployment
- ✅ **Comprehensive Validation** using Jakarta Bean Validation
- ✅ **Global Exception Handling** with structured JSON error responses
- ✅ **Entity Relationships** (One-to-Many: Team → Players)
- ✅ **Data Persistence** with PostgreSQL and JPA
- ✅ **Health Checks** ensuring database availability before app startup
- ✅ **Environment-based Configuration** supporting multiple deployment contexts

---

## 📁 Project Structure

```
src/main/java/com/Tate/NFL_db/
├── Controller/          # REST endpoints
│   ├── PlayerController.java
│   └── TeamController.java
├── Service/             # Business logic
│   ├── PlayerService.java
│   └── TeamService.java
├── Model/               # JPA entities
│   ├── Player.java
│   ├── Team.java
│   ├── Position.java (enum)
│   └── Status.java (enum)
├── Repositories/        # Data access layer
│   ├── PlayerRepository.java
│   └── TeamRepository.java
├── Seeder/              # Data initialization
│   ├── PlayerSeeder.java
│   └── TeamSeeder.java
├── dto/                 # Data transfer objects
│   ├── PlayerDTO.java
│   ├── TeamDTO.java
│   └── Mapping.java
└── exception/           # Error handling
    ├── GlobalExceptionHandler.java
    └── ApiError.java
```

---

## 🔐 Security Note

**For Development Only**

The credentials in `docker-compose.yml` are for local development and create an isolated PostgreSQL instance inside Docker. These are not production credentials.

---

## 🎯 Data Seeding

The application automatically seeds data when started with the `seed` profile (default in Docker).

**What gets seeded:**
1. **Teams:** All 32 NFL teams from ESPN API
2. **Players:** Current rosters for each team (~2,500+ players)

**To disable seeding:**
Remove or comment out this line in `docker-compose.yml`:
```yaml
SPRING_PROFILES_ACTIVE: seed
```

**Seeding process:**
- Checks if data already exists (won't duplicate)
- Fetches data from ESPN's public API
- Parses JSON responses
- Maps to internal entities
- Persists to PostgreSQL

---

## 🧪 API Response Examples

### Success Response (GET /api/v1/teams)
```json
[
  {
    "id": 1,
    "externalId": "1",
    "name": "Falcons",
    "city": "Atlanta",
    "abbreviation": "ATL"
  },
  {
    "id": 2,
    "externalId": "2",
    "name": "Bills",
    "city": "Buffalo",
    "abbreviation": "BUF"
  }
]
```

### Success Response (GET /api/v1/players/12345)
```json
{
  "id": 1,
  "externalId": "12345",
  "fullName": "Patrick Mahomes",
  "position": "QB",
  "status": "ACTIVE",
  "number": "15",
  "teamName": "Chiefs",
  "teamExternalId": "12"
}
```

### Error Response (404 Not Found)
```json
{
  "statusCode": 404,
  "error": "Not Found",
  "info": [
    "Player with external id: 99999 not found"
  ],
  "now": "2024-02-17T22:15:30.123Z"
}
```

### Validation Error Response (400 Bad Request)
```json
{
  "statusCode": 400,
  "error": "Validation Failed",
  "info": [
    "fullName: must not be blank",
    "position: must not be null"
  ],
  "now": "2024-02-17T22:15:30.123Z"
}
```

---

## 🎓 Learning Objectives

This project demonstrates:

**Backend Development:**
- RESTful API design and implementation
- Spring Boot application architecture
- JPA/Hibernate ORM and entity relationships
- Input validation and error handling
- External API integration

**Database:**
- PostgreSQL database design
- Schema creation with JPA annotations
- One-to-Many relationships
- CRUD operations
- Query optimization

**DevOps:**
- Docker containerization
- Multi-container orchestration with Docker Compose
- Environment-based configuration
- Health checks and service dependencies
- Volume management for data persistence

---

## 📝 License

This project is for educational and portfolio purposes.

---

## 👤 Author

**Tate Smith**
- GitHub: [@Tate-Smith](https://github.com/Tate-Smith)
- LinkedIn: [LinkedIn](www.linkedin.com/in/tate-smith-b1a973264)

---

## 🙏 Acknowledgments

- ESPN API for providing NFL data

---
