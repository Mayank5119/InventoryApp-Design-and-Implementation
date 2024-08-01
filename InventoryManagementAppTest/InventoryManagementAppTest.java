package test.InventoryManagementAppTest;

import InventoryManagementApp.InventoryDAO.model.Product;
import InventoryManagementApp.InventoryManagementApp;
import InventoryManagementApp.InventoryManagementService.ProductManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;
import java.util.InputMismatchException;

import static org.mockito.Mockito.*;

// future enhancement: shift all constants to a unit test constants file
class InventoryManagementAppTest {

    private Scanner scanner;
    private ProductManagerService productManagerService;
    private InventoryManagementApp inventoryManagementApp;

    @BeforeEach
    void setUp() {
        scanner = mock(Scanner.class);
        productManagerService = mock(ProductManagerService.class);
        inventoryManagementApp = new InventoryManagementApp(scanner, productManagerService);
    }

    @Test
    void testAddProduct() {
        when(scanner.nextInt()).thenReturn(1, 0);
        when(scanner.nextLine()).thenReturn("Test Product", "Test Category");
        when(scanner.nextDouble()).thenReturn(10.99);
        when(scanner.nextInt()).thenReturn(100, 10);

        inventoryManagementApp.startInventoryManagementApp();

        verify(productManagerService, times(1)).addProduct(any(Product.class));
    }

    @Test
    void testUpdateProductQuantity() {
        when(scanner.nextInt()).thenReturn(2, 0);
        when(scanner.nextLine()).thenReturn("Test Product", "Test Category");
        when(scanner.nextInt()).thenReturn(50);

        inventoryManagementApp.startInventoryManagementApp();

        verify(productManagerService, times(1)).updateProductQuantity("Test Product", "Test Category", 50);
    }

    @Test
    void testListProducts() {
        when(scanner.nextInt()).thenReturn(3, 0);

        inventoryManagementApp.startInventoryManagementApp();

        verify(productManagerService, times(1)).listProducts();
    }

    @Test
    void testSearchProductByName() {
        when(scanner.nextInt()).thenReturn(4, 0);
        when(scanner.nextLine()).thenReturn("Test Product");

        inventoryManagementApp.startInventoryManagementApp();

        verify(productManagerService, times(1)).searchProductByName("Test Product");
    }

    @Test
    void testSearchProductsByCategory() {
        when(scanner.nextInt()).thenReturn(5, 0);
        when(scanner.nextLine()).thenReturn("Test Category");

        inventoryManagementApp.startInventoryManagementApp();

        verify(productManagerService, times(1)).searchProductByCategory("Test Category");
    }

    @Test
    void testExitApp() {
        when(scanner.nextInt()).thenReturn(6);

        inventoryManagementApp.startInventoryManagementApp();

        verifyNoMoreInteractions(productManagerService);
    }

    @Test
    void testInvalidInput() {
        when(scanner.nextInt()).thenThrow(new InputMismatchException()).thenReturn(0);
        when(scanner.next()).thenReturn("invalid");

        inventoryManagementApp.startInventoryManagementApp();

        verifyNoMoreInteractions(productManagerService);
    }

    @Test
    void testInvalidOption() {
        when(scanner.nextInt()).thenReturn(7, 0);

        inventoryManagementApp.startInventoryManagementApp();

        verifyNoMoreInteractions(productManagerService);
    }
}