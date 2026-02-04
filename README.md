
# 🍽️ Food Delivery Application

### A Complete Full-Stack Java (Spring Boot + Spring MVC + JSP + MySQL) Web Project

This is a fully functional **Food Delivery Web Application** built using **Spring Boot and Spring MVC**.
The project implements a complete end-to-end flow including restaurant listing, menu display, cart management, checkout, comparison feature, and order persistence using MySQL.

---

## 🚀 Features

### ✅ **1. User Authentication**

* Login and Registration functionality
* Dedicated UI buttons
* HTTP session management for secure cart and order placement

### ✅ **2. Restaurant Dashboard**

* Displays all restaurants dynamically from the database
* Clicking a restaurant loads its menu dynamically
* Data handled through Spring MVC controllers

### ✅ **3. Menu Page**

* Menu items rendered dynamically using JSP
* Add-to-cart option for each menu item
* Session-based cart handling

### ✅ **4. Cart Management**

* Add and remove items from cart
* Increase or decrease quantity
* Quantity validation
* Persistent cart during user session

### ✅ **5. Checkout & Order Placement**

* Enter delivery address
* Secure order placement
* Order data stored persistently in MySQL
* Clears cart after successful order

### ✅ **6. Restaurant Comparison**

* Side-by-side menu comparison
* Average price calculation for better user decision-making

### ✅ **7. Responsive UI**

* Built using JSP, Bootstrap, CSS, and JavaScript
* Client-side validation
* Mobile-friendly responsive layout

### ✅ **8. Backend Architecture**

* Spring MVC Controllers for request handling
* Service layer for business logic
* Repository layer using Spring Data JPA
* Hibernate ORM for database operations
* Clean Controller–Service–Repository (MVC) architecture

---

## 🧱 Technologies Used


| Layer        | Technology                            |
| ------------ | ------------------------------------- |
| Frontend     | JSP, HTML, CSS, Bootstrap, JavaScript |
| Backend      | Java, Spring Boot, Spring MVC         |
| ORM          | Hibernate, Spring Data JPA            |
| Database     | MySQL                                 |
| Server       | Embedded Tomcat                       |
| IDE          | Eclipse                               |
| Build Tool   | Maven                                 |
| Architecture | MVC                                   |

---

## 📂 Project Structure (MVC)

```
FoodDeliveryApplication/
│
├── src/main/java
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│
├── src/main/webapp
│   ├── jsp
│   ├── css
│   ├── js
│
├── src/main/resources
│   ├── application.properties
│
├── pom.xml
└── README.md
```

---

## 🔧 How to Run the Project

### 1️⃣ Import to Eclipse

* File → Import → Existing Maven Project
* Select the project and import

### 2️⃣ Configure MySQL

* Create a database
* Update database credentials in `application.properties`

### 3️⃣ Run the Application

* Right-click project → Run As → **Spring Boot App**
* Open in browser:

```
http://localhost:8080/
```

---

## 👨‍💻 Author

**Chigurupati Prasad**
Software Developer | Java | Spring Boot | Web Development

* GitHub: [https://github.com/Cprasad01](https://github.com/Cprasad01)
* LinkedIn: [https://www.linkedin.com/in/chigurupati-prasad/](https://www.linkedin.com/in/chigurupati-prasad/)

---

## ⭐ If You Like This Project

Please ⭐ **star** the repository — it motivates future development!

<p align="center">
  <img src="https://img.shields.io/badge/Food%20Delivery%20App-Spring%20Boot-orange?style=for-the-badge&logo=springboot&logoColor=white">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot%20%7C%20Spring%20MVC%20%7C%20Hibernate%20%7C%20MySQL-blue?style=for-the-badge">
</p>

<p align="center">
  A complete end-to-end food delivery system built using Spring Boot, Spring MVC, Hibernate, and MySQL.
</p>

---


