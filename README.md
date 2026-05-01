# 🚆  Railway Ticket Reservation Management System

A full-stack Railway Reservation System that allows users to search trains, book tickets, and manage reservations through a responsive and interactive web interface. This project demonstrates real-world booking workflows using REST APIs and dynamic UI rendering.

---

## 📌 Overview

The system simulates core functionalities of modern railway booking platforms, including train search, seat selection, passenger management, and ticket booking with PNR generation.

---

## ✨ Features

- 🔐 User Authentication (Register & Login)
- 🔍 Train Search
  - Search by source and destination
  - View all available trains
- 🚆 Dynamic Train Listing (Card-based UI)
- 🎟️ Ticket Booking
  - Select coach (1AC, 2AC, 3AC, Sleeper, 2S)
  - Real-time seat availability display
  - Add multiple passengers
  - Berth preference selection
- 💳 Payment Mode Selection (UPI, Card, Net Banking)
- 📊 Booking Confirmation with PNR Number
- 📋 View Reservation Status
- 🚪 Logout functionality

---

## 🖥️ Tech Stack

### Frontend
- HTML5
- CSS3
- JavaScript (Vanilla JS)

### Backend
- Java
- Spring Boot (REST APIs)

### Data Exchange
- JSON (Client-Server Communication)

---

## 📁 Project Structure


├── index.html # Login & Registration Page
├── dashboard.html # Reservation Dashboard
├── script.js # Authentication Logic
├── dashboard.js # Booking & Train Logic
├── style.css # UI Styling


---

## 🔗 API Endpoints

| Method | Endpoint | Description |
|--------|---------|------------|
| POST   | /auth/register | Register a new user |
| POST   | /auth/login | Authenticate user |
| GET    | /reservation/trains | Fetch all trains |
| GET    | /reservation/search | Search by train number |
| GET    | /reservation/searchStations | Search trains by stations |
| POST   | /reservation/book | Book a ticket |
| GET    | /reservation/status | Get reservation details |

---

## ⚙️ Getting Started

### 1. Run Backend

Ensure your Spring Boot backend is running on:


http://localhost:8080


---

### 2. Run Frontend

Open the following file in your browser:


index.html


---

## 🧪 Usage Flow

1. Register a new account  
2. Login using credentials  
3. Search for trains  
4. Select a train and coach  
5. Add passenger details  
6. Choose payment method  
7. Confirm booking  
8. Receive PNR number  
9. Check reservation status  

---

## 💡 Key Highlights

- Modular architecture separating frontend and backend
- Dynamic UI updates using JavaScript
- REST API integration for real-time operations
- Multi-passenger booking system
- Input validation and error handling
- Session handling using browser localStorage

---

## 🚀 Future Enhancements

- JWT-based authentication & authorization
- Payment gateway integration
- Admin dashboard for train management
- Ticket PDF generation
- Mobile responsive design
- Email/SMS notifications
- Live train tracking

---

## ⚠️ Notes

- Backend server must be running before using the frontend
- Ensure CORS is enabled in the backend
- Default API base URL:

http://localhost:8080


## 👨‍💻 Author

Deepak Salluri
B.Tech Computer Science Engineering  
Aspiring Java Full Stack Developer  

