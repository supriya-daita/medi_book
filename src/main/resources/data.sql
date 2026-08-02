-- Initial Doctor Data
INSERT INTO doctors (id, name, specialization, consultation_fee, max_appointments_per_day, available) VALUES 
(1, 'Dr. Sarah Connor', 'Cardiology', 800.0, 15, true),
(2, 'Dr. Gregory House', 'Diagnostics & Internal Medicine', 1500.0, 10, true),
(3, 'Dr. Meredith Grey', 'General Surgery', 1200.0, 12, true),
(4, 'Dr. Leonard McCoy', 'Pediatrics', 500.0, 20, true);

-- Initial Patient Data
INSERT INTO patients (id, name, email, age, phone, active, medical_history) VALUES 
(1, 'John Doe', 'john.doe@example.com', 35, '9876543210', true, 'No known allergies. History of hypertension.'),
(2, 'Alice Smith', 'alice.smith@example.com', 65, '9123456789', true, 'Senior citizen. Diabetic type 2.'),
(3, 'Timmy Johnson', 'timmy.j@example.com', 8, '9988776655', true, 'Pediatric patient. Asthma history.');
