package InventoryManagementApp.InventoryManagementService;

import InventoryManagementApp.InventoryDAO.model.Product;

public interface ProductManagerService {

    void addProduct(Product product);

    void updateProductQuantity(String productName, String productCategory, int newQuantity);

    void listProducts();

    void searchProductByName(String inputName);

    void searchProductByCategory(String inputCategory);

}