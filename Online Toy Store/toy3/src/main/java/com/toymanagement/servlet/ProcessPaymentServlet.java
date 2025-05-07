package com.toymanagement.servlet;

import com.toymanagement.model.Order;
import com.toymanagement.model.Payment;
import com.toymanagement.util.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/processPayment")
public class ProcessPaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String toyName = request.getParameter("toyName");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        String paymentMethod = request.getParameter("paymentMethod");

        // Create and save order
        Order order = new Order(username, address, phoneNumber, toyName, totalAmount);
        List<Order> orders = FileHandler.readOrders();
        orders.add(order);
        FileHandler.writeOrders(orders);

        // Create and save payment
        Payment payment = new Payment(username, toyName, totalAmount, paymentMethod);
        List<Payment> payments = FileHandler.readPayments();
        payments.add(payment);
        FileHandler.writePayments(payments);

        request.setAttribute("message", "Payment successful! Order placed.");
        request.getRequestDispatcher("user.jsp").forward(request, response);
    }
}