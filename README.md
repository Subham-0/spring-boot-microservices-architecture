# 🚀 Spring Boot Microservices Architecture

This repository demonstrates a **Spring Boot–based microservices architecture** with **service discovery**, **API Gateway**, **inter-service communication**, and **fault tolerance** using **Resilience4j**.

The project is built incrementally to reflect **real-world microservices design decisions**.

---

## 🧩 Microservices Overview

The system consists of the following services:

- **User Service**
- **Department Service**
- **API Gateway**
- **Service Registry (Eureka Server)**

Each microservice is **independent** and can use its **own database**.  
For learning purposes, an **H2 in-memory database** is used.

---

## 🔍 Service Discovery (Eureka Server)

A **Netflix Eureka Server** is used as the **Service Registry**.

### Responsibilities
- Dynamic service registration
- Tracks which services are **UP** or **DOWN**
- Enables communication using **service names** instead of fixed URLs

---

## 🌐 API Gateway

The **API Gateway** acts as a **single entry point** for all client requests.

### Responsibilities
- Request routing
- Load balancing
- Centralized access point
- Client-facing circuit breaker & fallback
- Hides internal service architecture

### Technologies Used
- Spring Cloud Gateway (WebFlux)
- Spring Cloud LoadBalancer
- Resilience4j
- Spring Boot Actuator

---

## 🔄 Inter-Service Communication

Services communicate using **service names registered in Eureka** instead of direct URLs.

### Benefits
- No hard-coded service addresses
- Loose coupling
- Improved scalability
- Easier service replacement

---

## 🧪 Version 1 – Gateway-Level Fault Tolerance

### Architecture Flow
Client → API Gateway → Microservice


### Features Implemented
- API Gateway routing
- Gateway-level Circuit Breaker
- Gateway-level fallback handling
- Eureka-based service discovery

### Limitation Identified ❌
Client → Gateway → User Service → Department Service (DOWN)


- Gateway fallback does **not trigger**
- Internal service-to-service call fails
- User Service returns an error

---

## ⚠️ Root Cause Analysis

- API Gateway handles **only client-facing failures**
- It does **not manage internal service dependencies**
- Microservices must handle failures of services they depend on

---

## ✅ Version 2 – Service-Level Fault Tolerance (Current Version)

To overcome the limitation of Version 1, **service-level resilience** is implemented.

### Enhancements Introduced
- **Feign Client** for inter-service communication
- **Resilience4j Circuit Breaker** inside microservices
- **Service-level fallback methods**
- Support for **CLOSED → OPEN → HALF-OPEN** states

### Updated Architecture Flow
Client → API Gateway → User Service → Department Service
│
└── CircuitBreaker + Fallback


---

## 🛡️ Service-Level Resilience (Best Practice)

### Why This Works
- Each service protects its **own dependencies**
- Failures are **isolated**
- Partial responses can still be returned
- System remains stable even when dependent services fail

### Example
If **Department Service is DOWN**:
- User Service fallback executes
- User data is returned
- Department data is replaced with a safe default response

---

## 🎯 Key Takeaways

> **Gateway-level resilience protects external clients**  
> **Service-level resilience protects internal dependencies**

This layered approach follows **industry-standard microservices architecture practices**.

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Cloud
- Eureka Server
- Spring Cloud Gateway (WebFlux)
- Resilience4j
- OpenFeign
- H2 Database
- Maven

---

## 📌 Future Enhancements

- Centralized logging (ELK Stack)
- Distributed tracing (Zipkin / Micrometer)
- Docker support
- Kubernetes deployment

---

## 🏷️ Version Tags

| Tag                                                                                                                                  | Description                           |
|--------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| [`v1.0`](https://github.com/Subham-0/spring-boot-microservices-architecture/releases/tag/V1.0)                    | Gateway-level Circuit Breaker         |
| [`v2.0-service-resilience`](https://github.com/Subham-0/spring-boot-microservices-architecture/releases/tag/v2.0-service-resilience) | Feign + Resilience4j at service level |
| [`v3.0config-server+zipkin-server`](https://github.com/Subham-0/spring-boot-microservices-architecture/releases/tag/v3.0config-server_and_zipkin_server) | config server + zipkin server |

---
