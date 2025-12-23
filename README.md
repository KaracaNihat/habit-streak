# Habit Streak – CS50 Final Project

## 📌 Description

**Habit Streak** is a full-stack web application that helps users build and maintain positive habits by tracking weekly goals and streaks.

Users can register, log in, create habits, define weekly targets, mark habits as completed, and build streaks when weekly goals are achieved. The application is designed for personal use and focuses on simplicity, consistency, and motivation.

This project was built as the **Final Project for CS50** and demonstrates full-stack development using modern web technologies.

---

## 🎯 Features

- User registration and login using JWT authentication
- Secure REST API with Spring Security
- Create, update, and delete habits
- Weekly habit goals with progress tracking
- One-click habit completion
- Automatic streak increment when weekly targets are met
- Responsive dashboard UI
- Continuous Integration using GitHub Actions

---

## 🛠️ Technologies Used

### Backend
- Java
- Spring Boot
- Spring Security (JWT Authentication)
- MongoDB Atlas
- Maven
- JUnit & integration testing

### Frontend
- React
- TypeScript
- Material UI
- Axios

### Other
- Git
- GitHub
- GitHub Actions (CI)

---

## 🏗️ Project Structure
habit-streak

├─ habit-streak-backend/ # Spring Boot backend

├─ habit-streak-frontend/ # React frontend

└─ README.md

---

## ⚙️ Application Logic Overview

- Each habit has a **weekly target** (e.g. 5 times per week)
- Every time a user marks a habit as completed, the habit progress is incremented
- When the weekly target is reached:
  - The habit streak is incremented by one
  - The completed days are reset for the next week
- This logic is handled in the **frontend** and persisted via `PUT` requests to the backend API

---

## ▶️ How to Run the Project Locally

### Prerequisites
- Java 17+
- Node.js & npm
- MongoDB Atlas account

---

### Backend Setup

1. Navigate to the backend directory:
  * cd habit-streak-backend
2. Configure application.properties with:
  * MongoDB Atlas connection string
  * JWT secret key
3. Run the backend application:
  * ./mvnw spring-boot:run

---

### Frontend Setup

1. Navigate to the frontend directory:
  * cd habit-streak-frontend

2. Install dependencies:
 * npm install

3. Create a .env file in the frontend root:
  * REACT_APP_API_BASE_URL=http://localhost:8080/api

4. Start the frontend:
  * npm start

5. The frontend will be available at:
  * http://localhost:3000

---

## 🔐 Authentication
  * Authentication is handled using JWT tokens
  * Tokens are issued upon login and attached to protected API requests
  * Users must be authenticated to access habit-related endpoints

---

## 🎥 Demo

A demo video is included in the CS50 submission demonstrating:
  * User registration and login
  * Habit creation
  * Habit completion and streak increment
  * Habit deletion
  * Overview of the application architecture

---

## 👤 Author
**Nihat Karaca**
