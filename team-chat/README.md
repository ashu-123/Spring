# 💬 Team Chat

**Team Chat** is a real-time chat application built with **Spring Boot**, leveraging **WebSockets** and **RabbitMQ** for message handling and delivery. The frontend is a simple, responsive interface built with **HTML5**, **CSS**, and **JavaScript**, served directly from the Spring Boot application.

---

## 🚀 Features

- 🔁 Real-time messaging via **WebSockets**
- 📦 Message brokering using **RabbitMQ**
- ✅ User join/leave notifications in chat room
- 🖥️ Simple frontend UI served from Spring Boot static resources
- 🧩 Seamless integration between frontend and backend

---

## ⚙️ Requirements

- Java 17+
- Maven
- RabbitMQ server running (default port: `5672`)
- (Optional) Docker, if you want to run RabbitMQ easily

---

## 🧪 How to Run

### 🐇 Start RabbitMQ (Docker Option)
If you don’t have RabbitMQ installed, you can use Docker:

```bash
docker run -d --hostname rabbitmq --name teamchat-rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Management UI will be available at http://localhost:15672 (default user: guest, password: guest).

### 🏃 Run the Spring Boot App

- git clone https://github.com/your-username/team-chat.git
- cd team-chat
- mvn spring-boot:run

### 🌐 Access the App
Once the app is running, open your browser and go to: http://localhost:8080

Enter your username to join the chat room and start messaging in real-time.

---

## 📦 WebSocket Endpoints

- WebSocket Endpoint: /websocketApp

- Message Broker: /topic/interact

- User Join Message: Sent when a user connects

- User Leave Message: Sent when a user disconnects

---

## 💻 Frontend Overview

Frontend is a static web page served from: src/main/resources/static/

Includes:

- index.html: Main chat interface

- style.css: Basic styling

- script.js: Handles WebSocket events, message sending, and UI updates

---

## 📌 Event Flow

1. User connects → "User has joined" message broadcasted.
2. User sends a message → Relayed via RabbitMQ to all connected clients.
3. User disconnects → "User has left" message broadcasted.

---

## 📈 Future Improvements

- User authentication and avatars
- Private/group chat support
- Message history (persistence with a database)
- Typing indicators and online user lists

---

## 🛡 License
This project is licensed under the MIT License.

---

## 👨‍💻 Author
Built by Ashutosh Mishra using Spring Boot, WebSockets, and RabbitMQ.