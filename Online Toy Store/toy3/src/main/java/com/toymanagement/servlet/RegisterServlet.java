package com.toymanagement.servlet;

import com.toymanagement.model.User;
import com.toymanagement.util.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Input validation
        if (username == null || username.trim().isEmpty() || password == null || password.length() < 6) {
            request.setAttribute("error", "Username cannot be empty and password must be at least 6 characters.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        List<User> users = FileHandler.readUsers();
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User newUser = new User(username, password);
        users.add(newUser);
        FileHandler.writeUsers(users);

        request.setAttribute("message", "Registration successful! Please login.");
        request.getRequestDispatcher("userLogin.jsp").forward(request, response);
    }
}