package com.toymanagement.util;

import com.toymanagement.model.Toy;
import com.toymanagement.model.Order;
import com.toymanagement.model.Payment;
import com.toymanagement.model.User;

import jakarta.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // Changed to use ServletContext-based paths
    private static String TOYS_FILE_PATH;
    private static String ORDERS_FILE_PATH;
    private static String PAYMENTS_FILE_PATH;
    private static String USERS_FILE_PATH;

    // Initialize with ServletContext
    public static void initialize(ServletContext context) {
        String DATA_DIRECTORY = context.getRealPath("/") + "data/";

        // Create data directory if it doesn't exist
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        // Set file paths
        TOYS_FILE_PATH = DATA_DIRECTORY + "toys.txt";
        ORDERS_FILE_PATH = DATA_DIRECTORY + "orders.txt";
        PAYMENTS_FILE_PATH = DATA_DIRECTORY + "payments.txt";
        USERS_FILE_PATH = DATA_DIRECTORY + "users.txt";
    }

    // Toy File Handling
    public static void writeToys(List<Toy> toys) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TOYS_FILE_PATH))) {
            for (Toy toy : toys) {
                writer.write(toy.toString());
                writer.newLine();
            }
        }
    }

    public static List<Toy> readToys() throws IOException {
        List<Toy> toys = new ArrayList<>();
        File file = new File(TOYS_FILE_PATH);
        if (!file.exists()) {
            return toys;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(TOYS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    toys.add(Toy.fromString(line));
                }
            }
        }
        return toys;
    }

    // Order File Handling
    public static void writeOrders(List<Order> orders) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE_PATH))) {
            for (Order order : orders) {
                writer.write(order.toString());
                writer.newLine();
            }
        }
    }

    public static List<Order> readOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        File file = new File(ORDERS_FILE_PATH);
        if (!file.exists()) {
            return orders;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    orders.add(Order.fromString(line));
                }
            }
        }
        return orders;
    }

    // Payment File Handling
    public static void writePayments(List<Payment> payments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAYMENTS_FILE_PATH))) {
            for (Payment payment : payments) {
                writer.write(payment.toString());
                writer.newLine();
            }
        }
    }

    public static List<Payment> readPayments() throws IOException {
        List<Payment> payments = new ArrayList<>();
        File file = new File(PAYMENTS_FILE_PATH);
        if (!file.exists()) {
            return payments;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYMENTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    payments.add(Payment.fromString(line));
                }
            }
        }
        return payments;
    }

    // User File Handling
    public static void writeUsers(List<User> users) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE_PATH))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        }
    }

    public static List<User> readUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE_PATH);
        if (!file.exists()) {
            return users;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    users.add(User.fromString(line));
                }
            }
        }
        return users;
    }
}