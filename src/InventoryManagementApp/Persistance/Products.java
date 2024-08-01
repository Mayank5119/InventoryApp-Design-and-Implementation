package InventoryManagementApp.Persistance;

import InventoryManagementApp.InventoryDAO.model.Product;
import InventoryManagementApp.InventoryDAO.model.ProductKey;

import java.util.HashMap;
import java.util.Map;

public class Products {
    private static Map<ProductKey, Product> productsDatabase;
    public Products() {
        this.productsDatabase = new HashMap<>();
    }
    public Map<ProductKey, Product> getProductDatabase(){
        return productsDatabase;
    }
}
