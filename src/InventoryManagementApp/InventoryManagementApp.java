package InventoryManagementApp;

import InventoryManagementApp.InventoryDAO.model.Product;
import InventoryManagementApp.InventoryManagementService.ProductManagerService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManagementApp {
    private ProductManagerService productManagerService;
    private Scanner scanner;

    public InventoryManagementApp(Scanner scanner, ProductManagerService productManagerService) {
        this.scanner = scanner;
        this.productManagerService = productManagerService;
    }

    public void startInventoryManagementApp() {
        boolean appRunning = true;
        while (appRunning) {
            try {
                displayMenu();
                int operation = scanner.nextInt();
                scanner.nextLine();

                switch (operation) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        updateProductQuantity();
                        break;
                    case 3:
                        listProducts();
                        break;
                    case 4:
                        searchProductByName();
                        break;
                    case 5:
                        searchProductsByCategory();
                        break;
                    case 6:
                        appRunning = false;
                        System.out.println("Exiting App \n");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();  // Consume the invalid input
            }
        }
    }

    private void displayMenu() {
        System.out.println("How can we help with your inventory? \n" +
                "1: Add product to inventory \n" +
                "2: Update product quantity in inventory \n" +
                "3: List all products in inventory \n" +
                "4: Search for a product by name \n" +
                "5: Search for products by category \n" +
                "6: Exit the app \n");
    }

    private void addProduct() {
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product category:");
        String category = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Enter product threshold:");
        int threshold = scanner.nextInt();
        scanner.nextLine();
        productManagerService.addProduct(new Product(name, category, price, quantity, threshold));
        System.out.println("Product added to the inventory.\n");
    }

    private void updateProductQuantity() {
        System.out.println("Enter the name of the product:");
        String productName = scanner.nextLine();
        System.out.println("Enter the category of the product:");
        String productCategory = scanner.nextLine();
        System.out.println("Enter the new quantity:");
        int newQuantity = scanner.nextInt();
        scanner.nextLine();
        productManagerService.updateProductQuantity(productName, productCategory, newQuantity);
        System.out.println("Product quantity updated.\n");
    }

    private void listProducts() {
        System.out.println("Listing all products in inventory:");
        productManagerService.listProducts();
    }

    private void searchProductByName() {
        System.out.println("Enter the name of the product you want to search:");
        String inputName = scanner.nextLine();
        System.out.println("Searching product by name: " + inputName);
        productManagerService.searchProductByName(inputName);
    }

    private void searchProductsByCategory() {
        System.out.println("Enter the category of the products you want to search:");
        String inputCategory = scanner.nextLine();
        System.out.println("Searching products by category: " + inputCategory);
        productManagerService.searchProductByCategory(inputCategory);
    }
}