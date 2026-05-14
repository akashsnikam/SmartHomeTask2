
# Smart Home Dashboard

## Features
- Add smart devices (Light, Fan, AC, Sensor)
- Control devices (ON / OFF)
- Motion detection simulation
- Automation rule engine (IF-THEN logic)
- Real-time device state update
- Rule execution system

## 🛠️ Tech Stack

- Backend: Spring Boot (Java)
- Frontend: HTML, CSS, JavaScript
- Data Storage: In-memory (HashMap + List)
- API: REST APIs

Run:Backend
mvn spring-boot:run

 Run Frontend
Open index.html using Live Server

## APIs
GET /api/devices
POST /api/devices/{id}/control
POST /api/rules
GET /api/rules
POST /api/simulate-trigger/{id}

## Test Case
1. Motion Sensor detects movement
2. Rule triggers
3. Living Light turns ON



## Project Structure 
com.architechlabs
├── controller
│ └── SmartHomeController.java
├── service
│ └── SmartHomeService.java
├── model
│ ├── Device.java
│ └── AutomationRule.java



