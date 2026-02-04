<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart - Restaurant System</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .cart-item {
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 20px;
            margin-bottom: 20px;
        }
        .item-img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 8px;
        }
        .quantity-control {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .quantity-btn {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            border: 1px solid #ddd;
            background: white;
            cursor: pointer;
        }
        .empty-cart {
            text-align: center;
            padding: 100px 0;
        }
        .empty-cart i {
            font-size: 5em;
            color: #ddd;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light bg-white">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/restaurants">
            <i class="fas fa-arrow-left"></i> Back to Restaurants
        </a>

        <div class="d-flex align-items-center">
            <span class="me-3">Welcome, ${user.username}!</span>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-danger">
                <i class="fas fa-sign-out-alt"></i> Logout
            </a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h2 class="mb-4">🛒 Your Shopping Cart</h2>

    <c:choose>
        <c:when test="${not empty cartItems}">
            <div class="row">

                <!-- Cart Items -->
                <div class="col-md-8">
                    <c:forEach var="cartItem" items="${cartItems}">
                        <div class="cart-item">
                            <div class="row align-items-center">

                                <div class="col-md-2">
                                    <!-- ✅ FIXED IMAGE PATH -->
                                    <img src="${pageContext.request.contextPath}${cartItem.menuItem.imageUrl}"
                                         alt="${cartItem.menuItem.name}"
                                        class="item-img">
                                    
                                </div>

                                <div class="col-md-6">
                                    <h5>${cartItem.menuItem.name}</h5>
                                    <p class="text-muted mb-0">${cartItem.menuItem.description}</p>
                                    <small class="text-muted">${cartItem.menuItem.category}</small>
                                </div>

                                <div class="col-md-2">
                                    <h5 class="text-success">₹${cartItem.menuItem.price}</h5>
                                </div>

                                <div class="col-md-2">
                                    <div class="quantity-control">
                                        <button class="quantity-btn"
                                                onclick="updateCart(${cartItem.menuItem.id}, -1)">
                                            <i class="fas fa-minus"></i>
                                        </button>
                                        <span class="mx-2">${cartItem.quantity}</span>
                                        <button class="quantity-btn"
                                                onclick="updateCart(${cartItem.menuItem.id}, 1)">
                                            <i class="fas fa-plus"></i>
                                        </button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- Order Summary -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0">Order Summary</h5>
                        </div>

                        <div class="card-body">
                            <div class="d-flex justify-content-between mb-2">
                                <span>Subtotal</span>
                                <span>₹${total}</span>
                            </div>

                            <div class="d-flex justify-content-between mb-2">
                                <span>Tax (5%)</span>
                                <%
                                    double tax = Double.parseDouble(
                                            request.getAttribute("total").toString()) * 0.05;
                                    request.setAttribute("tax", tax);
                                %>
                                <span>₹${tax}</span>
                            </div>

                            <div class="d-flex justify-content-between mb-2">
                                <span>Delivery</span>
                                <span>₹50.00</span>
                            </div>

                            <hr>

                            <div class="d-flex justify-content-between mb-3">
                                <strong>Total</strong>
                                <%
                                    double delivery = 50.0;
                                    double totalAmount =
                                            Double.parseDouble(request.getAttribute("total").toString())
                                                    + tax + delivery;
                                    request.setAttribute("finalTotal", totalAmount);
                                %>
                                <strong>₹${finalTotal}</strong>
                            </div>

                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" onclick="checkout()">
                                    <i class="fas fa-shopping-bag"></i> Proceed to Checkout
                                </button>

                                <button class="btn btn-outline-danger" onclick="clearCart()">
                                    <i class="fas fa-trash"></i> Clear Cart
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </c:when>

        <c:otherwise>
            <div class="empty-cart">
                <i class="fas fa-shopping-cart"></i>
                <h3>Your cart is empty</h3>
                <p class="text-muted">Add some delicious items from our restaurants!</p>
                <a href="${pageContext.request.contextPath}/restaurants" class="btn btn-primary">
                    <i class="fas fa-utensils"></i> Browse Restaurants
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function updateCart(itemId, change) {
        fetch('${pageContext.request.contextPath}/restaurants/cart/add/' + itemId, {
            method: 'POST'
        }).then(() => {
            if (change === -1) {
                fetch('${pageContext.request.contextPath}/restaurants/cart/remove/' + itemId, {
                    method: 'POST'
                }).then(() => location.reload());
            } else {
                location.reload();
            }
        });
    }

    function clearCart() {
        if (confirm('Are you sure you want to clear your cart?')) {
            fetch('${pageContext.request.contextPath}/restaurants/cart/clear', {
                method: 'POST'
            }).then(() => location.reload());
        }
    }

    function checkout() {
        alert('Order placed successfully! Total: ₹${finalTotal}');
        fetch('${pageContext.request.contextPath}/restaurants/cart/clear', {
            method: 'POST'
        }).then(() => {
            localStorage.removeItem('cart');
            window.location.href = '${pageContext.request.contextPath}/restaurants';
        });
    }
</script>

</body>
</html>
