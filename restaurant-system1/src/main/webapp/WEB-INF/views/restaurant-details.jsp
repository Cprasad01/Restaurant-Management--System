<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${restaurant.name} - Restaurant System</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }

        /* HERO SECTION */
        .hero-section {
            background:
                linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.7)),
                url('${pageContext.request.contextPath}${restaurant.imageUrl}');
            background-size: cover;
            background-position: center;
            color: white;
            padding: 100px 0;
            text-align: center;
            margin-bottom: 40px;
        }

        /* MENU CARD */
        .menu-item-card {
            border: none;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            border-radius: 10px;
            overflow: hidden;
            transition: transform 0.3s;
            margin-bottom: 20px;
        }

        .menu-item-card:hover {
            transform: translateY(-5px);
        }

        .menu-item-img {
            height: 200px;
            width: 100%;
            object-fit: cover;
        }

        .price {
            color: #28a745;
            font-weight: bold;
            font-size: 1.2em;
        }

        .quantity-controls {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .quantity-btn {
            width: 35px;
            height: 35px;
            border-radius: 50%;
            border: none;
            background: #007bff;
            color: white;
            font-size: 1.2em;
            cursor: pointer;
        }

        .quantity-btn:hover {
            background: #0056b3;
        }

        .quantity-display {
            font-size: 1.2em;
            font-weight: bold;
            min-width: 40px;
            text-align: center;
        }

        .cart-count {
            position: absolute;
            top: -5px;
            right: -10px;
            background: red;
            color: white;
            border-radius: 50%;
            padding: 2px 6px;
            font-size: 12px;
        }
    </style>
</head>

<body>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/restaurants">
            <i class="fas fa-arrow-left"></i> Back
        </a>

        <div class="d-flex align-items-center">
            <span class="me-3">Welcome, ${user.username}</span>

            <a href="${pageContext.request.contextPath}/restaurants/cart"
               class="btn btn-outline-primary position-relative me-2">
                <i class="fas fa-shopping-cart"></i>
                <span id="cartCount" class="cart-count">0</span>
            </a>

            <a href="${pageContext.request.contextPath}/logout"
               class="btn btn-outline-danger">
                <i class="fas fa-sign-out-alt"></i>
            </a>
        </div>
    </div>
</nav>

<!-- HERO -->
<div class="hero-section">
    <div class="container">
        <h1 class="display-4">${restaurant.name}</h1>
        <p class="lead">${restaurant.description}</p>

        <div class="mt-4">
            <span class="badge bg-light text-dark me-2">
                <i class="fas fa-phone"></i> ${restaurant.phone}
            </span>
            <span class="badge bg-light text-dark me-2">
                <i class="fas fa-envelope"></i> ${restaurant.email}
            </span>
            <span class="badge bg-light text-dark">
                <i class="fas fa-map-marker-alt"></i> ${restaurant.address}
            </span>
        </div>
    </div>
</div>

<!-- MENU ITEMS -->
<div class="container">
    <h2 class="mb-4">📋 Menu Items</h2>

    <div class="row">
        <c:forEach var="item" items="${menuItems}">
            <div class="col-md-4 col-lg-3">
                <div class="card menu-item-card">

                    <!-- ✅ IMAGE FIXED -->
                    <img src="${pageContext.request.contextPath}${item.imageUrl}"
                         class="menu-item-img"
                         alt="${item.name}">

                    <div class="card-body">
                        <h5 class="card-title">${item.name}</h5>
                        <p class="card-text text-muted">${item.description}</p>

                        <div class="d-flex justify-content-between align-items-center mb-2">
                            <span class="price">₹${item.price}</span>
                            <span class="badge bg-secondary">${item.category}</span>
                        </div>

                        <div class="quantity-controls">
                            <button class="quantity-btn" onclick="updateQuantity(${item.id}, -1)">−</button>
                            <span class="quantity-display" id="quantity-${item.id}">0</span>
                            <button class="quantity-btn" onclick="updateQuantity(${item.id}, 1)">+</button>

                            <button class="btn btn-success btn-sm ms-2"
                                    onclick="addToCart(${item.id})">
                                <i class="fas fa-cart-plus"></i>
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- SCRIPTS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    let cart = {};

    function loadCart() {
        const saved = localStorage.getItem("cart");
        if (saved) {
            cart = JSON.parse(saved);
            updateUI();
        }
    }

    function updateQuantity(id, change) {
        cart[id] = (cart[id] || 0) + change;
        if (cart[id] < 0) cart[id] = 0;

        localStorage.setItem("cart", JSON.stringify(cart));
        updateUI();
    }

    function addToCart(id) {
        if (!cart[id] || cart[id] === 0) {
            cart[id] = 1;
        }

        fetch('${pageContext.request.contextPath}/restaurants/cart/add/' + id, {
            method: 'POST'
        }).then(() => {
            localStorage.setItem("cart", JSON.stringify(cart));
            updateUI();
            alert("Item added to cart!");
        });
    }

    function updateUI() {
        let total = 0;

        for (let id in cart) {
            total += cart[id];
            const span = document.getElementById("quantity-" + id);
            if (span) span.textContent = cart[id];
        }

        document.getElementById("cartCount").textContent = total;
    }

    loadCart();
</script>

</body>
</html>
