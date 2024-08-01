package InventoryManagementApp.InventoryDAO.Accessor.impl;

import InventoryManagementApp.InventoryDAO.Accessor.DatabaseFactory;
import InventoryManagementApp.InventoryDAO.model.Product;
import InventoryManagementApp.InventoryDAO.model.ProductKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductsDAOImpl implements DatabaseFactory {
    public static Map<ProductKey, Product> productDB = DatabaseFactory.getProductsDatabase().getProductDatabase();

    public void add(Product product) {
        ProductKey key = new ProductKey(product.getName(), product.getCategory());
        productDB.put(key, product);
    }

    public Product get(ProductKey key){
        Product product = productDB.get(key);
        return product;
    }

    public void update(String productName, String productCategory, int newQuantity) {
        ProductKey key = new ProductKey(productName, productCategory);
        Product product = get(key);
        if (product != null) {
            product.setQuantity(newQuantity);
        } else {
            System.out.println("Product not found.");
        }
    }

    public List<Product> findAllProducts() {
        List<Product> listOfProducts = new ArrayList<>();
        for (Product product : productDB.values()) {
            listOfProducts.add(product);
        }
        return listOfProducts;
    }

    public Optional<Product> findProductByName(String inputName) {
        for (ProductKey key : productDB.keySet()) {
            if (key.getName().equalsIgnoreCase(inputName)) {
                return Optional.ofNullable(productDB.get(key));
            }
        }
        return Optional.empty();
    }

    public List<Product> findProductByCategory(String inputCategory) {
        List<Product> listOfProducts = new ArrayList<>();
        for (Product product : productDB.values()) {
            if (product.getCategory().equalsIgnoreCase(inputCategory)) {
                listOfProducts.add(product);
            }
        }
        return listOfProducts;
    }

}
