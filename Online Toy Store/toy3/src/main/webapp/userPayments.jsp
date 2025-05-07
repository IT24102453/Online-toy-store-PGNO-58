<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.toymanagement.model.Payment" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>User Payments</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>User Payments</h1>
    <nav>
        <ul>
            <li><a href="adminDashboard.jsp">Back to Dashboard</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h2>View User Payments</h2>
    <form action="manageToys" method="post">
        <input type="submit" name="action" value="View Payments">
    </form>
    <% if (request.getAttribute("payments") != null) { %>
    <table border="1">
        <tr>
            <th>Username</th>
            <th>Toy Name</th>
            <th>Amount</th>
            <th>Payment Method</th>
        </tr>
        <% for (Payment payment : (List<Payment>) request.getAttribute("payments")) { %>
        <tr>
            <td><%= payment.getUsername() %></td>
            <td><%= payment.getToyName() %></td>
            <td><%= payment.getAmount() %></td>
            <td><%= payment.getPaymentMethod() %></td>
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