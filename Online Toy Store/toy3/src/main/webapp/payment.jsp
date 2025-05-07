<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Payment</h1>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h2>Order Summary</h2>
    <p>Username: <%= request.getAttribute("username") %></p>
    <p>Address: <%= request.getAttribute("address") %></p>
    <p>Phone Number: <%= request.getAttribute("phoneNumber") %></p>
    <p>Toy Name: <%= request.getAttribute("toyName") %></p>
    <p>Total Amount: <%= request.getAttribute("totalAmount") %></p>

    <h2>Select Payment Method</h2>
    <form action="processPayment" method="post">
        <!-- Pass all order details as hidden fields -->
        <input type="hidden" name="username" value="<%= request.getAttribute("username") %>">
        <input type="hidden" name="address" value="<%= request.getAttribute("address") %>">
        <input type="hidden" name="phoneNumber" value="<%= request.getAttribute("phoneNumber") %>">
        <input type="hidden" name="toyName" value="<%= request.getAttribute("toyName") %>">
        <input type="hidden" name="totalAmount" value="<%= request.getAttribute("totalAmount") %>">

        <label for="paymentMethod">Payment Method:</label>
        <select id="paymentMethod" name="paymentMethod" required>
            <option value="Credit Card">Credit Card</option>
            <option value="Debit Card">Debit Card</option>
            <option value="Cash on Delivery">Cash on Delivery</option>
        </select><br>
        <input type="submit" value="Confirm Payment">
    </form>
</main>
<footer>
    <p>© 2025 Toy Management System</p>
</footer>
</body>
</html>