# 🏥 MediBook — Sprint 2 Specification & Roadmap Documentation

> **Sprint 2 Focus:** Full Enterprise Architecture, Multi-Step `@Transactional` Services, Prescription BVA & Full React UI  
> **Version:** 2.0.0 Target  
> **Target Framework:** Spring Boot 3.2.x | Java 17 | React 18  

---

## 📌 1. Executive Summary

Sprint 2 expands MediBook from the Sprint 1 MVP into a comprehensive, enterprise-ready hospital appointment management system. 

It introduces:
- 3 Additional Domain Entities (`Appointment`, `Prescription`, `Bill`)
- 3 Advanced Service Layers (`AppointmentService`, `PrescriptionService`, `BillingService`)
- 8 New Custom Exception Classes (11 Total)
- Cross-Service `@Transactional` operations with rollback guarantees
- Detailed Prescription Dosage Boundary Value Analysis (BVA: 1mg – 5000mg)
- Full 9-page React Frontend with Axios API integration

---

## 🗂️ 2. Sprint 2 Added Components & Package Structure

```
c:/Users/2862390/Downloads/kb-scanner/Project - Test/medibook/
├── src/main/java/com/medibook/
│   ├── model/
│   │   ├── Appointment.java          (NEW)
│   │   ├── Prescription.java         (NEW)
│   │   └── Bill.java                 (NEW)
│   ├── service/
│   │   ├── AppointmentService.java   (NEW - 3 dependencies)
│   │   ├── PrescriptionService.java  (NEW)
│   │   └── BillingService.java       (NEW)
│   ├── controller/
│   │   ├── AppointmentController.java(NEW)
│   │   ├── PrescriptionController.java (NEW)
│   │   └── BillingController.java    (NEW)
│   ├── repository/
│   │   ├── AppointmentRepository.java (NEW)
│   │   ├── PrescriptionRepository.java(NEW)
│   │   └── BillRepository.java       (NEW)
│   └── exception/
│       ├── DoctorNotFoundException.java (NEW)
│       ├── AppointmentNotFoundException.java (NEW)
│       ├── AppointmentConflictException.java (NEW)
│       ├── AppointmentNotCancellableException.java (NEW)
│       ├── DoctorCapacityExceededException.java (NEW)
│       ├── BillNotFoundException.java (NEW)
│       ├── BillAlreadyPaidException.java (NEW)
│       └── InvalidAppointmentStatusException.java (NEW)
└── frontend/
    └── src/pages/
        ├── FindDoctor.jsx (NEW)
        ├── BookAppointment.jsx (NEW)
        ├── MyAppointments.jsx (NEW)
        ├── Prescriptions.jsx (NEW)
        ├── Bills.jsx (NEW)
        ├── DoctorDashboard.jsx (NEW)
        └── AdminPanel.jsx (NEW)
```

---

## 🎯 3. Domain Entities & Validation Rules (Sprint 2 Extensions)

### Entity 3: `Appointment`
- **Fields:** `id`, `patient` (`@ManyToOne`), `doctor` (`@ManyToOne`), `appointmentDateTime` (`@Future`), `status` (`SCHEDULED`, `COMPLETED`, `CANCELLED`), `notes` (`@Size(max=500)`), `createdAt`, `cancelledAt`

### Entity 4: `Prescription`
- **Fields:** `id`, `appointment` (`@ManyToOne`), `medicationName` (`@NotBlank`), `dosageMg` (`@Min(1)`, `@Max(5000)`), `frequencyPerDay` (`@Min(1)`, `@Max(4)`), `durationDays` (`@Min(1)`, `@Max(365)`), `instructions` (`@Size(max=500)`)

### Entity 5: `Bill`
- **Fields:** `id`, `appointment` (`@OneToOne`), `consultationFee`, `medicineCost` (`@Min(0)`), `discountPercent` (`@Min(0)`, `@Max(100)`), `totalAmount`, `paid`, `paidAt`

---

## ⚙️ 4. Multi-Step Services & Business Rules

### 1. `AppointmentService.bookAppointment(...)`
- **Multi-Step `@Transactional` Workflow:**
  1. Validates `Patient` exists and `active == true`.
  2. Validates `Doctor` exists and `available == true`.
  3. Checks doctor schedule for conflicts (`AppointmentConflictException`).
  4. Validates doctor daily appointment limit (`DoctorCapacityExceededException`).
  5. Saves `Appointment` entity (`SCHEDULED`).
  6. Calls `BillingService.createBillForAppointment(appointment)` to generate bill atomically.

### 2. `AppointmentService.cancelAppointment(appointmentId)`
- **24-Hour Cancellation Rule:**
  - `appointmentDateTime - currentDateTime > 24 hours` required.
  - Otherwise throws `AppointmentNotCancellableException`.

### 3. `PrescriptionService.createPrescription(...)`
- **Safety Boundary & Status Rules:**
  - Appointment must be in `COMPLETED` status (`InvalidAppointmentStatusException`).
  - `dosageMg` BVA: `1mg` (smallest safe dose) to `5000mg` (maximum ceiling).

---

## 🧪 5. Testing Target Matrix for Sprint 2

| Test Type | Target Method | Scanner Validation Purpose |
|---|---|---|
| **Multi-Mock Service Test** | `bookAppointment` | Verifies Mockito wiring across 4 dependencies |
| **Transactional Rollback** | `bookAppointment` | Verifies DB state resets if billing creation fails |
| **24-Hour BVA Boundary** | `cancelAppointment` | Verifies precise LocalDateTime boundary checks |
| **Dosage Safety BVA** | `createPrescription` | Tests `@Min(1)` and `@Max(5000)` boundary conditions |
| **WebMvcTest Integration** | Controllers | Verifies Spring `@WebMvcTest` endpoint mocking |
