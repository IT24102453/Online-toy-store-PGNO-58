package com.toymanagement.model;

public class Payment {
    private String username;
    private String toyName;
    private double amount;
    private String paymentMethod;

    public Payment(String username, String toyName, double amount, String paymentMethod) {
        this.username = username;
        this.toyName = toyName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
    // Getters
    public String getUsername() { return username; }
    public String getToyName() { return toyName; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }

    
