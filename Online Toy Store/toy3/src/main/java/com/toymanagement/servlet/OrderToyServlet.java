package com.toymanagement.servlet;

import com.toymanagement.model.Toy;
import com.toymanagement.util.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderToy")
public class OrderToyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        List<Toy> toys = FileHandler.readToys();
        if (toys.isEmpty()) {
            request.setAttribute("error", "No toys available to order.");
        }
        request.setAttribute("toys", toys);
        request.getRequestDispatcher("user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        String username = (String) session.getAttribute("user");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        if (!phoneNumber.matches("\\d{10}")) {
            request.setAttribute("error", "Invalid phone number. Must be 10 digits.");
            doGet(request, response);
            return;
        }

        String[] toyData = request.getParameter("toyName").split("\\|");
        String toyName = toyData[0];
        double toyPrice = Double.parseDouble(toyData[1]);

        request.setAttribute("username", username);
        request.setAttribute("address", address);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("toyName", toyName);
        request.setAttribute("totalAmount", toyPrice);
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }
}