package InventoryManagementApp.InventoryDAO.model;

public class Product {
    private String name;
    private String category;
    private double price;
    private int quantity;

    private int threshold;

    public Product(String name, String category, double price, int quantity, int threshold) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): $%.2f, Quantity: %d, Threshold: %d", name, category, price, quantity, threshold);
    }
}
