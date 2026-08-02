# 🏥 MediBook — Sprint 1 Specification & Technical Documentation

> **Sprint 1 Focus:** Minimal Core Foundation (MVP) & Primary Scanner Target Validation  
> **Version:** 1.0.0  
> **Target Framework:** Spring Boot 3.2.x | Java 17 | React 18  

---

## 📌 1. Executive Summary

Sprint 1 delivers the foundational core of the MediBook Hospital Appointment Management System. The objective of Sprint 1 is to provide a clean, fully functional, and fully documented Java Spring Boot backend slice alongside a lightweight React UI. 

This minimal slice enables immediate validation of `kb-scanner` against:
- **Boundary Value Analysis (BVA)** rules in business logic (`calculateDiscount`)
- **Bean Validation Annotations** (`@NotBlank`, `@Min`, `@Max`, `@Email`, `@Pattern`, `@Size`)
- **Custom Exception Handling** (`PatientNotFoundException`, `DuplicateEmailException`, `PatientAlreadyInactiveException`)
- **Spring Data JPA Repositories & `@Transactional` methods**
- **Complete Javadoc Documentation Standards**

---

## 🛠️ 2. Sprint 1 Tech Stack & Dependencies

- **Language:** Java 17 (LTS)
- **Framework:** Spring Boot 3.2.2
- **Persistence:** Spring Data JPA + Hibernate
- **Database:** H2 In-Memory Database (for testing & development)
- **Validation:** `jakarta.validation-api` (Bean Validation 3.0)
- **Build Tool:** Apache Maven
- **Security:** Spring Security (PermitAll for API / H2 console in Dev mode)
- **Documentation:** SpringDoc OpenAPI 2.3.0 (Swagger UI at `/swagger-ui.html`)
- **Testing:** JUnit 5 + Mockito + Spring Boot Test

---

## 🗂️ 3. Sprint 1 Package Structure

```
c:/Users/2862390/Downloads/kb-scanner/Project - Test/medibook/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/medibook/
│   │   │   ├── MediBookApplication.java
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   └── PatientController.java
│   │   │   ├── service/
│   │   │   │   └── PatientService.java
│   │   │   ├── repository/
│   │   │   │   ├── PatientRepository.java
│   │   │   │   └── DoctorRepository.java
│   │   │   ├── model/
│   │   │   │   ├── Patient.java
│   │   │   │   └── Doctor.java
│   │   │   ├── dto/
│   │   │   │   ├── PatientRegistrationRequest.java
│   │   │   │   └── PatientResponse.java
│   │   │   └── exception/
│   │   │       ├── PatientNotFoundException.java
│   │   │       ├── DuplicateEmailException.java
│   │   │       ├── PatientAlreadyInactiveException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
└── frontend/
    ├── package.json
    └── src/
        ├── App.jsx
        ├── components/Navbar.jsx
        └── pages/
            ├── Dashboard.jsx
            └── PatientManagement.jsx
```

---

## 🎯 4. Domain Models & Bean Validation Rules

### Entity 1: `Patient`
| Field | Data Type | Constraints & Annotations | Business Meaning |
|---|---|---|---|
| `id` | `Long` | `@Id`, `@GeneratedValue(IDENTITY)` | Primary key |
| `name` | `String` | `@NotBlank`, `@Size(min = 2, max = 100)` | Full legal patient name |
| `email` | `String` | `@NotNull`, `@Email`, `@Column(unique = true)` | Unique email address |
| `age` | `int` | `@Min(0)`, `@Max(150)` | Patient age (0 = newborn, max = 150) |
| `phone` | `String` | `@Pattern(regexp = "\\d{10}")` | 10-digit telephone number |
| `active` | `boolean` | Default `true` | Soft-delete flag |
| `medicalHistory` | `String` | `@Size(max = 1000)` | Optional medical history notes |

### Entity 2: `Doctor`
| Field | Data Type | Constraints & Annotations | Business Meaning |
|---|---|---|---|
| `id` | `Long` | `@Id`, `@GeneratedValue(IDENTITY)` | Primary key |
| `name` | `String` | `@NotBlank`, `@Size(min = 2, max = 100)` | Doctor full name |
| `specialization` | `String` | `@NotBlank` | Medical specialty |
| `consultationFee` | `double` | `@Min(100)`, `@Max(10000)` | Fee in INR |
| `maxAppointmentsPerDay` | `int` | `@Min(1)`, `@Max(50)` | Daily patient cap |
| `available` | `boolean` | Default `true` | Availability status |

---

## ⚙️ 5. Business Logic & BVA Specification (`PatientService`)

### Methods Defined:

#### 1. `registerPatient(Patient patient)`
- **Behavior:** Saves a new patient after confirming email is unique in repository.
- **Exceptions Thrown:**
  - `IllegalArgumentException`: If `patient` object is `null`.
  - `DuplicateEmailException`: If `email` already exists in `PatientRepository`.

#### 2. `findPatientById(Long patientId)`
- **Behavior:** Queries repository for patient by ID.
- **Exceptions Thrown:**
  - `IllegalArgumentException`: If `patientId` is `null` or `<= 0`.
- **Return Type:** `Optional<Patient>`

#### 3. `deactivatePatient(Long patientId)`
- **Behavior:** `@Transactional` method to set `patient.setActive(false)`.
- **Exceptions Thrown:**
  - `PatientNotFoundException`: If no patient exists with `patientId`.
  - `PatientAlreadyInactiveException`: If `patient.isActive()` is already `false`.

#### 4. `calculateDiscount(int age)` — BVA Boundary Test Target
- **Business Rule:**
  - Age `< 0` or `> 150`: Throws `IllegalArgumentException("Invalid age: must be between 0 and 150")`
  - Age `0 - 12` (Child): Returns `10.0` (10% discount)
  - Age `13 - 59` (Standard adult): Returns `0.0` (0% discount)
  - Age `60 - 150` (Senior citizen): Returns `20.0` (20% discount)

#### BVA Test Cases Matrix (`calculateDiscount`):
| Input `age` | Expected Result | Boundary Meaning |
|:---:|:---:|:---|
| `-1` | Throws `IllegalArgumentException` | Invalid below min |
| `0` | Returns `10.0` | Min valid / Min child boundary |
| `12` | Returns `10.0` | Max child boundary |
| `13` | Returns `0.0` | Min standard adult boundary |
| `59` | Returns `0.0` | Max standard adult boundary |
| `60` | Returns `20.0` | Min senior citizen boundary |
| `150` | Returns `20.0` | Max valid age boundary |
| `151` | Throws `IllegalArgumentException` | Invalid above max |

---

## 🌐 6. REST API Endpoint Specification

| HTTP Method | URL Path | Request Body | Response Status | Description |
|---|---|---|---|---|
| `POST` | `/api/patients` | `PatientRegistrationRequest` | `201 Created` | Registers a new patient |
| `GET` | `/api/patients/{id}` | None | `200 OK` / `404 Not Found` | Retrieves patient details |
| `PUT` | `/api/patients/{id}/deactivate` | None | `200 OK` | Deactivates patient account |
| `GET` | `/api/patients/discount?age={age}` | None | `200 OK` / `400 Bad Request` | Calculates discount % for given age |

---

## 📝 7. Documentation & Javadoc Standards

All Java source files in Sprint 1 strictly adhere to section 9 of `12_medibook_test_project_spec.md`:
- Class-level Javadoc detailing business responsibilities, example usage, `@author MediBook Team`, `@version 1.0`.
- Method-level Javadoc detailing explicit parameters with valid ranges, return value details (including empty vs null handling), and exact exception trigger conditions.
