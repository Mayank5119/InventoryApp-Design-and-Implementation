package InventoryManagementApp.InventoryDAO.model;

import java.util.Objects;

public class ProductKey {
    private String name;
    private String category;

    public ProductKey(String name, String category) {
        this.name = name.toLowerCase();
        this.category = category.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductKey that = (ProductKey) o;
        return Objects.equals(name, that.name) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }
}

