<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.toymanagement.model.Toy" %>
<html>
<head>
  <title>Update Toy</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
  <h1>Update Toy</h1>
  <nav>
    <ul>
      <li><a href="adminDashboard.jsp">Back to Dashboard</a></li>
    </ul>
  </nav>
</header>
<main>
  <h2>Edit Toy Details</h2>
  <% Toy toy = (Toy) request.getAttribute("toy"); %>
  <form action="manageToys" method="post">
    <input type="hidden" name="index" value="<%= request.getAttribute("index") %>">
    <label for="toyName">Toy Name:</label>
    <input type="text" id="toyName" name="toyName" value="<%= toy.getToyName() %>" required><br>
    <label for="ageGroup">Age Group:</label>
    <input type="text" id="ageGroup" name="ageGroup" value="<%= toy.getAgeGroup() %>" required><br>
    <label for="toyPrice">Toy Price:</label>
    <input type="number" id="toyPrice" name="toyPrice" value="<%= toy.getToyPrice() %>" step="0.01" required><br>
    <label for="category">Category:</label>
    <select id="category" name="category">
      <option value="General" <%= "General".equals(toy.getCategory()) ? "selected" : "" %>>General</option>
      <option value="Educational" <%= "Educational".equals(toy.getCategory()) ? "selected" : "" %>>Educational</option>
      <option value="Action Figures" <%= "Action Figures".equals(toy.getCategory()) ? "selected" : "" %>>Action Figures</option>
      <option value="Puzzles" <%= "Puzzles".equals(toy.getCategory()) ? "selected" : "" %>>Puzzles</option>
    </select><br>
    <input type="submit" name="action" value="Update Toy">
  </form>
</main>
<footer>
  <p>© 2025 Toy Management System</p>
</footer>
</body>
</html>