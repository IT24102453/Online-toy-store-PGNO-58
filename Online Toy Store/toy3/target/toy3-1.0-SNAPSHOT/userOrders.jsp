<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.toymanagement.model.Order" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>User Orders</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>User Orders</h1>
    <nav>
        <ul>
            <li><a href="adminDashboard.jsp">Back to Dashboard</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h2>View User Orders</h2>
    <form action="manageToys" method="post">
        <input type="submit" name="action" value="View Orders">
    </form>
    <% if (request.getAttribute("orders") != null) { %>
    <table border="1">
        <tr>
            <th>Username</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Toy Name</th>
            <th>Price</th>
        </tr>
        <% for (Order order : (List<Order>) request.getAttribute("orders")) { %>
        <tr>
            <td><%= order.getUsername() %></td>
            <td><%= order.getAddress() %></td>
            <td><%= order.getPhoneNumber() %></td>
            <td><%= order.getToyName() %></td>
            <td><%= order.getToyPrice() %></td>
        </tr>
        <% } %>
    </table>
    <% } %>
</main>
<footer>
    <p>© 2025 Toy Management System</p>
</footer>
</body>
</html>