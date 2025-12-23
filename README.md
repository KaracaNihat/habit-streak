# Habit Streak
## 🎥 Demo: 
Video link: https://youtu.be/132uQSv5n70

In the demo video included in the CS50 submission, I demonstrate:
  * User login
  * Habit creation
  * Habit completion and streak increment
  * Habit deletion

---

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

## 🧠 Design Decisions

During the development of Habit Streak, several design decisions were made to balance simplicity, learning goals, and real-world usability.

One of the most important decisions was choosing a full-stack architecture instead of a single-page or frontend-only application. By building both a backend and frontend, the project demonstrates an understanding of how client-server systems work, including authentication, API design, and data persistence.

For authentication, JWT (JSON Web Tokens) were chosen over session-based authentication. JWTs allow the backend to remain stateless, which simplifies scalability and aligns with modern REST API best practices. Tokens are issued on login and attached to subsequent API requests, ensuring that only authenticated users can access protected resources.

MongoDB was selected as the database instead of a relational SQL database. Since habits are document-oriented and may evolve with additional fields (such as reminders, notes, or history), MongoDB provides flexibility without requiring frequent schema migrations. Using MongoDB Atlas also allowed the application to be hosted in the cloud without managing database infrastructure locally.

On the frontend, React with TypeScript was chosen to enforce type safety and reduce runtime errors. Material UI was used to quickly build a clean, responsive interface while focusing on functionality rather than custom styling from scratch.

The habit streak logic is currently handled in the frontend. This decision was made to keep the backend simpler during initial development. While this approach works well for a single-user application, moving this logic entirely to the backend would be a logical improvement for future scalability and data integrity.

## 🏗️ Detailed Project Structure

The project is organized as a monorepository containing both frontend and backend code to simplify development and deployment.

The habit-streak-backend folder contains the Spring Boot application. It follows a layered architecture with controllers handling HTTP requests, services implementing business logic, repositories managing database access, and DTOs ensuring clean data transfer between layers. Security configuration, JWT utilities, and global exception handling are also centralized to keep the codebase maintainable and scalable.

The habit-streak-frontend folder contains the React application. It includes reusable components, page-level views such as login, registration, and dashboard, and an Axios configuration file responsible for attaching JWT tokens to API requests. State is managed locally using React hooks to keep the application simple and easy to understand.

Both parts of the project communicate via RESTful APIs, making the architecture modular and suitable for future extensions such as mobile applications.

## 🚀 Future Improvements

While Habit Streak is fully functional, there are several features that could be added in the future to enhance the user experience.

One potential improvement is adding a calendar view to visualize habit completion over time. This would give users better insight into their consistency. Another improvement would be daily or weekly reminders, either via email or push notifications, to encourage habit completion.

The streak logic could also be fully migrated to the backend to ensure consistency across devices and prevent manipulation. Additional analytics, such as charts showing habit progress over months, could further motivate users.

Finally, improving mobile responsiveness or developing a dedicated mobile application would make Habit Streak more accessible for daily use.

## 🤖 AI Usage

AI-based tools such as ChatGPT were used as a learning aid during this project. These tools were utilized to clarify concepts, discuss architectural decisions, and receive feedback on implementation ideas. All core logic, structure, and code were written and fully understood by the author. AI tools served only as a productivity aid and did not replace independent problem-solving or design decisions.

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

## 👤 Author
**Nihat Karaca**
