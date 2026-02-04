<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurants - Restaurant System</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 20px;
        }

        .navbar {
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .restaurant-card {
            border: none;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            transition: transform 0.3s;
            margin-bottom: 20px;
        }

        .restaurant-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 15px rgba(0,0,0,0.2);
        }

        .restaurant-img {
            height: 220px;
            width: 100%;
            object-fit: cover;
        }

        .badge {
            font-size: 0.8em;
            padding: 5px 10px;
        }

        .cart-count {
            position: absolute;
            top: -5px;
            right: -5px;
            background: #dc3545;
            color: white;
            border-radius: 50%;
            width: 20px;
            height: 20px;
            font-size: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>

<body>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-light bg-white">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/restaurants">
            <i class="fas fa-utensils"></i> Restaurant System
        </a>

        <div class="d-flex align-items-center">
            <span class="me-3">Welcome, ${user.username}</span>

            <a href="${pageContext.request.contextPath}/restaurants/cart"
               class="btn btn-outline-primary position-relative me-2">
                <i class="fas fa-shopping-cart"></i>
                <span class="cart-count" id="cartCount">0</span>
            </a>

            <a href="${pageContext.request.contextPath}/logout"
               class="btn btn-outline-danger">
                <i class="fas fa-sign-out-alt"></i>
            </a>
        </div>
    </div>
</nav>

<!-- RESTAURANTS -->
<div class="container mt-4">
    <h2 class="mb-4">🍽️ Select a Restaurant</h2>

    <div class="row">
        <c:forEach var="restaurant" items="${restaurants}">
            <div class="col-md-6 col-lg-4">
                <div class="card restaurant-card">

                    <!-- ✅ IMAGE FIXED -->
                    <img src="${restaurant.imageUrl}" class="restaurant-img" alt="${restaurant.name}">

                    <div class="card-body">
                        <h5 class="card-title">${restaurant.name}</h5>
                        <p class="card-text text-muted">${restaurant.description}</p>

                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <span class="badge bg-info">
                                    ${restaurant.menuItems.size()} items
                                </span>
                                <small class="text-muted ms-2">
                                    <i class="fas fa-map-marker-alt"></i>
                                    ${restaurant.address}
                                </small>
                            </div>

                            <a href="${pageContext.request.contextPath}/restaurants/${restaurant.id}"
                               class="btn btn-primary">
                                View Menu <i class="fas fa-arrow-right"></i>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Simple cart count using localStorage
    function updateCartCount() {
        const cart = JSON.parse(localStorage.getItem("cart")) || {};
        let total = 0;
        for (let k in cart) total += cart[k];
        document.getElementById("cartCount").textContent = total;
    }

    updateCartCount();
</script>

</body>
</html>
