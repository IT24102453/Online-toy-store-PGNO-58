<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .tabs {
            display: flex;
            gap: 1rem;
            margin-bottom: 1rem;
            justify-content: center;
        }
        .tab {
            background-color: #4CAF50;
            color: white;
            padding: 0.5rem 1rem;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .tab:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("admin.jsp");
        return;
    }
%>
<header>
    <h1>Admin Dashboard</h1>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <div class="tabs">
        <a href="manageToys.jsp" class="tab">Manage Toys</a>
        <a href="toyInventory.jsp" class="tab">Toy Inventory</a>
        <a href="userOrders.jsp" class="tab">User Orders</a>
        <a href="userPayments.jsp" class="tab">User Payments</a>
    </div>

    <h2>Toy Inventory Overview</h2>
    <jsp:include page="toyInventory.jsp"/>

    <h2>User Orders Overview</h2>
    <jsp:include page="userOrders.jsp"/>

    <h2>User Payments Overview</h2>
    <jsp:include page="userPayments.jsp"/>
</main>
<footer>
    <p>© 2025 Toy Management System</p>
</footer>
</body>
</html>