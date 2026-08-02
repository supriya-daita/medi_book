# 🏥 MediBook — Hospital Appointment Management System

> Production-level Java Spring Boot 3.2 + React 18 application designed to benchmark and validate automated test generation capabilities.

---

## 🚀 Overview

MediBook is a hospital management system built with Spring Boot 3.2, Spring Data JPA, Java 17, and React 18. It features comprehensive Bean Validation rules, age-bracket Boundary Value Analysis (BVA), `@Transactional` business logic, and custom exception handling.

---

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot 3.2.2, Spring Data JPA, Hibernate, H2 Database
- **Validation:** `jakarta.validation` (Bean Validation 3.0)
- **Security:** Spring Security
- **API Documentation:** SpringDoc OpenAPI 2.3.0 (Swagger UI at `/swagger-ui.html`)
- **Testing:** JUnit 5, Mockito, Spring Boot Test
- **Frontend:** React 18, Vite, Axios

---

## 📁 Repository Structure

```
medibook/
├── pom.xml                                 # Maven dependencies
├── docs/
│   ├── sprint_1_documentation.md           # Sprint 1 Technical Specification
│   └── sprint_2_documentation.md           # Sprint 2 Technical Specification
├── src/
│   ├── main/java/com/medibook/
│   │   ├── MediBookApplication.java        # Entry point
│   │   ├── config/SecurityConfig.java      # Security configuration
│   │   ├── controller/PatientController.java # REST APIs
│   │   ├── service/PatientService.java    # Business logic & BVA engine
│   │   ├── repository/                     # JPA repositories
│   │   ├── model/                          # JPA Entities with Bean Validation
│   │   ├── dto/                            # DTOs
│   │   └── exception/                      # Custom Exceptions & Global Handler
│   └── resources/
│       ├── application.properties
│       └── data.sql                        # Seed data
└── frontend/                               # React 18 application
```

---

## ⚙️ Running Locally

### Backend (Spring Boot)
```bash
mvn spring-boot:run
```
- API Base URL: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console`

### Frontend (React)
```bash
cd frontend
npm install
npm run dev
```

---

## 📝 License
MIT License
