# 🚀 Spring Boot Microservices Architecture

This project demonstrates a **Spring Boot–based microservices architecture** with **service discovery**, **API Gateway**, **inter-service communication**, and **fault tolerance** using **Resilience4j**.

---

## 🧩 Microservices Overview

In this version of the project, the following microservices are implemented:

- **User Service**
- **Department Service**

Each service is designed to be **independent** and can use its **own database**.  
For learning purposes, an **H2 in-memory database** is used.

---

## 🔍 Service Discovery (Eureka)

A **Service Registry** is implemented using **Netflix Eureka Server**.

### Key Features:
- All microservices register themselves with Eureka
- Tracks which services are **UP** or **DOWN**
- Enables **dynamic service discovery** instead of hard-coded URLs

---

## 🌐 API Gateway

An **API Gateway** acts as a **single entry point** for all client requests.

### Responsibilities:
- Request routing
- Load balancing
- Circuit breaker and fallback (client-facing)
- Centralized access point for all services

### Technologies Used:
- Spring Cloud Gateway (Reactive / WebFlux)
- LoadBalancer
- Resilience4j (Circuit Breaker)
- Spring Boot Actuator

---

## 🔄 Inter-Service Communication

Initially, services communicate using **service names** instead of direct URLs.

### This avoids:
- Tight coupling
- Hard-coded service addresses
- Scalability issues as services grow

---

## 🚧 Problem Statement

Gateway-level fault tolerance works well for:

Client → API Gateway → Microservice


However, an issue arises when:

Client → Gateway → User Service → Department Service (DOWN ❌)

yaml
Copy code

### In this case:
- Gateway fallback **does NOT help**
- Internal service-to-service call fails
- **User Service returns an error**

---

## ⚠️ Root Cause

- API Gateway fallback handles **only external traffic**
- It does **not handle internal service dependencies**
- When one microservice depends on another, resilience must be handled **inside the service itself**

---

## ✅ Solution (Next Version)

To solve this issue, the next version introduces:

- **Feign Client** for inter-service communication
- **Resilience4j** at the service level
- **Service-level Circuit Breaker & Fallback**

### Benefits:
- Each service protects its own dependencies
- Failures are isolated
- Partial responses can still be returned
- System becomes more **fault-tolerant and resilient**

---

## 🏗️ Architecture Evolution

### 🔹 Version 1
- User Service
- Department Service
- Eureka Server
- API Gateway
- Gateway-level Circuit Breaker & Fallback

### 🔹 Version 2 (Improved)
- Feign Client for service-to-service calls
- Resilience4j inside services
- Service-level fallback handling
- Better fault isolation

---

## 🎯 Key Takeaway

> **Gateway-level resilience protects external clients.**  
> **Service-level resilience protects internal dependencies.**

This approach follows **industry best practices** for building **scalable and resilient microservices**.

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Cloud 
- Eureka Server
- Spring Cloud Gateway (WebFlux)
- Resilience4j
- Feign Client
- H2 Database
- Maven

---

## 📌 Future Enhancements

- Rate limiting using Redis
- Centralized logging
- Distributed tracing
- Docker & Kubernetes deployment