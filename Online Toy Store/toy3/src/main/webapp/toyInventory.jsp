<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.toymanagement.model.Toy" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Toy Inventory</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Toy Inventory</h1>
    <nav>
        <ul>
            <li><a href="adminDashboard.jsp">Back to Dashboard</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h2>View Toys</h2>
    <form action="manageToys" method="post">
        <label for="filterCategory">Filter by Category:</label>
        <select id="filterCategory" name="filterCategory">
            <option value="">All</option>
            <option value="General">General</option>
            <option value="Educational">Educational</option>
            <option value="Action Figures">Action Figures</option>
            <option value="Puzzles">Puzzles</option>
        </select>
        <input type="submit" name="action" value="View Toys">
    </form>
    <form action="manageToys" method="post">
        <input type="submit" name="action" value="Sort by Age Group">
    </form>


    <% if (request.getAttribute("toys") != null) { %>
    <table border="1">
        <tr>
            <th>Toy Name</th>
            <th>Age Group</th>
            <th>Price</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        <%
            List<Toy> toys = (List<Toy>) request.getAttribute("toys");
            for (int i = 0; i < toys.size(); i++) {
                Toy toy = toys.get(i);
        %>
        <tr>
            <td><%= toy.getToyName() %></td>
            <td><%= toy.getAgeGroup() %></td>
            <td><%= toy.getToyPrice() %></td>
            <td><%= toy.getCategory() %></td>
            <td>
                <form action="manageToys" method="post" style="display:inline;">
                     <input type="hidden" name="index" value="<%= i %>">
                    <input type="submit" name="action" value="Update">
                </form>
                <form action="manageToys" method="post" style="display:inline;">
                    <input type="hidden" name="index" value="<%= i %>">
                    <input type="submit" name="action" value="Delete">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <% } %>

    <% if (request.getAttribute("message") != null) { %>
    <p style="color: green;"><%= request.getAttribute("message") %></p>
    <% } %>
</main>
<%--<footer>--%>
<%--    <p>© 2025 Toy Management System</p>--%>
<%--</footer>--%>
</body>
</html>