<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Toys</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Manage Toys</h1>
    <nav>
        <ul>
            <li><a href="adminDashboard.jsp">Back to Dashboard</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h2>Add New Toy</h2>
    <form action="manageToys" method="post">
        <label for="toyName">Toy Name:</label>
        <input type="text" id="toyName" name="toyName" required><br>
        <label for="ageGroup">Age Group:</label>
        <input type="text" id="ageGroup" name="ageGroup" required><br>
        <label for="toyPrice">Toy Price:</label>
        <input type="number" id="toyPrice" name="toyPrice" step="0.01" required><br>
        <label for="category">Category:</label>
        <select id="category" name="category">
            <option value="General">General</option>
            <option value="Educational">Educational</option>
            <option value="Action Figures">Action Figures</option>
            <option value="Puzzles">Puzzles</option>
        </select><br>
        <input type="submit" name="action" value="Add Toy">
    </form>

    <% if (request.getAttribute("message") != null) { %>
    <p style="color: green;"><%= request.getAttribute("message") %></p>
    <% } %>
</main>
<footer>
    <p>© 2025 Toy Management System</p>
</footer>
</body>
</html>