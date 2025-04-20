package com.toymanagement.model;

public class Order {
    private String username;
    private String address;
    private String phoneNumber;
    private String toyName;
    private double toyPrice;

    public Order(String username, String address, String phoneNumber, String toyName, double toyPrice) {
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.toyName = toyName;
        this.toyPrice = toyPrice;
    }

    public String getUsername() { return username; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getToyName() { return toyName; }
    public double getToyPrice() { return toyPrice; }

    @Override
    public String toString() {
        return username + "," + address + "," + phoneNumber + "," + toyName + "," + toyPrice;
    }

    public static Order fromString(String line) {
        String[] parts = line.split(",");
        // Ensure the array has enough parts to avoid ArrayIndexOutOfBoundsException
        String username = parts.length > 0 ? parts[0] : "";
        String address = parts.length > 1 ? parts[1] : "";
        String phoneNumber = parts.length > 2 ? parts[2] : "";
        String toyName = parts.length > 3 ? parts[3] : "";
        double toyPrice = parts.length > 4 ? Double.parseDouble(parts[4]) : 0.0;
        return new Order(username, address, phoneNumber, toyName, toyPrice);
    }
}