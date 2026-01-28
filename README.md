# Gym Management System

## Features

### User Authentication

* JWT-based login system
* Secure authentication & authorization

### Gym Management

* Manage gym members
* Manage trainers
* Membership handling
* Attendance tracking

### REST API Design

* Clean RESTful APIs
* DTO-based request & response
* Layered architecture (Controller, Service, Repository)

---

## Tech Stack

* **Backend:** Java 17, Spring Boot
* **Database:** MySQL / PostgreSQL
* **Security:** JWT
* **Build Tool:** Maven

---

## Getting Started

### Prerequisites

* Java 17+
* Maven
* Docker

### Steps

Clone the repository:

```bash
git clone https://github.com/Hemant210/Gym-management-system.git
cd Gym-management-system
```

Build the project:

```bash
mvn clean install
```

Run using Docker:

```bash
docker-compose up
```

---

## API Endpoints

POST /api/auth/register → Register user
POST /api/auth/login → Login & get JWT
POST /api/auth/logout → Logout user

POST /api/admin/member → Add member
GET /api/admin/members → View members
POST /api/admin/trainer → Add trainer
GET /api/admin/trainers → View trainers

