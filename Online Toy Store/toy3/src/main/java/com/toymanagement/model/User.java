package com.toymanagement.model;

public class User {
    private final String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return username + "," + password;
    }

    public static User fromString(String line) {
        String[] parts = line.split(",");
        return new User(parts[0], parts[1]);
    }
}