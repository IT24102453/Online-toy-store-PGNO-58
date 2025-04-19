package com.toymanagement.model;

public class Toy {
    private String toyName;
    private String ageGroup;
    private double toyPrice;
    private String category;

    public Toy(String toyName, String ageGroup, double toyPrice, String category) {
        this.toyName = toyName;
        this.ageGroup = ageGroup;
        this.toyPrice = toyPrice;
        this.category = category != null ? category : "General";
    }

    public String getToyName() { return toyName; }
    public void setToyName(String toyName) { this.toyName = toyName; }
    public String getAgeGroup() { return ageGroup; }
    public void setAgeGroup(String ageGroup) { this.ageGroup = ageGroup; }
    public double getToyPrice() { return toyPrice; }
    public void setToyPrice(double toyPrice) { this.toyPrice = toyPrice; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return toyName + "," + ageGroup + "," + toyPrice + "," + category;
    }

    public static Toy fromString(String line) {
        String[] parts = line.split(",");
        String category = parts.length > 3 ? parts[3] : "General";
        return new Toy(parts[0], parts[1], Double.parseDouble(parts[2]), category);
    }
    public int getAge() {
        if (ageGroup == null || ageGroup.isEmpty()) return Integer.MAX_VALUE;
        try {
            String[] parts = ageGroup.split("-");
            return Integer.parseInt(parts[0].trim());
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }

}