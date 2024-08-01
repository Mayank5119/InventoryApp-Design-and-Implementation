import InventoryManagementApp.InventoryDAO.Accessor.impl.ProductsDAOImpl;
import InventoryManagementApp.InventoryManagementApp;
import InventoryManagementApp.InventoryManagementService.ProductManagerService;
import InventoryManagementApp.InventoryManagementService.impl.ProductManagerServiceImpl;

import java.util.Scanner;
//Future enhancement would be dependency injection through so that actual objects don't have to be made in class
public class Main {
    private static InventoryManagementApp inventoryManagementApp;
    private static ProductManagerService productManagerService;
    private static ProductsDAOImpl productsDAOImpl;
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        productsDAOImpl = new ProductsDAOImpl();
        productManagerService = new ProductManagerServiceImpl(productsDAOImpl);
        inventoryManagementApp = new InventoryManagementApp(scanner, productManagerService);
        inventoryManagementApp.startInventoryManagementApp();
    }
}
