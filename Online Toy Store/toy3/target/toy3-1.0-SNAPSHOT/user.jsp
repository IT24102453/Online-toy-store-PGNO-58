<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.toymanagement.model.Toy" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>User Dashboard</h1>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h2>Order a Toy</h2>
    <% if (request.getAttribute("toys") != null) { %>
    <form action="orderToy" method="post">
        <label for="toyName">Select Toy:</label>
        <select id="toyName" name="toyName" required>
            <% for (Toy toy : (List<Toy>) request.getAttribute("toys")) { %>
            <option value="<%= toy.getToyName() %>|<%= toy.getToyPrice() %>">
                <%= toy.getToyName() %> (<%= toy.getAgeGroup() %>, <%= toy.getCategory() %>) - $<%= toy.getToyPrice() %>
            </option>
            <% } %>
        </select><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br>
        <label for="phoneNumber">Phone Number (10 digits):</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required><br>
        <input type="submit" value="Proceed to Payment">
    </form>
    <% } else { %>
    <p>No toys available to order.</p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
    <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <% if (request.getAttribute("message") != null) { %>
    <p style="color: green;"><%= request.getAttribute("message") %></p>
    <% } %>
</main>
<footer>
    <p>© 2025 Toy Management System</p>
</footer>
</body>
</html>