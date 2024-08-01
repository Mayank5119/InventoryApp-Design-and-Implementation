package InventoryManagementApp.InventoryManagementService.impl;

import InventoryManagementApp.InventoryDAO.Accessor.impl.ProductsDAOImpl;
import InventoryManagementApp.InventoryDAO.model.Product;
import InventoryManagementApp.InventoryDAO.model.ProductKey;
import InventoryManagementApp.InventoryManagementService.ProductManagerService;

import java.util.List;
import java.util.Optional;


//future enhancement will be making model classes for input and output of all operations in service
public class ProductManagerServiceImpl implements ProductManagerService {

    private static ProductsDAOImpl productsDAOImpl;

    public ProductManagerServiceImpl(ProductsDAOImpl productsDAOImpl) {
        this.productsDAOImpl = productsDAOImpl;
    }

    @Override
    public void addProduct(Product product) {
        if (product.getQuantity() < product.getThreshold()) {
            System.out.println("Warning: Quantity of " + product.getName() + " is below the threshold.");
        }
        productsDAOImpl.add(product);
    }

    @Override
    public void updateProductQuantity(String productName, String productCategory, int newQuantity) {
            ProductKey key = new ProductKey(productName, productCategory);
            Product existingProduct = productsDAOImpl.get(key);
            if (existingProduct != null) {
                productsDAOImpl.update(productName, productCategory, newQuantity);
                if (newQuantity < existingProduct.getThreshold()) {
                    System.out.println("Warning: Quantity of " + existingProduct.getName() + " is below the threshold.");
                }
            } else {
                System.out.println("Product not found.");
            }
    }

    @Override
    public void listProducts() {
        List<Product> listOfProducts = productsDAOImpl.findAllProducts();
        System.out.println("Total number of Products: " + listOfProducts.size());
        for (Product product : listOfProducts) {
            System.out.println(product);
        }
        System.out.println();
    }

    @Override
    public void searchProductByName(String inputName) {
        Optional<Product> product = productsDAOImpl.findProductByName(inputName);
        if (product.isPresent()){
            System.out.println("Product found:"+product+"\n");
        } else {
            System.out.println("No product found with name"+inputName+"\n");
        }
    }

    @Override
    public void searchProductByCategory(String inputCategory) {
        List<Product> listOfProducts = productsDAOImpl.findProductByCategory(inputCategory);
        if (listOfProducts.isEmpty()){
            System.out.println("No product not found in Category."+inputCategory+"\n");
        } else {
            System.out.println("Products in Category: " + inputCategory);
            for (Product product : listOfProducts) {
                System.out.println(product);
            }
        }
        System.out.println();
    }
}